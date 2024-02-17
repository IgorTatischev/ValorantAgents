package com.agents.main.presentation.screens.weapon_info_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.agents.main.data.util.Resource
import com.agents.main.domain.use_case.GetWeaponInfo
import com.agents.main.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class WeaponInfoViewModel(private val getWeaponInfoUseCase: GetWeaponInfo, savedStateHandle: SavedStateHandle) :
    BaseViewModel<WeaponInfoScreenState, WeaponInfoScreenSideEffect, WeaponInfoScreenEvent>(
        initialState = WeaponInfoScreenState.Loading
    ) {

    private var weaponId = ""

    init {
        savedStateHandle.get<String>("weaponId")?.let { weaponId = it }
    }

    override fun dispatch(event: WeaponInfoScreenEvent) {
        when(event){
            is WeaponInfoScreenEvent.PopBack -> post(WeaponInfoScreenSideEffect.PopBack)
            is WeaponInfoScreenEvent.LoadInfo -> getWeaponInfo(weaponId)
        }
    }

    private fun getWeaponInfo(id: String) {
        getWeaponInfoUseCase(id).onEach { result ->
            when(result){
                is Resource.Error -> {
                    intent { reduce { WeaponInfoScreenState.Error(message = result.message.toString()) } }
                    post(WeaponInfoScreenSideEffect.ShowSnackBar)
                }
                is Resource.Success -> {
                    intent { reduce { WeaponInfoScreenState.Success(weapon = result.data?.weapon) } }
                }
            }
        }.launchIn(viewModelScope)
    }
}