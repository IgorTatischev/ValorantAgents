package com.agents.main.presentation.screens.weapon_info_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import com.agents.main.R
import com.agents.main.presentation.components.CustomSnackbar
import com.agents.main.presentation.components.ErrorScreen
import com.agents.main.presentation.components.InfoTopBar
import com.agents.main.presentation.components.LoadingScreen
import com.agents.main.presentation.core.WeaponsNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@OptIn(ExperimentalMaterial3Api::class)
@WeaponsNavGraph
@Destination
@Composable
fun WeaponInfoScreen(
    viewModel: WeaponInfoViewModel = koinViewModel(),
    navigator: DestinationsNavigator,
    weaponId: String,
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.dispatch(WeaponInfoScreenEvent.LoadInfo)
    }

    viewModel.collectSideEffect {
        when (it) {
            is WeaponInfoScreenSideEffect.PopBack -> {
                navigator.popBackStack()
            }

            is WeaponInfoScreenSideEffect.ShowSnackBar -> {
                val result = snackbarHostState.showSnackbar(
                    message = context.resources.getString(R.string.tryAgain),
                    actionLabel = context.resources.getString(R.string.refresh)
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.dispatch(WeaponInfoScreenEvent.LoadInfo)
                    }

                    SnackbarResult.Dismissed -> {}
                }
            }
        }
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) { CustomSnackbar(snackbarData = it) } },
        topBar = {
            InfoTopBar(
                onClick = { viewModel.dispatch(event = WeaponInfoScreenEvent.PopBack) },
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValues ->

        when (state) {

            is WeaponInfoScreenState.Loading -> LoadingScreen()

            is WeaponInfoScreenState.Error -> ErrorScreen(error = (state as WeaponInfoScreenState.Error).message)

            is WeaponInfoScreenState.Success -> (state as WeaponInfoScreenState.Success).weapon?.let {
                WeaponInfo(weapon = it, paddingValues = paddingValues)
            }
        }
    }
}

