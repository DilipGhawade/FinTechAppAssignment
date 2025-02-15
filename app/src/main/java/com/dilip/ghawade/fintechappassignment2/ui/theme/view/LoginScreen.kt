package com.dilip.ghawade.fintechappassignment2.ui.theme.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dilip.ghawade.fintechappassignment2.navigation.Screens
import com.dilip.ghawade.fintechappassignment2.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun LoginScreen(navController: NavHostController, viewModel: AuthViewModel) {
    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(loginState) {
        if (loginState.isNotEmpty()) {
            Log.d("LoginScreen", "Showing Toast: $loginState") // Debugging
            Toast.makeText(context, loginState, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Login") }
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        value = viewModel.username,
                        onValueChange = { viewModel.onUsernameChange(it) },
                        label = { Text("Username") }
                    )

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        value = viewModel.password,
                        onValueChange = { viewModel.onPasswordChange(it) },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Button(modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                        .fillMaxWidth(), onClick = {
                        viewModel.loginUser(
                            viewModel.username,
                            viewModel.password
                        )
                        navController.navigate(Screens.Home.route)
                    }

                    ) {
                        Text("Login")
                    }
                    Text(
                        "Yet Not Register!! click here to register",
                        modifier = Modifier.clickable {
                            navController.navigate(Screens.Registration.route)

                        },
                        color = Color.Blue
                    )


                }

            }
        }
    )

}