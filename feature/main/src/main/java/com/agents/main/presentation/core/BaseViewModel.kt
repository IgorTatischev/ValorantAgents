package com.agents.main.presentation.core

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<STATE : Any, SIDE_EFFECT : Any, EVENT : Any>(
    initialState: STATE,
) : ViewModel(), ContainerHost<STATE, SIDE_EFFECT> {

    override val container: Container<STATE, SIDE_EFFECT> = container(
        initialState = initialState
    )

    abstract fun dispatch(event: EVENT)

    fun post(sideEffect: SIDE_EFFECT) = intent { postSideEffect(sideEffect) }
}