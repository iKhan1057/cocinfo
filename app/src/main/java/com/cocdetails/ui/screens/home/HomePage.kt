package com.cocdetails.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController


@Composable
fun HomePage(navController: NavHostController, name: String) {
    Text(modifier = Modifier.fillMaxSize(),
        color = Color.Red,
        text = name)

}
