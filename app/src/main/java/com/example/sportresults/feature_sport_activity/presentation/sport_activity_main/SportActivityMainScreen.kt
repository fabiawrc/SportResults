package com.example.sportresults.feature_sport_activity.presentation.sport_activity_main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sportresults.core.presentation.ui.theme.SpaceSmall
import com.example.sportresults.R
import com.example.sportresults.core.presentation.ui.theme.MainButtonHeight
import com.example.sportresults.core.presentation.ui.theme.SpaceLarge
import com.example.sportresults.core.presentation.ui.theme.SpaceMedium

@Composable
fun SportActivityMainScreen(
    navController: NavController,
    onPopBackStack: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    viewModelSport: SportActivityViewModel = hiltViewModel()
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(SpaceLarge)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.activity_list),
                    style = MaterialTheme.typography.h2
                )
            }


            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = SpaceMedium)
                ) {
                    Text(
                        text = stringResource(id = R.string.new_activity),
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            Spacer(modifier = Modifier.height(SpaceMedium))
        }
    }
}