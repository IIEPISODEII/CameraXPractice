package com.sb.imfine.presentation.view.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sb.imfine.presentation.view.ui.theme.robotoFont
import com.sb.imfine.util.CLICK_DEBOUNCE_BUFFER

@Composable
fun ImButton(
    text: String,
    onClick: () -> Unit
) {
    var preClickTime by remember {
        mutableLongStateOf(0L)
    }

    Button(
        modifier = Modifier
            .fillMaxWidth(0.85F)
            .wrapContentHeight(),
        onClick = {
            if (System.currentTimeMillis() - preClickTime > CLICK_DEBOUNCE_BUFFER) {
                onClick()
            }
            preClickTime = System.currentTimeMillis()
        }
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp),
            text = text,
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = robotoFont,
            fontWeight = FontWeight.Normal,
        )
    }
}