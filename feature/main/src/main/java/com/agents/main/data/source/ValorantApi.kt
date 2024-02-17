package com.agents.main.data.source

import com.agents.main.domain.model.agents.AgentInfo
import com.agents.main.domain.model.agents.Agents
import com.agents.main.domain.model.weapons.WeaponInfo
import com.agents.main.domain.model.weapons.Weapons
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ValorantApi {

    @GET("agents?isPlayableCharacter=true")
    suspend fun getAgents(): Response<Agents>

    @GET("agents/{id}")
    suspend fun getAgentInfo(
        @Path("id") id: String
    ): Response<AgentInfo>

    @GET("weapons")
    suspend fun getWeapons(): Response<Weapons>

    @GET("weapons/{id}")
    suspend fun getWeaponInfo(
        @Path("id") id: String
    ): Response<WeaponInfo>

}