package com.example.sportresults.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sportresults.core.presentation.ui.theme.Silver
import com.example.sportresults.core.presentation.ui.theme.SpaceMedium
import com.example.sportresults.core.presentation.ui.theme.SpaceSmall

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StandardTextField(
    text: String,
    label: String,
    placeholder: String = "",
    postfix: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLength: Int = 50,
    onValueChanged: (String) -> Unit = {},
    onDone: (String) -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .padding(horizontal = SpaceMedium)
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.primary
            )
        }
        Spacer(modifier = Modifier.height(SpaceSmall))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                Row(horizontalArrangement = Arrangement.Center) {
                    BasicTextField(
                        modifier = Modifier.padding(end = 0.dp),
                        value = text,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = keyboardType,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            keyboardController?.hide()
                            onDone(text)
                        }),
                        onValueChange = {
                            if (it.length <= maxLength)
                                onValueChanged(it)
                        },
                        textStyle = MaterialTheme.typography.body1.copy(textAlign = TextAlign.Center)
                    )
                    if (!postfix.isNullOrBlank()) {
                        Text(
                            text = postfix,
                            color = if (text.isNullOrBlank()) Silver else MaterialTheme.colors.primary
                        )
                    }
                }
                if (text.isNullOrBlank())
                    Text(text = placeholder, style = MaterialTheme.typography.body1, color = Silver)
            }
        }
        Divider(
            color = Silver,
        )
    }
}
