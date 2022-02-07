package com.common.core.arch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.common.core.arch.UIState

class State<State : UIState>(initialState: State) {

    private val _state: MutableLiveData<State> = MutableLiveData(initialState)
    val state: LiveData<State> = _state

    fun setState(newState: (State) -> State) {
        _state.postValue(newState(_state.value!!))
    }
}