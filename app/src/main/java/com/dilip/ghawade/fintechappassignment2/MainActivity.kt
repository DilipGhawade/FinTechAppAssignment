package com.dilip.ghawade.fintechappassignment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.dilip.ghawade.fintechappassignment2.navigation.AppNavigation
import com.dilip.ghawade.fintechappassignment2.ui.theme.FinTechAppAssignment2Theme
import com.dilip.ghawade.fintechappassignment2.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinTechAppAssignment2Theme {
//                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//                val viewModel: AuthViewModel = viewModel()
                val viewModel: AuthViewModel = hiltViewModel()
//                val isLoggedIn by viewModel.isLoggedIn.collectAsState(initial = false)
//                if (isLoggedIn) {
//                    AppNavigation(rememberNavController(),viewModel)
//                }
                AppNavigation(rememberNavController(),viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinTechAppAssignment2Theme {
        Greeting("Android")
    }
}