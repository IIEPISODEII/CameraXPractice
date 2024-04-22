package com.sb.camerax.ui.sceen

import android.content.Intent
import android.provider.MediaStore
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.sb.camerax.R
import com.sb.camerax.ui.action.CameraScreenAction
import com.sb.camerax.ui.composables.CamIconButton
import com.sb.camerax.ui.theme.TranslucentBlue
import com.sb.camerax.util.switchCameraDirection
import com.sb.camerax.util.takePicture
import kotlinx.coroutines.delay

@Composable
fun CameraViewScreen(
    onAction: CameraScreenAction
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var isFirstComposition by remember { mutableStateOf(true) }

    val cameraController = LifecycleCameraController(context).apply {
        this.bindToLifecycle(lifecycleOwner)
        this.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    }
    val previewView = remember {
        PreviewView(context).apply {
            this.controller = cameraController
        }
    }
    val intent = remember {
        Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
        }
    }

    var isZoomInfoActivated by remember { mutableStateOf(false) }

    var zoomRatio by remember { mutableFloatStateOf(1F) }

    val roundedZoomRatio by remember(zoomRatio) {
        derivedStateOf {
            String.format("%.1f", zoomRatio).toFloatOrNull()
        }
    }

    LaunchedEffect(key1 = lifecycleOwner, key2 = previewView) {
        previewView.controller?.zoomState?.observe(lifecycleOwner) {
            zoomRatio = it?.zoomRatio ?: 1F
        }
    }
    LaunchedEffect(key1 = roundedZoomRatio) {
        if (isFirstComposition) {
            isFirstComposition = false
            return@LaunchedEffect
        }
        isZoomInfoActivated = true
        delay(2000L)
        isZoomInfoActivated = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AndroidView(
            modifier = Modifier
                .fillMaxSize(),
            factory = {
                previewView
            }
        )
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.Center),
            visible = isZoomInfoActivated,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                modifier = Modifier
                    .background(color = TranslucentBlue, shape = RoundedCornerShape(50))
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                text = "x $roundedZoomRatio",
                color = Color.White,
                fontSize = 16.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2F)
                .background(color = Color(0x7F7F7F7F))
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CamIconButton(
                modifier = Modifier
                    .weight(0.15F, false)
                    .height(50.dp),
                onClick = { context.startActivity(intent) },
                painter = painterResource(id = R.drawable.rounded_photo_24)
            )
            CamIconButton(
                modifier = Modifier
                    .weight(0.2F, false)
                    .height(60.dp),
                onClick = { cameraController.takePicture(context) },
                painter = painterResource(id = R.drawable.rounded_photo_camera_24)
            )
            CamIconButton(
                modifier = Modifier
                    .weight(0.15F, false)
                    .height(50.dp),
                onClick = { cameraController.switchCameraDirection() },
                painter = painterResource(id = R.drawable.baseline_cameraswitch_24)
            )
        }
    }
}