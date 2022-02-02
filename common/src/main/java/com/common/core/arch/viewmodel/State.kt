package com.common.core.arch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.common.core.arch.UIState

class State<State : UIState>(initalState: State) {

    private val _state: MutableLiveData<State> = MutableLiveData(initalState)
    val state: LiveData<State> = _state

    fun setState(state: (State) -> State) {
        _state.value = state(_state.value!!)
    }

}