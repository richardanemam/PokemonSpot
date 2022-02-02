package com.common.core.arch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.common.core.arch.UIAction
import com.common.core.arch.UIState

abstract class BaseViewModel<State : UIState, Action : UIAction>(
    initialState: State
) : ViewModel() {

    private val viewModelState = State(initialState)
    private val viewModelAction = Action<Action>()

    val state: LiveData<State>
        get() = viewModelState.state

    val action: LiveData<Action>
        get() = viewModelAction.action

    protected fun setState(state: (State) -> State) {
        viewModelState.setState(state)
    }

    protected fun sendAction(action: () -> Action) {
        viewModelAction.sendAction(action)
    }
}