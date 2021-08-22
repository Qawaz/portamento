package com.wakaztahir.portamento

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.wakaztahir.portamento.components.DefaultPlayer
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Composable
fun Portamento(
    state: PortamentoState = rememberPortamentoState(),
    path: String,
    onInitialized: PortamentoState.() -> Unit = {},
    content: @Composable PortamentoScope.() -> Unit = { DefaultPlayer() },
) {
    val player = rememberPortamentoPlayer(path = path, state = state, onInitialized = onInitialized)
    content(player)
}

@Composable
fun rememberPortamentoPlayer(
    path: String,
    state: PortamentoState,
    onInitialized: PortamentoState.() -> Unit
): PortamentoScope {
    //Creating Scope
    val scope = remember(state, state.duration, state.currentPosition) {
        object : PortamentoScope {
            override val state: PortamentoState = state
            override val duration: Int = state.duration
            override var currentPosition: Int = state.currentPosition
                internal set(value) {
                    if (value < duration) {
                        state.currentPosition = value
                        field = currentPosition
                    }
                }
        }
    }

    LaunchedEffect(state.currentPosition, state.playState, block = {
        delay(500)
        if (state.player != null) {
            state.currentPosition = state.player!!.currentPosition
        }
    })

    DisposableEffect(key1 = path) {
        // Initializing Media Player
        state.initialize(path) {
            // Setting Audio Attributes
            state.duration = it.duration
            state.currentPosition = it.currentPosition

            // Calling onInitialized
            onInitialized(state)
        }
        onDispose {
            // Destroying Media Player
            state.destroy()
        }
    }

    return scope
}