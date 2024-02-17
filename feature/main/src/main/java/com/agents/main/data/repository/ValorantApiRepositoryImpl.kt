package com.agents.main.data.repository

import com.agents.main.data.source.ValorantApi
import com.agents.main.data.util.Resource
import com.agents.main.data.util.safeApiCall
import com.agents.main.domain.model.agents.AgentInfo
import com.agents.main.domain.model.agents.Agents
import com.agents.main.domain.model.weapons.WeaponInfo
import com.agents.main.domain.model.weapons.Weapons
import com.agents.main.domain.repository.ValorantApiRepository


class ValorantApiRepositoryImpl(private val api: ValorantApi): ValorantApiRepository {

    override suspend fun getAgents(): Resource<Agents> = safeApiCall { api.getAgents() }

    override suspend fun getAgentsInfo(id: String): Resource<AgentInfo> = safeApiCall { api.getAgentInfo(id = id) }

    override suspend fun getWeapons(): Resource<Weapons> = safeApiCall { api.getWeapons() }

    override suspend fun getWeaponInfo(id: String): Resource<WeaponInfo> = safeApiCall { api.getWeaponInfo(id = id) }
}