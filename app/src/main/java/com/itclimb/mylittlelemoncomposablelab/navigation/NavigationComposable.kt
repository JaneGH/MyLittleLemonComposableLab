package com.itclimb.mylittlelemoncomposablelab.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itclimb.mylittlelemoncomposablelab.screens.onBoarding
import com.itclimb.mylittlelemoncomposablelab.screens.onProfile
import com.itclimb.mylittlelemoncomposablelab.screens.onHome


@Composable
fun MyNavigation(navController: NavHostController, context: Context) {
    var initialRoute = Onboarding.route
    if (navController.context.getSharedPreferences("sharedPref", Context.MODE_PRIVATE).getString("firstName","")?.isNotBlank() == true ){
        initialRoute = Home.route
    }

    NavHost(navController = navController, startDestination = initialRoute){
        composable(Onboarding.route){
            onBoarding(navController)
        }

           composable(Home.route) {
               onHome(navController,context)
           }

           composable(Profile.route) {
               onProfile(navController)
           }
       }
}


