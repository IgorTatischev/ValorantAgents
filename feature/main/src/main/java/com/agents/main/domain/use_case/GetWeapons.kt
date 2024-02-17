package com.agents.main.domain.use_case

import com.agents.main.data.util.Resource
import com.agents.main.domain.model.weapons.Weapons
import com.agents.main.domain.repository.ValorantApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetWeapons(private val repository: ValorantApiRepository)  {

    operator fun invoke(): Flow<Resource<Weapons>> = flow {
        emit(repository.getWeapons())
    }.flowOn(Dispatchers.IO)
}