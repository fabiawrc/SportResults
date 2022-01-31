package com.example.sportresults.feature_sport_activity.presentation.sport_activity_main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
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
import com.example.sportresults.R
import com.example.sportresults.core.presentation.components.Chip
import com.example.sportresults.core.presentation.ui.theme.*
import com.example.sportresults.core.util.Screen
import com.example.sportresults.core.util.UiEvent
import com.example.sportresults.core.util.asString
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SportActivityMainScreen(
    navController: NavController,
    onPopBackStack: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    viewModel: SportActivityMainViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        scope.launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is UiEvent.ShowSnackbar -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(message = event.uiText.asString(context))
                        }
                    }
                    is UiEvent.Navigate -> {
                        onNavigate(event.route)
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceLarge)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = stringResource(id = R.string.activity_list),
                    style = MaterialTheme.typography.h2,
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    mainAxisAlignment = MainAxisAlignment.Center,
                    mainAxisSpacing = SpaceSmall,
                    crossAxisSpacing = SpaceSmall
                ) {
                    state.storageTypes.forEach { item ->
                        Chip(
                            text = item.name,
                            selected = state.selectedStorageType == item,
                            onChipClick = {
                                viewModel.onEvent(SportActivityMainEvent.StorageTypeChange(item))
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(SpaceMedium))

            if (state.sportActivities.count() > 0) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(state.sportActivities.size) { i ->
                        val sportActivity = state.sportActivities[i]
                        SportActivityItem(
                            sportActivity = sportActivity
                        )
                    }
                }
            } else {
                Text(
                    text = "",
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterHorizontally)
                )
            }


            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        onNavigate(Screen.SportActivityDetailScreen.route)
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .height(MainButtonHeight)
                ) {
                    Text(
                        text = stringResource(id = R.string.new_activity),
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

            Spacer(modifier = Modifier.height(SpaceMedium))
        }

        if(state.sportActivities.count() <= 0){
            Text(
                text = stringResource(id = R.string.emptylist),
                color = Silver,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .padding(bottom = SnackBarBottomPadding)
                .align(Alignment.BottomCenter)
        )
    }
}