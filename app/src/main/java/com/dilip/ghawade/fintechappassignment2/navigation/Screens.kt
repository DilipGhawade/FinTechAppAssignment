package com.dilip.ghawade.fintechappassignment2.navigation

sealed class Screens(val route: String) {
    data object Login : Screens(route = "login_screen")
    data object Registration : Screens(route = "registration_screen")
    data object Home : Screens(route = "home_screen")
    data object Transaction : Screens(route = "txn_screen")
    data object TransactionHistory : Screens(route = "txn_history_screen")

}