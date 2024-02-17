package com.agents.main.presentation.screens.agents_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.agents.main.R
import com.agents.main.domain.model.agents.Agent
import com.agents.main.presentation.components.AgentItem
import com.agents.main.presentation.components.ErrorScreen
import com.agents.main.presentation.components.LoadingScreen
import com.agents.main.presentation.core.AgentsNavGraph
import com.agents.main.presentation.screens.destinations.AgentInfoScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@AgentsNavGraph(start = true)
@Destination
@Composable
fun AgentsScreen(
    viewModel: AgentsScreenViewModel = koinViewModel(),
    navigator: DestinationsNavigator,
) {

    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val state by viewModel.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.dispatch(event = AgentsScreenEvent.LoadAgents)
    }

    viewModel.collectSideEffect {
        when (it) {
            is AgentsScreenSideEffect.NavigateToAgentInfo -> {
                navigator.navigate(AgentInfoScreenDestination(agentId = it.id))
            }

            is AgentsScreenSideEffect.ShowSnackBar -> {
                val result = snackbarHostState.showSnackbar(
                    message = context.resources.getString(R.string.tryAgain),
                    actionLabel = context.resources.getString(R.string.refresh)
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        viewModel.dispatch(AgentsScreenEvent.LoadAgents)
                    }

                    SnackbarResult.Dismissed -> {}
                }
            }
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { paddingValues ->
        when (state) {

            is AgentsScreenState.Loading -> LoadingScreen()

            is AgentsScreenState.Error -> ErrorScreen(error = (state as AgentsScreenState.Error).message)

            is AgentsScreenState.Success -> AgentsLazyColumn(
                agents = (state as AgentsScreenState.Success).agents,
                paddingValues = paddingValues
            ) { viewModel.dispatch(AgentsScreenEvent.AgentClick(it)) }
        }
    }
}

@Composable
fun AgentsLazyColumn(
    agents: List<Agent>,
    paddingValues: PaddingValues,
    onClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items = agents) { agent ->
                AgentItem(item = agent) { onClick(agent.uuid) }
            }
        }
    }
}