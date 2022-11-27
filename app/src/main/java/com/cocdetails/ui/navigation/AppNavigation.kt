package com.cocdetails.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cocdetails.ui.screens.home.HomePage
import com.cocdetails.ui.screens.homedetails.HomeDetails
import com.cocdetails.ui.screens.landing.LandingPage

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreenName.LANDINGSCREEN.name) {
        composable(route = AppScreenName.LANDINGSCREEN.name) { navBackStackEntry ->
            LandingPage(navController)
        }

//        composable(route = AppScreenName.HOMESCREEN.name + "/{name}", arguments = listOf(
//            navArgument(name = "name") {
//                defaultValue = ""
//                nullable = true
//                type = NavType.StringType
//            }
//        )) { navBackStackEntry ->
//            HomePage(
//                navController = navController,
//                name = navBackStackEntry.arguments?.getString("name")!!
//            )
//        }
//
//        composable(route = AppScreenName.HOMEDETAILS.name) { navBackStackEntry ->
//           HomeDetails(navHostController = navController)
//        }
    }
}