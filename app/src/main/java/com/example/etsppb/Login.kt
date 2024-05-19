package com.example.etsppb

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Login(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val gradient = Brush.linearGradient(
            colors = listOf(
                Color(0xFF155E9F),
                Color(0xFF68C3EA)
            )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(280.dp)
                .background(brush = gradient),
        ) {
            Column(modifier = Modifier.fillMaxSize()){
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.access_kai_logo_white),
                        contentDescription = "Login Image",
                        modifier = Modifier.size(200.dp),
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(){
                        Text(
                            text = "Halo, Ichlas",
                            fontSize = 24.sp,
                            color = Color(0xFFFFFFFF),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 6.dp)
                        )
                        Text(
                            text = "Pilih layanan Access by KAI",
                            fontSize = 18.sp,
                            color = Color(0xFFFFFFFF),
                        )
                    }
                }
            }
        }

        val username = "Ichlas"

        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(modifier = Modifier.fillMaxSize()){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ServiceButton("Kereta Antar Kota", R.drawable.kereta_antar_kota, navController)
                    ServiceButton("Kereta Lokal", R.drawable.kereta_lokal, navController)
                    ServiceButton("Kereta Bandara", R.drawable.kereta_bandara, navController)
                }

                PromoSection()
            }
        }
    }
}

@Composable
fun ServiceButton(label: String, iconResId: Int, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                if (label == "Kereta Antar Kota") {
//                    navController.navigate("kereta_antar_kota")
                    navController.navigate(Routes.tiketKereta + "/Ichlas")
                }
            }
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            modifier = Modifier.size(48.dp)
        )
        Text(text = label, fontSize = 14.sp, modifier = Modifier.padding(top = 6.dp))
    }
}

@Composable
fun PromoSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(top = 30.dp), // Placeholder for the promo image
        contentAlignment = Alignment.TopStart
    ) {
        Column(){
            Text("Promo", color = Color.DarkGray, fontWeight = FontWeight.SemiBold, fontSize = 25.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.promo1),
                    contentDescription = "Promo1",
                    modifier = Modifier
                        .size(300.dp)
                )
            }
        }
    }
}