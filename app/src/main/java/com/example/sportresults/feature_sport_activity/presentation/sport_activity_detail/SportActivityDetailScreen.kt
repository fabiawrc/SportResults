package com.example.sportresults.feature_sport_activity.presentation.sport_activity_detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sportresults.R
import com.example.sportresults.core.presentation.components.StandardDropDown
import com.example.sportresults.core.presentation.components.StandardToolbar
import com.example.sportresults.core.presentation.ui.theme.MainButtonHeight
import com.example.sportresults.core.presentation.ui.theme.SpaceLarge
import com.example.sportresults.core.presentation.ui.theme.SpaceMedium
import com.example.sportresults.core.presentation.ui.theme.SpaceSmall
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType

@Composable
fun SportActivityDetailScreen(
    navController: NavController,
    onPopBackStack: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    viewModel: SportActivityDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            StandardToolbar(
                modifier = Modifier.fillMaxWidth(),
                onNavigateUp = onPopBackStack,
                showBackArrow = true,
                navActions = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.new_sport_activity),
                        style = MaterialTheme.typography.h2
                    )
                }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(SpaceLarge)
            ) {
                Column() {
                    StandardDropDown(
                        label = stringResource(id = R.string.sport),
                        text = stringResource(id = R.string.sporttype_select),
                        items = state.sportTypes.map { SportType.toDropDownContentData(it) },
                        onItemClick = {
                            viewModel.onEvent(SportActivityDetailEvent.SetSportType(it.value))
                        }
                    )

                    Spacer(modifier = Modifier.height(SpaceSmall))

                    StandardDropDown(
                        label = stringResource(id = R.string.storage),
                        text = stringResource(id = R.string.storagetype_select),
                        items = state.storageTypes.map { StorageType.toDropDownContentData(it) },
                        onItemClick = {
                            viewModel.onEvent(SportActivityDetailEvent.SetStorageType(it.value))
                        }
                    )
                }
                Spacer(modifier = Modifier.height(SpaceSmall))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { }, modifier = Modifier
                            .fillMaxWidth()
                            .height(MainButtonHeight)
                    ) {
                        Text(
                            text = stringResource(id = R.string.save),
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(SpaceMedium))
            }
        }
    }
}
