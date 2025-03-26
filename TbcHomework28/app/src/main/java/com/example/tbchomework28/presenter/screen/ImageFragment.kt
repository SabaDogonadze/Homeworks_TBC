package com.example.tbchomework28.presenter.screen

import android.net.Uri
import android.os.Build
import androidx.navigation.fragment.findNavController
import com.example.tbchomework28.databinding.FragmentImageBinding
import com.example.tbchomework28.presenter.base.BaseFragment

class ImageFragment : BaseFragment<FragmentImageBinding>(FragmentImageBinding::inflate) {

    override fun setUp() {
        clickListeners()
        setTakenPicture()
        setSelectedImage()
    }

    override fun clickListeners() {
        binding.btnAddImage.setOnClickListener {
            findNavController().navigate(ImageFragmentDirections.actionImageFragmentToCameraBottomSheetFragment())
        }
    }

    // For Android Tiramisu (API 33) and above:
    // It calls bundle.getParcelable("bitmap_key", android.graphics.Bitmap::class.java) to safely retrieve the bitmap.

    //For older Android versions:
   // It calls the older version of bundle.getParcelable("bitmap_key"). The @Suppress("DEPRECATION") annotation is used to suppress warnings about deprecated methods.
    private fun setTakenPicture() {
        parentFragmentManager.setFragmentResultListener(
            "bitmapRequestKey",
            viewLifecycleOwner
        ) { _, bundle ->
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("bitmap_key", android.graphics.Bitmap::class.java)
            } else {
                @Suppress("DEPRECATION")
                bundle.getParcelable("bitmap_key")
            }
            bitmap?.let {
                binding.ivImage.setImageBitmap(it) //resulting bitmap (if present) is stored in the bitmap variable.
            }
        }
    }

    private fun setSelectedImage() {
        parentFragmentManager.setFragmentResultListener(
            "imageUriRequestKey",
            viewLifecycleOwner
        ) { _, bundle ->
            val imageUri = bundle.getParcelable("imageUri_key") as? Uri // this is deprecated should change like above
            imageUri?.let {
                binding.ivImage.setImageURI(it)
            }
        }
    }
}
