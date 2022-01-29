package com.example.sportresults.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.sportresults.R
import com.example.sportresults.core.presentation.ui.theme.Silver
import com.example.sportresults.core.presentation.ui.theme.SpaceLittle
import com.example.sportresults.core.presentation.ui.theme.SpaceMedium

@Composable
fun StandardTimePicker(
    hours: Int = 0,
    minutes: Int = 0,
    seconds: Int = 0,
    onHoursSet: (Int) -> Unit = {},
    onMinutesSet: (Int) -> Unit = {},
    onSecondsSet: (Int) -> Unit = {},
) {
    var hoursState by rememberSaveable() { mutableStateOf(hours) }
    var minutesState by rememberSaveable() { mutableStateOf(minutes) }
    var secondsState by rememberSaveable() { mutableStateOf(seconds) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SpaceMedium),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.duration),
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.primary
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            NumberPicker(
                value = hoursState,
                onValueChange = {
                    hoursState = it
                    onHoursSet(it)
                },
                dividersColor = Silver,
                range = 0..99
            )
            Spacer(modifier = Modifier.width(SpaceLittle))
            Text(text = "h", color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.width(SpaceLittle))
            NumberPicker(
                value = minutesState,
                onValueChange = {
                    minutesState = it
                    onMinutesSet(it)
                },
                dividersColor = Silver,
                range = 0..59
            )
            Spacer(modifier = Modifier.width(SpaceLittle))
            Text(text = "min", color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.width(SpaceLittle))
            NumberPicker(
                value = secondsState,
                onValueChange = {
                    secondsState = it
                    onSecondsSet(it)
                },
                dividersColor = Silver,
                range = 0..59
            )
            Spacer(modifier = Modifier.width(SpaceLittle))
            Text(text = "s", color = MaterialTheme.colors.primary)
        }
    }
}