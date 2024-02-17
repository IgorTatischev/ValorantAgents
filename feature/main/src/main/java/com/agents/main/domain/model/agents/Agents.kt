package com.agents.main.domain.model.agents

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Agents(
    @SerializedName("data")
    val agents: List<Agent>,
    val status: Int
)