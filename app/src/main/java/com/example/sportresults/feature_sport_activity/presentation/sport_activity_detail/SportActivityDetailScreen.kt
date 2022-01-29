package com.example.sportresults.feature_sport_activity.presentation.sport_activity_detail

import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.sportresults.R
import com.example.sportresults.core.presentation.components.StandardAlertDialog
import com.example.sportresults.core.presentation.components.StandardDropDown
import com.example.sportresults.core.presentation.components.StandardTimePicker
import com.example.sportresults.core.presentation.components.StandardToolbar
import com.example.sportresults.core.presentation.ui.theme.*
import com.example.sportresults.core.util.UiEvent
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SportActivityDetailScreen(
    navController: NavController,
    onPopBackStack: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    viewModel: SportActivityDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        scope.launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEvent.ShowSnackbar -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(message = event.message)
                        }
                    }
                    is UiEvent.Navigate -> {
                        onNavigate(event.route)
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            StandardToolbar(
                modifier = Modifier.fillMaxWidth(),
                onNavigateUp = onPopBackStack,
                showBackArrow = true,
                navActions = {
                    TextButton(
                        onClick = {
                            viewModel.onEvent(SportActivityDetailEvent.ClickSave)
                        },
                        enabled = state.canSave
                    ) {
                        Text(text = stringResource(id = R.string.save))
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
                Column {
                    StandardDropDown(
                        label = stringResource(id = R.string.sport),
                        text = stringResource(id = R.string.sporttype_select),
                        items = state.sportTypes.map { SportType.toDropDownContentData(it) },
                        onItemClick = {
                            viewModel.onEvent(SportActivityDetailEvent.SetSportType(it.value))
                        }
                    )
                    Spacer(modifier = Modifier.height(SpaceLarge))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.onEvent(SportActivityDetailEvent.SetShowDurationDialog) },
                    ) {
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
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = state.sportActivity?.hours.toString(),
                                style = MaterialTheme.typography.h3
                            )
                            Spacer(modifier = Modifier.width(SpaceLittle))
                            Text(text = "h", color = MaterialTheme.colors.primary)
                            Spacer(modifier = Modifier.width(SpaceLittle))

                            Text(
                                text = state.sportActivity?.minutes.toString(),
                                style = MaterialTheme.typography.h3
                            )
                            Spacer(modifier = Modifier.width(SpaceLittle))
                            Text(text = "min", color = MaterialTheme.colors.primary)
                            Spacer(modifier = Modifier.width(SpaceLittle))

                            Text(
                                text = state.sportActivity?.seconds.toString(),
                                style = MaterialTheme.typography.h3
                            )
                            Spacer(modifier = Modifier.width(SpaceLittle))
                            Text(text = "s", color = MaterialTheme.colors.primary)
                        }
                        Spacer(modifier = Modifier.height(SpaceLittle))
                        Divider(
                            color = Silver,
                            modifier = Modifier.padding(horizontal = SpaceMedium)
                        )
                    }

                    Spacer(modifier = Modifier.height(SpaceLarge))
                    StandardDropDown(
                        label = stringResource(id = R.string.storage),
                        text = stringResource(id = R.string.storagetype_select),
                        items = state.storageTypes.map { StorageType.toDropDownContentData(it) },
                        onItemClick = {
                            viewModel.onEvent(SportActivityDetailEvent.SetStorageType(it.value))
                        }
                    )
                }
                Spacer(modifier = Modifier
                    .height(SpaceLarge)
                    .weight(1f))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { }, modifier = Modifier
                            .fillMaxWidth()
                            .height(MainButtonHeight),
                        enabled = state.canSave
                    ) {
                        Text(
                            text = stringResource(id = R.string.save),
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(SpaceMedium))


                StandardAlertDialog(
                    state.showDurationDialog,
                    onDismiss = { viewModel.onEvent(SportActivityDetailEvent.SetShowDurationDialog) },
                    onConfirm = { viewModel.onEvent(SportActivityDetailEvent.SetShowDurationDialog) },
                    content = {
                        StandardTimePicker(
                            hours = state.sportActivity?.hours ?: 0,
                            minutes = state.sportActivity?.minutes ?: 0,
                            seconds = state.sportActivity?.seconds ?: 0,
                            onHoursSet = {
                                viewModel.onEvent(
                                    SportActivityDetailEvent.SetHours(
                                        it
                                    )
                                )
                            },
                            onMinutesSet = {
                                viewModel.onEvent(
                                    SportActivityDetailEvent.SetMinutes(
                                        it
                                    )
                                )
                            },
                            onSecondsSet = {
                                viewModel.onEvent(
                                    SportActivityDetailEvent.SetSeconds(
                                        it
                                    )
                                )
                            }
                        )
                    }
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
