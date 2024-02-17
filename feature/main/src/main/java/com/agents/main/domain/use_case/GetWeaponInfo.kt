package com.agents.main.domain.use_case

import com.agents.main.data.util.Resource
import com.agents.main.domain.model.weapons.WeaponInfo
import com.agents.main.domain.repository.ValorantApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetWeaponInfo(private val repository: ValorantApiRepository)  {

    operator fun invoke(id: String): Flow<Resource<WeaponInfo>> = flow {
        emit(repository.getWeaponInfo(id))
    }.flowOn(Dispatchers.IO)
}