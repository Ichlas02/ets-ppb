package com.example.etsppb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JadwalKereta(navController: NavController, username: String, stasiunKeberangkatan: String,
                 stasiunTujuan: String, jadwalKereta: String, jumlahPenumpang: String) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF155E9F),
            Color(0xFF68C3EA)
        )
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "$stasiunKeberangkatan > $stasiunTujuan".uppercase())
                        Text(
                            text = "$jadwalKereta - $jumlahPenumpang Penumpang",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Routes.tiketKereta + "/$username")
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color(0xFFFFFFFF),
                    navigationIconContentColor = Color(0xFFFFFFFF)
                ),
                modifier = Modifier.background(
                    brush = gradient
                )
            )
        }
    ) {
        val padding = it
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color.LightGray)){
            LazyRow(modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly){
                items(tanggalKeberangkatan) { tanggal ->
                    CardDate(tanggal)
                }
            }
            Box(
                modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize())
                {
                    items(daftarKereta) { tiket ->
                        CardTiket(tiket, username, stasiunKeberangkatan, stasiunTujuan, jumlahPenumpang, jadwalKereta, navController)
                    }
                }
            }
        }
    }
}

data class Tiket(
    val namaKereta: String,
    val kelas: String,
    val stasiunKeberangkatan: String,
    val waktuKeberangkatan: String,
    val durasi: String,
    val stasiunTujuan: String,
    val waktuTiba: String,
    val harga: String,
    val status: Boolean
)

data class Tanggal(
    val day: String,
    val date: String
)

val tanggalKeberangkatan = listOf(
    Tanggal("Min", "19"),
    Tanggal("Sen", "20"),
    Tanggal("Sel", "21"),
    Tanggal("Rab", "22"),
    Tanggal("Kam", "23"),
    Tanggal("Jum", "24"),
    Tanggal("Sab", "25"),

)

val daftarKereta = listOf(
    Tiket("Airlangga", "Ekonomi (L)", "PSE", "11.20", "11j 45m", "SBI", "23.05", "110.000", false),
    Tiket("Dharmawangsa", "Ekonomi (P)", "PSE", "19.20", "11j 03m", "SBI", "07.23", "280.000", true),
    Tiket("Kertajaya", "Ekonomi (CC)", "PSE", "14.30", "10j 32m", "SBI", "01.02", "340.000", true),
    Tiket("Kertajaya", "Ekonomi (CD)", "PSE", "14.30", "10j 32m", "SBI", "01.02", "350.000", true),
    )

@Composable
fun CardDate(tanggal: Tanggal){
    val gradient = Brush.linearGradient(
        colors = if (tanggal.date == "22") listOf(
            Color(0xFF155E9F),
            Color(0xFF68C3EA)
        ) else listOf(
            Color(0xFF155E9F).copy(alpha = 0.5f),
            Color(0xFF68C3EA).copy(alpha = 0.5f)
        )
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column(modifier = Modifier.fillMaxWidth()
            .background(brush = gradient)
            .padding(vertical = 8.dp, horizontal = 10.dp))
        {
            Text(
                text = tanggal.day,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
            Text(
                text = tanggal.date,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun CardTiket(
    tiket: Tiket, username: String, stasiunKeberangkatan: String, stasiunTujuan: String,
    jumlahPenumpang: String, jadwalKereta: String, navController: NavController
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = tiket.namaKereta,
                    color = if (tiket.status) Color(0xFFE16417) else Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Text(
                    text = tiket.kelas,
                    color = if (tiket.status) Color.DarkGray else Color.Gray,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = tiket.stasiunKeberangkatan,
                            color = if (tiket.status) Color.DarkGray else Color.Gray
                        )
                        Text(
                            text = tiket.waktuKeberangkatan,
                            fontWeight = FontWeight.Bold,
                            color = if (tiket.status) Color.DarkGray else Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Icon(Icons.Filled.ArrowForward,
                            contentDescription = "")
                        Text(
                            text = tiket.durasi,
                            color = if (tiket.status) Color.DarkGray else Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(
                            text = tiket.stasiunTujuan,
                            color = if (tiket.status) Color.DarkGray else Color.Gray
                        )
                        Text(
                            text = tiket.waktuTiba,
                            fontWeight = FontWeight.Bold,
                            color = if (tiket.status) Color.DarkGray else Color.Gray
                        )
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = if (tiket.status) "Harga tersedia mulai dari" else "(Kursi Habis)",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = "Rp. ${tiket.harga}",
                        color = if (tiket.status) Color(0xFFE16417) else Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = "/pax",
                        color = if (tiket.status) Color.DarkGray else Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        navController.navigate(
                            Routes.login
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (tiket.status) Color(0xFFE16417) else Color.Gray,
                        contentColor = Color(0xFFFFFFFF)
                    ),
                    modifier = Modifier
                        .width(120.dp)
                        .height(40.dp),
                    enabled = tiket.status
                ) {
                    Text(text = "pilih".uppercase())
                }
            }
        }
    }
}