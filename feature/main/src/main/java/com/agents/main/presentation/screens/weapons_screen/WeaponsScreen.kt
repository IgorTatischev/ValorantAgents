package com.agents.main.presentation.screens.weapons_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.ActionPerformed
import androidx.compose.material3.SnackbarResult.Dismissed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.agents.main.R
import com.agents.main.domain.model.weapons.Weapon
import com.agents.main.presentation.components.CustomSnackbar
import com.agents.main.presentation.components.ErrorScreen
import com.agents.main.presentation.components.LoadingScreen
import com.agents.main.presentation.components.WeaponItem
import com.agents.main.presentation.core.WeaponsNavGraph
import com.agents.main.presentation.screens.destinations.WeaponInfoScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@WeaponsNavGraph(start = true)
@Destination
@Composable
fun WeaponsScreen(viewModel: WeaponsViewModel = koinViewModel(), navigator: DestinationsNavigator) {

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.dispatch(event = WeaponsScreenEvent.LoadWeapons)
    }

    viewModel.collectSideEffect {
        when (it) {
            is WeaponsScreenSideEffect.NavigateToWeaponInfo -> {
                navigator.navigate(WeaponInfoScreenDestination(weaponId = it.id))
            }

            is WeaponsScreenSideEffect.ShowSnackBar -> {
                val result = snackbarHostState.showSnackbar(
                    message = context.resources.getString(R.string.tryAgain),
                    actionLabel = context.resources.getString(R.string.refresh)
                )
                when (result) {
                    ActionPerformed -> {
                        viewModel.dispatch(WeaponsScreenEvent.LoadWeapons)
                    }

                    Dismissed -> {}
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) { CustomSnackbar(snackbarData = it) } },
    ) { paddingValues ->
        when (state) {

            is WeaponsScreenState.Loading -> LoadingScreen()

            is WeaponsScreenState.Error -> ErrorScreen(error = (state as WeaponsScreenState.Error).message)

            is WeaponsScreenState.Success -> WeaponsLazyColumn(
                weapons = (state as WeaponsScreenState.Success).weapons,
                paddingValues = paddingValues
            ) { viewModel.dispatch(WeaponsScreenEvent.WeaponClick(it)) }
        }
    }
}

@Composable
fun WeaponsLazyColumn(
    weapons: List<Weapon>,
    paddingValues: PaddingValues,
    onClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = weapons) { weapon ->
                WeaponItem(item = weapon) { onClick(weapon.uuid) }
            }
        }
    }
}