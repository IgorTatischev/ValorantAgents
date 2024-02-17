package com.agents.main.presentation.screens.agent_info_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.agents.main.data.util.Resource
import com.agents.main.domain.use_case.GetAgentInfo
import com.agents.main.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class AgentInfoViewModel(private val getAgentInfoUseCase: GetAgentInfo, savedStateHandle: SavedStateHandle) :
    BaseViewModel<AgentInfoScreenState, AgentInfoScreenSideEffect, AgentInfoScreenEvent>(
        initialState = AgentInfoScreenState.Loading
    ) {

     private var agentId = ""

     init {
         savedStateHandle.get<String>("agentId")?.let { agentId = it}
     }

    override fun dispatch(event: AgentInfoScreenEvent) {
        when(event){
            is AgentInfoScreenEvent.PopBack -> post(AgentInfoScreenSideEffect.PopBack)
            is AgentInfoScreenEvent.LoadInfo -> getAgentInfo(agentId)
        }
    }

    private fun getAgentInfo(id: String) {
        getAgentInfoUseCase(id).onEach { result ->
            when(result){
                is Resource.Error -> {
                    intent { reduce { AgentInfoScreenState.Error(message = result.message.toString()) } }
                    post(AgentInfoScreenSideEffect.ShowSnackBar)
                }
                is Resource.Success -> {
                    intent { reduce { AgentInfoScreenState.Success(agent = result.data?.agent) } }
                }
            }
        }.launchIn(viewModelScope)
    }
}