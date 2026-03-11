package com.mememaker.app.ui.gallery

import android.os.Bundle
import androidx.navigation.NavDirections
import com.mememaker.app.R
import kotlin.Int
import kotlin.String

public class GalleryFragmentDirections private constructor() {
  private data class ActionGalleryFragmentToEditorFragment(
    public val imageUrl: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_galleryFragment_to_editorFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("imageUrl", this.imageUrl)
        return result
      }
  }

  public companion object {
    public fun actionGalleryFragmentToEditorFragment(imageUrl: String): NavDirections =
        ActionGalleryFragmentToEditorFragment(imageUrl)
  }
}
