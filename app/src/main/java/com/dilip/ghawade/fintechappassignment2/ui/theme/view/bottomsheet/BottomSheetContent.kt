package com.dilip.ghawade.fintechappassignment2.ui.theme.view.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    txnAmount:String="00.00",
    message:String ="Success",
    onDismiss: () -> Unit) {
    // Content of the bottom sheet
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$txnAmount the transaction is $message",
            style = MaterialTheme.typography.titleLarge
        )

        // Button to close the bottom sheet
        Button(onClick = onDismiss) {
            Text(text = "Close")
        }
    }
}
