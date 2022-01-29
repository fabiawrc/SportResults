package com.example.sportresults.feature_splash.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.sportresults.R
import com.example.sportresults.core.presentation.ui.theme.SpaceSmall
import com.example.sportresults.core.util.UiEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

@Composable
fun SplashScreen(
    onPopBackStack: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    viewModel: SplashViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    delay(2000L)
                    onPopBackStack()
                    onNavigate(event.route)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Loader()
            Spacer(modifier = Modifier.height(SpaceSmall))
            Text(text = "RK", style = MaterialTheme.typography.h2)
            Text(text = "SportResults", style = MaterialTheme.typography.h2)
        }

    }

}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.running3))
    val progress by animateLottieCompositionAsState(
        composition,
        restartOnPlay = true,
        iterations = 10,
        isPlaying = true
    )
    LottieAnimation(
        composition,
        progress,
        modifier = Modifier.size(100.dp)
    )
}