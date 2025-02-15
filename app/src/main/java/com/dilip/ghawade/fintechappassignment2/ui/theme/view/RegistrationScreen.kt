package com.dilip.ghawade.fintechappassignment2.ui.theme.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dilip.ghawade.fintechappassignment2.model.User
import com.dilip.ghawade.fintechappassignment2.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavHostController) {
    val viewModel: AuthViewModel = hiltViewModel()
    val context = LocalContext.current
    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        if (loginState.isNotEmpty()) {
            Log.d("LoginScreen", "Showing Toast: $loginState") // Debugging
            Toast.makeText(context, loginState, Toast.LENGTH_SHORT).show()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Registration") })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = viewModel.username,
                    onValueChange = { viewModel.onUsernameChange(it) },
                    label = { Text("Username") })
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = viewModel.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = viewModel.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text("Email") })
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = viewModel.mobileNo,
                    onValueChange = { viewModel.onMobileNoChange(it) },
                    label = { Text("Mobile No") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    onClick = {
                        viewModel.registerUser(
                            user = User(
                                username = viewModel.username,
                                password = viewModel.password,
                                email = viewModel.email,
                                mobileNo = viewModel.mobileNo
                            )

                        )
                        Log.d("RegistrationScreen", "RegistrationScreen: ${loginState}")
                        navController.popBackStack()
                    }) { Text("Register") }

            }

        }
    )
}