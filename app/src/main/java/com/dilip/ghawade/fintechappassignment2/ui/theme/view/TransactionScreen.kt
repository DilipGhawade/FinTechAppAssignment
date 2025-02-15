package com.dilip.ghawade.fintechappassignment2.ui.theme.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dilip.ghawade.fintechappassignment2.ui.theme.view.bottomsheet.BottomSheetContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun TransactionScreen(navController: NavHostController) {
    var amount by remember { mutableStateOf("") }
    // Bottom sheet state
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    // Coroutine scope for animating the sheet
    val coroutineScope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transaction Screen 3") },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    value = amount,
                    onValueChange = {
                        amount = it
                    },
                    label = { Text("Enter Amount") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                    )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    onClick = {
                        showBottomSheet = true
                        coroutineScope.launch { sheetState.show() }
                    }) { Text("Submit") }
                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            coroutineScope.launch {
                                sheetState.hide()
                            }
                        },
                        sheetState = sheetState
                    ) {
                        BottomSheetContent(
                            txnAmount = amount,
                            message = "Success"
                        ) {
                            showBottomSheet = false
                            coroutineScope.launch { sheetState.hide() }
                        }
                    }
                }

            }
        }
    }
}