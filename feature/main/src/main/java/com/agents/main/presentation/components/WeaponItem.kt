package com.agents.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.agents.main.domain.model.weapons.Weapon

@Composable
fun WeaponItem(item: Weapon, onClick: () -> Unit) {
    AnimatedBorderCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onClick = onClick,
        gradient = Brush.sweepGradient(
            listOf(
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.colorScheme.onPrimary,
                MaterialTheme.colorScheme.primaryContainer,
            )
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.background,
                        )
                    )
                )
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 15.dp),
                text = item.displayName,
                style = MaterialTheme.typography.headlineSmall
            )

            TintImage(
                modifier = Modifier
                    .weight(3f)
                    .size(60.dp)
                    .padding(end = 15.dp),
                imageUri = item.killStreamIcon,
                tint = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}