package com.example.sportresults.feature_sport_activity.presentation.sport_activity_main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddRoad
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sportresults.core.presentation.ui.theme.IconSizeSmall
import com.example.sportresults.core.presentation.ui.theme.Silver
import com.example.sportresults.core.presentation.ui.theme.SpaceLittle
import com.example.sportresults.core.presentation.ui.theme.SpaceSmall
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import java.util.*

@Composable
fun SportActivityItem(
    sportActivity: SportActivity,
    onClick: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }) {
            Spacer(modifier = Modifier.height(SpaceSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = sportActivity.formattedTime,
                    style = MaterialTheme.typography.caption
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    sportActivity.storageType?.let { storageType ->
                        Icon(
                            painter = painterResource(id = storageType.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSizeSmall),
                            tint = storageType.color
                        )
                        Text(
                            text = storageType.name,
                            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                            color = storageType.color
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.weight(0.4f), horizontalArrangement = Arrangement.Start) {
                    sportActivity.sportType?.let { sportType ->
                        Icon(
                            painter = painterResource(id = sportType.icon),
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary
                        )
                        Spacer(modifier = Modifier.width(SpaceSmall))
                        Text(text = sportType.name)
                    }
                }

                if (sportActivity.sportType?.hasDistance == true) {
                    Row(modifier = Modifier.weight(0.3f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        Icon(
                            imageVector = Icons.Default.AddRoad,
                            contentDescription = null,
                            modifier = Modifier.size(IconSizeSmall),
                            tint = Silver
                        )
                        Spacer(modifier = Modifier.width(SpaceSmall))
                        Text(
                            text = "${sportActivity.distance.toString()} km",
                            style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colors.primary
                        )
                    }
                }

                Row(modifier = Modifier.weight(0.3f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        modifier = Modifier.size(IconSizeSmall),
                        tint = Silver
                    )
                    Spacer(modifier = Modifier.width(SpaceSmall))
                    Text(
                        text = sportActivity.getFormatedDuration(),
                        style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colors.primary
                    )
                }

            }
            Spacer(modifier = Modifier.height(SpaceSmall))
            Divider(color = Silver)
        }
    }
}