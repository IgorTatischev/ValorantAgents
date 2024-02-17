package com.agents.main.presentation.core

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.agents.main.presentation.screens.NavGraphs
import com.agents.main.presentation.screens.appCurrentDestinationAsState
import com.agents.main.presentation.screens.startAppDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {

    val context = LocalContext.current

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            DestinationsNavHost(navController = navController, navGraph = NavGraphs.root)
        }
    }

    DisposableEffect(key1 = Unit) {
        val activity = context as Activity
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        onDispose {
            activity.requestedOrientation = originalOrientation
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {

    NavigationBar {
        val currentDestination = navController.appCurrentDestinationAsState().value?.route
            ?: NavGraphs.root.startAppDestination.route

        BottomBarDestination.values().forEach { destination ->
            AddItem(
                destination = destination,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    destination: BottomBarDestination,
    currentDestination: String,
    navController: NavHostController,
) {

    NavigationBarItem(
        selected = currentDestination.contains(destination.direction.startRoute.route),
        onClick = {
            navController.navigate(destination.direction) {
                launchSingleTop = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = destination.icon),
                contentDescription = null
            )
        },
        label = { Text(text = stringResource(id = destination.label)) },
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = MaterialTheme.colorScheme.primary,
            unselectedTextColor = MaterialTheme.colorScheme.primary,
            indicatorColor = MaterialTheme.colorScheme.primary
        )
    )
}