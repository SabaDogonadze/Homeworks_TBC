package com.example.tbchomework28.presenter.screen

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.tbchomework28.R
import com.example.tbchomework28.databinding.FragmentCameraBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CameraBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCameraBottomSheetBinding? = null
    private val binding get() = _binding!!

    //camera permission request launcher
    //registers an ActivityResultLauncher for a permission request using ActivityResultContracts.RequestPermission().
    //when the permission request completes, the lambda receives a Boolean (isGranted) indicating whether permission was granted.
    // if permission is granted it takes picture and if permission is denied it shows toast
    private val requestCameraPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                takePicture()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.camera_permission_is_required),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    // gallery permission request launcher
    // same logic as camera request launcher
    private val requestGalleryPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                pickImageFromGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.gallery_permission_is_required),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    //uses  TakePicturePreview contract to capture an image as a bitmap
    //if a bitmap is returned (for example - the capture is successful)  bitmap is placed into a Bundle with the key "bitmap_key"
    //bundle is sent back to a parent fragment via setFragmentResult using the key "bitmapRequestKey"
    private val takePicturePreviewLauncher: ActivityResultLauncher<Void?> =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            bitmap?.let {
                val resultBundle = Bundle().apply {
                    putParcelable("bitmap_key", it)
                }
                parentFragmentManager.setFragmentResult("bitmapRequestKey", resultBundle)
            }
            dismiss() //bottom sheet is dismissed
        }

    // uses the GetContent contract to allow the user to select an image from their device
    // if an image URI is returned, it is added to a Bundle with the key "imageUri_key"
    // result is sent back to the parent fragment using the key "imageUriRequestKey"
    private val pickImageLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val resultBundle = Bundle().apply {
                    putParcelable("imageUri_key", it)
                }
                parentFragmentManager.setFragmentResult("imageUriRequestKey", resultBundle)
            }
            dismiss()
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCameraBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        clickListener()
    }

    private fun clickListener() {
        binding.btnTakePicture.setOnClickListener {
            checkCameraPermissionAndTakePicture()
        }
        binding.btnGalleyPicture.setOnClickListener {
            checkGalleryPermissionAndPickImage()
        }
    }

    //uses ContextCompat.checkSelfPermission to verify if the camera permission is already granted
    // if it is granted it takes picture, if not launches the camera permission request with requestCameraPermissionLauncher
    private fun checkCameraPermissionAndTakePicture() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            takePicture()
        } else {
            requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    // launch(null) indicates no additional input is needed
    private fun takePicture() {
        takePicturePreviewLauncher.launch(null)
    }

    // checks android version
    // for Android Tiramisu (API level 33) or newer, it requires READ_MEDIA_IMAGES
    // for older versions, it requires READ_EXTERNAL_STORAGE

    //uses ContextCompat.checkSelfPermission to verify if the permission is already granted
    //if permission is granted it calls pickImageFromGallery() , if it is denied it calls  equestGalleryPermissionLauncher.launch(permission)
    private fun checkGalleryPermissionAndPickImage() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        if (ContextCompat.checkSelfPermission(requireContext(), permission)
            == PackageManager.PERMISSION_GRANTED
        ) {
            pickImageFromGallery()
        } else {
            requestGalleryPermissionLauncher.launch(permission)
        }
    }

    // parameter "image/*" ensures that only images are shown for selection.
    private fun pickImageFromGallery() {
        pickImageLauncher.launch("image/*")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
