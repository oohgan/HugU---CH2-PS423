package com.dicoding.huguapp.ui.component

import android.icu.text.AlphabeticIndex.Bucket.LabelType
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun MyTextField(labelValue: String, painterResource : Painter){
    val textValue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        label = {Text(text = labelValue)},
        value = textValue.value,
        keyboardOptions = KeyboardOptions.Default,
        onValueChange = {
            textValue.value = it
        },
        leadingIcon ={
            Icon(painter = painterResource, contentDescription = "",
                modifier = Modifier.size(30.dp))
        } )
}