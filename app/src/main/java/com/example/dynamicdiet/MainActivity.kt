package com.example.dynamicdiet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dynamicdiet.dto.Weight
import com.example.dynamicdiet.ui.theme.DynamicDietTheme
import java.util.*

class MainActivity : ComponentActivity() {

//    var viewModel = MainViewModel()
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val weightEntries = ArrayList<Weight>()
//            viewModel.weightEntries.add(Weight(weight = 165.0, date = "03-25-2022"))
            DynamicDietTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }
}








