package com.example.sportresults.feature_sport_activity.presentation.sport_activity_main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sportresults.core.presentation.ui.theme.Silver
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity

@Composable
fun SportActivityItem(
    sportActivity: SportActivity
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Divider(color = Silver)
            Text(text = sportActivity.sportType.name)
            Divider(color = Silver)
        }
    }
}