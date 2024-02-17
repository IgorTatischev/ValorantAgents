package com.agents.main.presentation.screens.weapons_screen

import androidx.lifecycle.viewModelScope
import com.agents.main.data.util.Resource
import com.agents.main.domain.use_case.GetWeapons
import com.agents.main.presentation.core.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class WeaponsViewModel(private val getWeaponsUseCase: GetWeapons) :
    BaseViewModel<WeaponsScreenState, WeaponsScreenSideEffect, WeaponsScreenEvent>(
        initialState = WeaponsScreenState.Loading
    ) {

    override fun dispatch(event: WeaponsScreenEvent) {
        when (event) {
            is WeaponsScreenEvent.WeaponClick -> post(WeaponsScreenSideEffect.NavigateToWeaponInfo(id = event.id))

            is WeaponsScreenEvent.LoadWeapons -> getWeapons()
        }
    }

    private fun getWeapons() {
        getWeaponsUseCase().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    intent { reduce { WeaponsScreenState.Error(message = result.message.toString()) } }
                    post(WeaponsScreenSideEffect.ShowSnackBar)
                }

                is Resource.Success -> {
                    intent {
                        reduce {
                            WeaponsScreenState.Success(
                                weapons = result.data?.weapons ?: emptyList()
                            )
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}