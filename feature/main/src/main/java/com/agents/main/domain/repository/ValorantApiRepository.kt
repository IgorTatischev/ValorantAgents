package com.agents.main.domain.repository

import com.agents.main.data.util.Resource
import com.agents.main.domain.model.agents.AgentInfo
import com.agents.main.domain.model.agents.Agents
import com.agents.main.domain.model.weapons.WeaponInfo
import com.agents.main.domain.model.weapons.Weapons

interface ValorantApiRepository {

    suspend fun getAgents(): Resource<Agents>

    suspend fun getAgentsInfo(id: String): Resource<AgentInfo>

    suspend fun getWeapons(): Resource<Weapons>

    suspend fun getWeaponInfo(id: String): Resource<WeaponInfo>
}