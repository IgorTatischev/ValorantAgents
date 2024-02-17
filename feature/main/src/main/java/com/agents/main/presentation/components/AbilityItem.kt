package com.agents.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.agents.main.domain.model.agents.Ability

@Composable
fun AbilityItem(item: Ability) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.primaryContainer,
                        )
                    )
                )
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                item.displayIcon?.let {
                    TintImage(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 15.dp),
                        imageUri = it,
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )
                }
                Text(
                    text = item.displayName,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            Text(
                text = item.slot,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}