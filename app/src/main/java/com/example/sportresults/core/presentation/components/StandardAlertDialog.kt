package com.example.sportresults.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.sportresults.R
import com.example.sportresults.core.presentation.ui.theme.SpaceSmall

@Composable
fun StandardAlertDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onConfirm)
                { Text(text = stringResource(id = R.string.ok)) }
            },
            text = {
                Column(modifier = Modifier.padding(SpaceSmall)) {
                    content()
                }
            }
        )
    }
}