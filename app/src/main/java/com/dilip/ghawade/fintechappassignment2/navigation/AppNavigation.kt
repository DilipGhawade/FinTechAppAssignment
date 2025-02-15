package com.dilip.ghawade.fintechappassignment2.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dilip.ghawade.fintechappassignment2.ui.theme.view.HomeScreen
import com.dilip.ghawade.fintechappassignment2.ui.theme.view.LoginScreen
import com.dilip.ghawade.fintechappassignment2.ui.theme.view.RegistrationScreen
import com.dilip.ghawade.fintechappassignment2.ui.theme.view.TransactionHistoryScreen
import com.dilip.ghawade.fintechappassignment2.ui.theme.view.TransactionScreen
import com.dilip.ghawade.fintechappassignment2.viewmodel.AuthViewModel

@androidx.compose.runtime.Composable
fun AppNavigation(navController: NavHostController, viewModel: AuthViewModel) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) Screens.Home.route else Screens.Login.route
    ) {
        composable(Screens.Login.route) {
            LoginScreen(navController, viewModel)
        }
        composable(Screens.Registration.route) {
            RegistrationScreen(navController)
        }
        composable(Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(Screens.Transaction.route) {
            TransactionScreen(navController)
        }
        composable(Screens.TransactionHistory.route) {
            TransactionHistoryScreen(navController)
        }
    }

}