package com.aptivist.composestate

sealed class MainViewContainerState {
    object ViewOne : MainViewContainerState()
    object ViewTwo : MainViewContainerState()
    object ViewThree : MainViewContainerState()
}
