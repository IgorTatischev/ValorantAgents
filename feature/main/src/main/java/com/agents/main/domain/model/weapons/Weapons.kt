package com.agents.main.domain.model.weapons

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Weapons(
    @SerializedName("data")
    val weapons: List<Weapon>,
    val status: Int
)