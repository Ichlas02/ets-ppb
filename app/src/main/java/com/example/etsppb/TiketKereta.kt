package com.example.etsppb

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiketKereta(navController: NavController, username: String) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF155E9F),
            Color(0xFF68C3EA)
        )
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Kereta Antar Kota") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Routes.login)
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            var stasiunKeberangkatan by remember { mutableStateOf("Jakarta") }
            var stasiunTujuan by remember { mutableStateOf("Surabaya") }
            var jadwalKereta by remember { mutableStateOf("") }
            var jumlahPenumpang by remember { mutableStateOf(1) }

            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                LocalContext.current,
                { _, year, month, dayOfMonth ->
                    val selectedDate = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }.time
                    val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))
                    jadwalKereta = dateFormat.format(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = stasiunKeberangkatan,
                    onValueChange = { stasiunKeberangkatan = it },
                    label = { Text("From") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = stasiunTujuan,
                    onValueChange = { stasiunTujuan = it },
                    label = { Text("To") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = jadwalKereta,
                    onValueChange = { },
                    label = { Text("Tanggal Keberangkatan") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { datePickerDialog.show() }) {
                            Icon(Icons.Default.DateRange, contentDescription = "Pilih Tanggal")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Adults", fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(20.dp))
                    CounterButton(count = jumlahPenumpang, updateCount = { jumlahPenumpang = it })
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate(Routes.jadwalKereta +
                                "/$username/$stasiunKeberangkatan/$stasiunTujuan/$jadwalKereta/$jumlahPenumpang")
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE16417),
                        contentColor = Color(0xFFFFFFFF)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Cari Tiket".uppercase())
                }
            }
        }
    }
}

@Composable
fun CounterButton(count: Int, updateCount: (Int) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically){
        Button(
            onClick = { if (count > 1) updateCount(count - 1)},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF68C3EA)),
            ) {
            Text("-", fontSize = 15.sp)
        }
        Text(count.toString(), modifier = Modifier.padding(horizontal = 8.dp), fontSize = 20.sp)
        Button(
            onClick = { updateCount(count + 1) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF68C3EA))
            ) {
            Text("+", fontSize = 15.sp)
        }
    }
}