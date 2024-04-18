package com.sb.imfine.presentation.view.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sb.imfine.presentation.data.UserViewData
import com.sb.imfine.presentation.view.ui.composable.ImButton
import com.sb.imfine.presentation.view.ui.theme.robotoFont
import com.sb.imfine.util.BIRTHDAY_FORMAT
import com.sb.imfine.util.formatTime

@Composable
fun LoginScreen(
    user: UserViewData,
    onStartClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.weight(1F))
        AsyncImage(
            model = user.profileImage,
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.weight(1F))
        Text(
            modifier = Modifier.fillMaxWidth(0.9F),
            text = user.name,
            color = Color.Gray,
            fontSize = TextUnit(24F, TextUnitType.Sp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.fillMaxWidth(0.9F),
            text = formatTime(user.birthDay, BIRTHDAY_FORMAT),
            color = Color.Gray,
            fontSize = TextUnit(20F, TextUnitType.Sp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1.5F))
        ImButton(
            text = "Start",
            onClick = {
                onStartClick()
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}