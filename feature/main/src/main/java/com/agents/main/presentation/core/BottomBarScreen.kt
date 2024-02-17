package com.agents.main.presentation.core

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.agents.main.R
import com.agents.main.presentation.screens.NavGraphs
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.spec.NavGraphSpec

enum class BottomBarDestination(
    val direction: NavGraphSpec,
    @DrawableRes val icon: Int,
    @StringRes val label: Int,
) {
    Agents(NavGraphs.agents, R.drawable.agents , R.string.agents),
    Weapons(NavGraphs.weapons, R.drawable.weapons, R.string.weapons),
}

@RootNavGraph(start = true)
@NavGraph
annotation class AgentsNavGraph(
    val start: Boolean = false,
)

@RootNavGraph
@NavGraph
annotation class WeaponsNavGraph(
    val start: Boolean = false,
)