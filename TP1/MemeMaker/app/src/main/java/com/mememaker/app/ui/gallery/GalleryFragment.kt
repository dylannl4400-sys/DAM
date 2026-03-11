package com.mememaker.app.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mememaker.app.databinding.FragmentGalleryBinding
import kotlinx.coroutines.launch

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GalleryViewModel by viewModels()
    private lateinit var adapter: MemeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = MemeAdapter { meme ->
            val action = GalleryFragmentDirections.actionGalleryFragmentToEditorFragment(meme.url)
            findNavController().navigate(action)
        }
        binding.recyclerViewMemes.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewMemes.adapter = adapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is GalleryState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerViewMemes.visibility = View.GONE
                        }
                        is GalleryState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            binding.recyclerViewMemes.visibility = View.VISIBLE
                            adapter.submitList(state.memes)
                        }
                        is GalleryState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
