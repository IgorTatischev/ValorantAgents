package com.agents.main.presentation.screens.agents_screen

import com.agents.main.domain.model.agents.Agent

sealed class AgentsScreenState {
    object Loading : AgentsScreenState()

    data class Success(
        val agents: List<Agent> = emptyList()
    ) : AgentsScreenState()

    class Error(val message: String) : AgentsScreenState()
}

sealed interface AgentsScreenSideEffect {
    object ShowSnackBar : AgentsScreenSideEffect
    data class NavigateToAgentInfo(val id: String): AgentsScreenSideEffect
}

sealed interface AgentsScreenEvent {
    object LoadAgents: AgentsScreenEvent
    data class AgentClick(val id: String): AgentsScreenEvent
}