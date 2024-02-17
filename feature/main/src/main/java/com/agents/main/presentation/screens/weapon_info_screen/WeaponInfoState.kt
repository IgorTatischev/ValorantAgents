package com.agents.main.presentation.screens.weapon_info_screen

import com.agents.main.domain.model.weapons.Weapon


sealed class WeaponInfoScreenState {
    object Loading : WeaponInfoScreenState()
    data class Success(val weapon: Weapon?) : WeaponInfoScreenState()
    class Error(val message: String) : WeaponInfoScreenState()
}

sealed interface WeaponInfoScreenSideEffect {
    object ShowSnackBar : WeaponInfoScreenSideEffect
    object PopBack : WeaponInfoScreenSideEffect
}

sealed interface WeaponInfoScreenEvent {
    object LoadInfo: WeaponInfoScreenEvent
    object PopBack : WeaponInfoScreenEvent
}