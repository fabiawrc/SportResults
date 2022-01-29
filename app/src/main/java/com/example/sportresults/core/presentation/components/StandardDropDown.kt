package com.example.sportresults.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.RequestQuote
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.sportresults.R
import com.example.sportresults.core.domain.model.DropDownContentData
import com.example.sportresults.core.presentation.ui.theme.*

@Composable
fun StandardDropDown(
    text: String,
    label: String,
    items: List<DropDownContentData> = emptyList(),
    onItemClick: (DropDownContentData) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by rememberSaveable { mutableStateOf(text) }

    var selectedItem = remember {
        mutableStateOf<DropDownContentData?>(null)
    }
    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        painterResource(id = R.drawable.chevron_up)
    else
        painterResource(id = R.drawable.chevron_down)

    Column(modifier = Modifier
        .clickable {
            expanded = !expanded
        }
        .padding(horizontal = SpaceMedium)) {

        Spacer(modifier = Modifier.height(SpaceSmall))
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
        Row(modifier = Modifier
            .onGloballyPositioned { coordinates ->
                //This value is used to assign to the DropDown the same width
                textfieldSize = coordinates.size.toSize()
            },
        )
        {
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
                selectedItem.value?.icon?.let {
                    Icon(painter = painterResource(id = it), contentDescription = null, tint = MaterialTheme.colors.primary)
                }
                Spacer(modifier = Modifier.width(SpaceSmall))
                selectedItem.value?.let {
                    Text(text = selectedText)
                } ?: Text(text = text, color = Silver)
            }

            Icon(painter = icon, contentDescription = null, tint = Silver)
        }
        Spacer(modifier = Modifier.height(SpaceLittle))
        Divider(color = Silver)

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