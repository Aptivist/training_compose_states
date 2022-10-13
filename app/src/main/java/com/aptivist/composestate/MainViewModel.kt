package com.aptivist.composestate

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _containerState = mutableStateOf<MainViewContainerState>(MainViewContainerState.ViewOne)
    val containerState : State<MainViewContainerState>
        get() = _containerState

    fun switchViews(view : MainViewContainerState) {
        _containerState.value = view
    }

}