package com.agents.main.presentation.screens.agent_info_screen

import com.agents.main.domain.model.agents.Agent

sealed class AgentInfoScreenState {
    object Loading : AgentInfoScreenState()
    data class Success(val agent: Agent?) : AgentInfoScreenState()
    class Error(val message: String) : AgentInfoScreenState()
}

sealed interface AgentInfoScreenSideEffect {
    object ShowSnackBar : AgentInfoScreenSideEffect
    object PopBack : AgentInfoScreenSideEffect
}

sealed interface AgentInfoScreenEvent {
    object LoadInfo: AgentInfoScreenEvent
    object PopBack : AgentInfoScreenEvent
}