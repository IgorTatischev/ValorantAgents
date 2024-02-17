package com.agents.main.presentation.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun StatsText(stat: String, @StringRes resId: Int) {
    Text(
        text = stringResource(id = resId, stat),
        style = MaterialTheme.typography.bodyLarge
    )
}