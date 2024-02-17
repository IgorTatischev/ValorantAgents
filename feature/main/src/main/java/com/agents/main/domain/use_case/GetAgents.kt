package com.agents.main.domain.use_case

import com.agents.main.data.util.Resource
import com.agents.main.domain.model.agents.Agents
import com.agents.main.domain.repository.ValorantApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAgents(private val repository: ValorantApiRepository)  {

    operator fun invoke(): Flow<Resource<Agents>> = flow {
        emit(repository.getAgents())
    }.flowOn(Dispatchers.IO)
}