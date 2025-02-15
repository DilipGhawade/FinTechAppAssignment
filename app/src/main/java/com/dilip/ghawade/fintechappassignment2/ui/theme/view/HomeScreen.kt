package com.dilip.ghawade.fintechappassignment2.ui.theme.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dilip.ghawade.fintechappassignment2.navigation.Screens
import com.dilip.ghawade.fintechappassignment2.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun HomeScreen(navController: NavHostController) {
    var isSwitchOn by remember { mutableStateOf(false) }
    val viewModel: AuthViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Home")
                },
                actions = {
                    Button(onClick = {
                        viewModel.logoutUser() // Call the logout method in the ViewModel
                        navController.navigate(Screens.Login.route) {
                            popUpTo(Screens.Home.route) { inclusive = true }
                        }
                    }) {
                        Text("Logout")
                    }
                }

            )
        },

        content = { p ->
            Box(modifier = Modifier.padding(p)) {
                Column {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Check Wallet Balance")
                        Switch(
                            checked = isSwitchOn,
                            onCheckedChange = { isSwitchOn = it }
                        )
                    }


                    // Text is only shown when the switch is ON
                    if (isSwitchOn) {
                        Text(
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                            text = "500.00",
                        )
                    }
                    Button(modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                        .fillMaxWidth(), onClick = {
                        navController.navigate(Screens.Transaction.route)

//                        navController.navigate(Screens.Home.route)
                    }

                    ) {
                        Text("Send Money")
                    }
                    Button(modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                        .fillMaxWidth(), onClick = {
                        navController.navigate(Screens.TransactionHistory.route)

                    }

                    ) {
                        Text("View Transaction")
                    }
                }
            }
        }
    )
}