package com.example.etsppb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.login, builder = {
                composable(Routes.login) {
                    Login(navController = navController)
                }
                composable(Routes.tiketKereta + "/{username}") {
                    val username = it.arguments?.getString("username")
                    TiketKereta(navController, username?: "User tidak ditemukan")
                }
                composable(
                    Routes.jadwalKereta + "/{username}" + "/{stasiunKeberangkatan}" +
                            "/{stasiunTujuan}" + "/{jadwalKereta}" +"/{jumlahPenumpang}"
                ) {
                    val username = it.arguments?.getString("username")
                    val stasiunKeberangkatan = it.arguments?.getString("stasiunKeberangkatan")
                    val stasiunTujuan = it.arguments?.getString("stasiunTujuan")
                    val jadwalKereta = it.arguments?.getString("jadwalKereta")
                    val jumlahPenumpang = it.arguments?.getString("jumlahPenumpang")
                    JadwalKereta(
                        navController = navController,
                        username?: "User tidak ditemukan",
                        stasiunKeberangkatan?: "Stasiun tidak ditemukan",
                        stasiunTujuan?: "Stasiun tidak ditemukan",
                        jadwalKereta?: "Jadwal tidak ditemukan",
                        jumlahPenumpang?: "Penumpang tidak ditemukan"
                    )
                }
            })
        }
    }
}