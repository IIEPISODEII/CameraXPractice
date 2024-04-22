package com.sb.camerax

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.sb.camerax.ui.action.CameraScreenAction
import com.sb.camerax.ui.sceen.CameraViewScreen
import com.sb.camerax.ui.theme.CameraXTheme

class MainActivity : ComponentActivity() {
    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) startMainScreen()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (hasPermissionForCamera()) {
            startMainScreen()
        } else {
            requestPermission.launch(Manifest.permission.CAMERA)
        }
    }

    private fun startMainScreen() {
        val cameraScreenAction = object: CameraScreenAction {
            override fun launchGallery() {
                val intent = Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"

                if (intent.resolveActivity(this@MainActivity.packageManager) != null) {
                    startActivity(intent)
                }
            }
        }
        setContent {
            CameraXTheme {
                CameraViewScreen(cameraScreenAction)
            }
        }
    }

    private fun hasPermissionForCamera(): Boolean = checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
}