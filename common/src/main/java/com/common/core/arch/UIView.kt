package com.common.core.arch

interface UIView<State: UIState> {
    fun render(state: State)
}