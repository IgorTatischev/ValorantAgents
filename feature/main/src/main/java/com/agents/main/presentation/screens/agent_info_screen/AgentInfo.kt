package com.agents.main.presentation.screens.agent_info_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.agents.main.domain.model.agents.Agent
import com.agents.main.presentation.components.AbilityItem
import com.agents.main.presentation.components.TintImage
import com.agents.main.presentation.util.fromHex

@Composable
fun AgentInfo(agent: Agent, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(15.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            TintImage(
                modifier = Modifier.size(400.dp),
                imageUri = agent.background,
                tint = MaterialTheme.colorScheme.primaryContainer
            )
            SubcomposeAsyncImage(
                modifier = Modifier.size(400.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(agent.fullPortrait)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }

        Text(
            text = agent.displayName,
            style = MaterialTheme.typography.headlineLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TintImage(
                modifier = Modifier.size(20.dp),
                imageUri = agent.role.displayIcon,
                tint = MaterialTheme.colorScheme.primaryContainer
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = agent.role.displayName, style = MaterialTheme.typography.headlineSmall)
        }

        Text(text = agent.description, style = MaterialTheme.typography.bodyLarge)

        val colorList = remember { mutableStateListOf<Color>() }
        agent.backgroundGradientColors.forEach {
            val color = Color.fromHex(it.take(6))
            colorList.add(color)
        }

        agent.abilities.forEach { ability ->
            AbilityItem(item = ability, colorList)
        }
    }
}