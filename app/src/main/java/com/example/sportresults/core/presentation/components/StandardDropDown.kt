package com.example.sportresults.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.toSize
import com.example.sportresults.core.domain.model.DropDownContentData
import com.example.sportresults.core.presentation.ui.theme.DarkGray
import com.example.sportresults.core.presentation.ui.theme.Silver

@Composable
fun StandardDropDown(
    text: String,
    label: String,
    items: List<DropDownContentData> = emptyList(),
    onItemClick: (DropDownContentData) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(text) }

    var selectedItem = remember {
        mutableStateOf<DropDownContentData?>(null)
    }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    Column(modifier = Modifier) {
        OutlinedTextField(
            value = selectedText,
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = DarkGray,
                disabledIndicatorColor = Silver,
            ),
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded=!expanded
                }
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(label, color = MaterialTheme.colors.primary) },
            leadingIcon = {
                selectedItem.value?.icon?.let {
                    Icon(
                        painter = painterResource(id = it),
                        tint = MaterialTheme.colors.primary,
                        contentDescription = null
                    )
                }
            },
            trailingIcon = {
                Icon(icon, null)
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            items.forEach { dropDownItem ->
                DropdownMenuItem(onClick = {
                    onItemClick(dropDownItem)
                    selectedItem.value = dropDownItem
                    selectedText = dropDownItem.text
                    expanded = !expanded
                }) {
                    StandardDropDownItem(dropDownContentData = dropDownItem)
                }
            }
        }
    }
}