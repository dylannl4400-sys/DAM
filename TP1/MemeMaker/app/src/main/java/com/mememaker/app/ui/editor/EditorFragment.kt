package com.mememaker.app.ui.editor

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mememaker.app.databinding.FragmentEditorBinding
import java.io.File
import java.io.FileOutputStream
import kotlin.math.atan2

class EditorFragment : Fragment() {

    private var _binding: FragmentEditorBinding? = null
    private val binding get() = _binding!!
    private val args: EditorFragmentArgs by navArgs()

    private var lastFocusedEditText: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val textOverlays = mutableListOf<EditText>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupToolbar()
        loadImage()
        setupInitialTexts()
        setupShareButton()
        setupAddTextButton()
        setupRemoveTextButton()
        setupFilters()
        setupColorSelection()
    }

    private fun setupRemoveTextButton() {
        binding.buttonRemoveText.setOnClickListener {
            removeFocusedTextOverlay()
        }
    }

    private fun removeFocusedTextOverlay() {
        val focusedView = lastFocusedEditText ?: return
        
        // Remove from UI
        binding.memeContainer.removeView(focusedView)
        
        // Remove from list
        textOverlays.remove(focusedView)
        
        // Clear focus tracker
        lastFocusedEditText = null
        
        // Focus the last remaining overlay if any
        if (textOverlays.isNotEmpty()) {
            val last = textOverlays.last()
            last.requestFocus()
            lastFocusedEditText = last
        }
    }

    private fun setupInitialTexts() {
        // We can keep the existing ones for backward compatibility or starting point
        textOverlays.add(binding.editTextTop)
        textOverlays.add(binding.editTextBottom)

        textOverlays.forEach { editText ->
            setupEditText(editText)
        }
        
        lastFocusedEditText = binding.editTextTop
    }

    private fun setupEditText(editText: EditText) {
        makeInteractive(editText)
        editText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) lastFocusedEditText = editText
        }
    }

    private fun setupAddTextButton() {
        binding.buttonAddText.setOnClickListener {
            addNewTextOverlay()
        }
    }

    private fun addNewTextOverlay() {
        val newText = EditText(requireContext()).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = android.view.Gravity.CENTER
            }
            hint = "NEW TEXT"
            minWidth = 200
            background = null
            gravity = android.view.Gravity.CENTER
            inputType = android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE
            setTextColor(Color.WHITE)
            textSize = 32f
            setShadowLayer(4f, 2f, 2f, Color.BLACK)
            // Use same font as in XML if possible, but for dynamic views default sans-serif-black is fine
            typeface = android.graphics.Typeface.create("sans-serif-black", android.graphics.Typeface.NORMAL)
        }

        setupEditText(newText)
        binding.memeContainer.addView(newText)
        textOverlays.add(newText)
        newText.requestFocus()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun loadImage() {
        Glide.with(requireContext())
            .load(args.imageUrl)
            .into(binding.imageViewMemeEdit)
    }

    private fun makeInteractive(view: View) {
        var dX = 0f
        var dY = 0f

        val scaleDetector = ScaleGestureDetector(requireContext(), object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                view.scaleX *= detector.scaleFactor
                view.scaleY *= detector.scaleFactor
                return true
            }
        })

        // Simple rotation logic
        var lastAngle = 0f

        view.setOnTouchListener { v, event ->
            scaleDetector.onTouchEvent(event)

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    dX = v.x - event.rawX
                    dY = v.y - event.rawY
                    v.requestFocus()
                    true
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    if (event.pointerCount == 2) {
                        lastAngle = rotationAngle(event)
                    }
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    if (event.pointerCount == 1) {
                        v.animate()
                            .x(event.rawX + dX)
                            .y(event.rawY + dY)
                            .setDuration(0)
                            .start()
                    } else if (event.pointerCount == 2) {
                        val currentAngle = rotationAngle(event)
                        v.rotation += currentAngle - lastAngle
                        lastAngle = currentAngle
                    }
                    true
                }
                MotionEvent.ACTION_UP -> {
                    v.performClick()
                    false
                }
                else -> false
            }
        }
    }

    private fun rotationAngle(event: MotionEvent): Float {
        val dx = (event.getX(0) - event.getX(1)).toDouble()
        val dy = (event.getY(0) - event.getY(1)).toDouble()
        val radians = atan2(dy, dx)
        return Math.toDegrees(radians).toFloat()
    }

    private fun setupColorSelection() {
        val colors = listOf(
            Color.WHITE, Color.BLACK, Color.RED, Color.GREEN, 
            Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA,
            Color.parseColor("#FF5722"), // Deep Orange
            Color.parseColor("#4CAF50"), // Green
            Color.parseColor("#2196F3"), // Blue
            Color.parseColor("#FFEB3B"), // Yellow
            Color.parseColor("#9C27B0")  // Purple
        )

        colors.forEach { color ->
            val colorView = View(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(100, 100).apply {
                    setMargins(8, 8, 8, 8)
                }
                background = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    setColor(color)
                    setStroke(2, Color.LTGRAY)
                }
                setOnClickListener {
                    lastFocusedEditText?.setTextColor(color)
                }
            }
            binding.colorContainer.addView(colorView)
        }
    }

    private fun setupShareButton() {
        binding.buttonShare.setOnClickListener {
            shareMeme()
        }
    }

    private fun setupFilters() {
        binding.btnFilterNormal.setOnClickListener {
            binding.imageViewMemeEdit.clearColorFilter()
        }
        binding.btnFilterGray.setOnClickListener {
            val matrix = ColorMatrix().apply { setSaturation(0f) }
            binding.imageViewMemeEdit.colorFilter = ColorMatrixColorFilter(matrix)
        }
        binding.btnFilterSepia.setOnClickListener {
            val matrix = ColorMatrix().apply {
                setSaturation(0f)
                val sepiaMatrix = ColorMatrix(
                    floatArrayOf(
                        1.2f, 0f, 0f, 0f, 0f,
                        0f, 1.0f, 0f, 0f, 0f,
                        0f, 0f, 0.8f, 0f, 0f,
                        0f, 0f, 0f, 1f, 0f
                    )
                )
                postConcat(sepiaMatrix)
            }
            binding.imageViewMemeEdit.colorFilter = ColorMatrixColorFilter(matrix)
        }
        binding.btnFilterInvert.setOnClickListener {
            val matrix = ColorMatrix(
                floatArrayOf(
                    -1f, 0f, 0f, 0f, 255f,
                    0f, -1f, 0f, 0f, 255f,
                    0f, 0f, -1f, 0f, 255f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
            binding.imageViewMemeEdit.colorFilter = ColorMatrixColorFilter(matrix)
        }
    }

    private fun shareMeme() {
        // Clear focus so cursor doesn't appear in shared image
        textOverlays.forEach { it.clearFocus() }
        
        val bitmap = getViewBitmap(binding.memeContainer)
        val uri = saveImageToCache(bitmap)
        if (uri != null) {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/jpeg"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            startActivity(Intent.createChooser(shareIntent, "Share Meme via"))
        } else {
            Toast.makeText(requireContext(), "Failed to prepare image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getViewBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun saveImageToCache(bitmap: Bitmap): Uri? {
        try {
            val cachePath = File(requireContext().cacheDir, "images")
            cachePath.mkdirs()
            val stream = FileOutputStream("$cachePath/shared_meme.jpeg")
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.close()
 
            val newFile = File(cachePath, "shared_meme.jpeg")
            return FileProvider.getUriForFile(requireContext(), "${requireContext().packageName}.provider", newFile)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
