package com.dicoding.huguapp.ui.screen.loginpage

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.huguapp.R
import com.dicoding.huguapp.ui.component.MyTextField
import com.dicoding.huguapp.ui.component.buttonlogpage

@Composable
fun LoginScreen(){
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box{
                Image(
                    painter = painterResource(id = R.drawable.logo_hugu),
                    contentDescription = "",
                    modifier = Modifier
                        .size(350.dp)
                        .padding(top = 40.dp)

                )
                Text (
                        text = "Hug U",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                color = colorResource(id = R.color.blue_80),
                modifier = Modifier.align(Alignment.BottomCenter)
                )

            }
            Spacer(modifier = Modifier.height(30.dp))
            MyTextField(
                labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.icon_mail))

            MyTextField(
                labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.icon_lock))

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(190.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue_100)))
            {
                Text (text = stringResource(id = R.string.masuk),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(190.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.red)))
            {
                Text (text = stringResource(id = R.string.daftar),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}



@Preview
@Composable
fun previewlogin(){
    LoginScreen()
}

