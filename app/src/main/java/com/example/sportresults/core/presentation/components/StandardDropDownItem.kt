package com.example.sportresults.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.sportresults.core.domain.model.DropDownContentData
import com.example.sportresults.core.presentation.ui.theme.SpaceMedium
import com.example.sportresults.core.presentation.ui.theme.SpaceSmall

@Composable
fun StandardDropDownItem(
    dropDownContentData: DropDownContentData
) {
    Row(
        modifier = Modifier.padding(horizontal = SpaceMedium, vertical = SpaceSmall)
    ) {
        dropDownContentData.icon?.let { icon ->
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
        }
        Spacer(modifier = Modifier.width(SpaceSmall))
        Text(text = dropDownContentData.text)
    }
}