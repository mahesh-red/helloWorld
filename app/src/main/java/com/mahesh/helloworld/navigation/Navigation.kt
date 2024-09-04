package com.mahesh.helloworld.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mahesh.helloworld.components.CalculationScren
import com.mahesh.helloworld.components.NameScreen
import com.mahesh.helloworld.components.myViewModel

class Navigation {

@Composable
    fun MyNavigation(myViewModel: myViewModel){

        val navcontroller= rememberNavController()
    NavHost(navController = navcontroller, startDestination = "home" ){
        composable("home"){
            CalculationScren(modifier = Modifier, viewModel = myViewModel, navController = navcontroller)

        }
        composable("details/{name}", arguments = listOf(navArgument("name"){type=
            NavType.StringType})){ navBackStackEntry ->
            NameScreen(name=navBackStackEntry.arguments?.getString("name")!!)

        }
        
    }

    }
}