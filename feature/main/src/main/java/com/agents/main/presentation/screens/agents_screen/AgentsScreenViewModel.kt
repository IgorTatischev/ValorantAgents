package com.agents.main.presentation.screens.agents_screen

import androidx.lifecycle.viewModelScope
import com.agents.main.data.util.Resource
import com.agents.main.domain.use_case.GetAgents
import com.agents.main.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class AgentsScreenViewModel(private val getAgentsUseCase: GetAgents) :
    BaseViewModel<AgentsScreenState, AgentsScreenSideEffect, AgentsScreenEvent>(
        initialState = AgentsScreenState.Loading
    ) {

    override fun dispatch(event: AgentsScreenEvent) {
        when (event) {
            is AgentsScreenEvent.AgentClick -> post(AgentsScreenSideEffect.NavigateToAgentInfo(id = event.id))

            is AgentsScreenEvent.LoadAgents -> getAgents()
        }
    }

    private fun getAgents() {
        getAgentsUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    intent { reduce { AgentsScreenState.Error(message = result.message.toString()) } }
                    post(AgentsScreenSideEffect.ShowSnackBar)
                }

                is Resource.Success -> {
                    intent { reduce { AgentsScreenState.Success(agents = result.data?.agents ?: emptyList()) } }
                }
            }
        }.launchIn(viewModelScope)
    }
}