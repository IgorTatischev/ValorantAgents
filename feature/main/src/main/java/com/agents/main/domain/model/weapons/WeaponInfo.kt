package com.agents.main.domain.model.weapons

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WeaponInfo(
    @SerializedName("data")
    val weapon: Weapon,
    val status: Int
)

@Keep
data class Weapon(
    val uuid: String,
    val displayIcon: String,
    val displayName: String,
    val killStreamIcon: String,
    val shopData: ShopData?,
    val skins: List<Skin>,
    val weaponStats: WeaponStats?
)

@Keep
data class Skin(
    val displayIcon: String?,
    val displayName: String?,
    val wallpaper: String?
)

@Keep
data class ShopData(
    val category: String?,
    val cost: Int?,
)

@Keep
data class WeaponStats(
    val equipTimeSeconds: Double?,
    val fireRate: Double?,
    val magazineSize: Int?,
    val reloadTimeSeconds: Double?,
    val damageRanges: List<DamageRange>?,
)

@Keep
data class DamageRange(
    val rangeStartMeters: Int,
    val rangeEndMeters: Int,
    val headDamage: Double,
    val bodyDamage: Double,
    val legDamage: Double,
)