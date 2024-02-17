package com.agents.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.agents.main.domain.model.agents.Agent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentItem(item: Agent, onClick: () -> Unit) {

    val colorList = remember { mutableStateListOf<Color>()}
    item.backgroundGradientColors.forEach {
        val color = Color.fromHex(it.take(6))
        colorList.add(color)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = Brush.linearGradient(colors = colorList))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            AgentImage(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 5.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                imageUri = item.displayIcon
            )

            Text(
                modifier = Modifier
                    .weight(3f),
                text = item.displayName,
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )

            TintImage(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 5.dp),
                imageUri = item.role.displayIcon,
                tint = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}

@Composable
fun AgentImage(modifier: Modifier = Modifier, imageUri: String){
    SubcomposeAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUri)
            .crossfade(true)
            .build(),
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}

fun Color.Companion.fromHex(colorString: String) =
    Color(android.graphics.Color.parseColor("#$colorString"))