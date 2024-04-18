package com.sb.camerax

import android.Manifest
import android.app.Instrumentation.ActivityResult
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sb.camerax.ui.sceen.CameraViewScreen
import com.sb.camerax.ui.theme.CameraXTheme

class MainActivity : ComponentActivity() {
    val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
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
        setContent {
            CameraXTheme {
                CameraViewScreen()
            }
        }
    }

    private fun hasPermissionForCamera(): Boolean = checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
}