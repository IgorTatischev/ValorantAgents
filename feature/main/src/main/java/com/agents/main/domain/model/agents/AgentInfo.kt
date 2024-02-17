package com.agents.main.domain.model.agents

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AgentInfo(
    @SerializedName("data")
    val agent: Agent,
    val status: Int
)

@Keep
data class Agent(
    val uuid: String,
    val abilities: List<Ability>,
    val background: String,
    val backgroundGradientColors: List<String>,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val fullPortrait: String,
    val role: Role,
)

@Keep
data class Role(
    val displayIcon: String,
    val displayName: String,
)

@Keep
data class Ability(
    val description: String,
    val displayIcon: String?,
    val displayName: String,
    val slot: String
)