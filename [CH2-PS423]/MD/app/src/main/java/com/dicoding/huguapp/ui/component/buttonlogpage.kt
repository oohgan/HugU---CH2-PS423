package com.dicoding.huguapp.ui.component

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun buttonlogpage(value: String ){
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .width(190.dp)
            .height(40.dp))
    {
        Text (text = value,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}