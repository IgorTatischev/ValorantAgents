package com.agents.main.presentation.screens.weapons_screen

import com.agents.main.domain.model.weapons.Weapon

sealed class WeaponsScreenState {
    object Loading : WeaponsScreenState()

    data class Success(
        val weapons: List<Weapon> = emptyList()
    ) : WeaponsScreenState()

    class Error(val message: String) : WeaponsScreenState()
}

sealed interface WeaponsScreenSideEffect {
    object ShowSnackBar : WeaponsScreenSideEffect
    data class NavigateToWeaponInfo(val id: String): WeaponsScreenSideEffect
}

sealed interface WeaponsScreenEvent {
    object LoadWeapons: WeaponsScreenEvent
    data class WeaponClick(val id: String): WeaponsScreenEvent
}