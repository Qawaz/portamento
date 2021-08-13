package com.wakaztahir.portamento

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Composable
fun Portamento(
    state: PortamentoState = rememberPortamentoState(),
    path: String,
    onInitialized: PortamentoState.() -> Unit = {},
    content: @Composable PortamentoScope.() -> Unit,
) {

    var playState by remember { mutableStateOf(PlayState.Stopped) }
    var duration by remember { mutableStateOf(0) }
    var currentPosition by remember { mutableStateOf(0) }

    //Creating Scope
    val scope = remember(state, duration, currentPosition) {
        object : PortamentoScope {
            override val state: PortamentoState = state
            override var playState: PlayState = playState
                internal set(value) {
                    playState = value
                    field = playState
                }
            override val duration: Int = duration
            override var currentPosition: Int = currentPosition
                internal set(value) {
                    if (value < duration) {
                        currentPosition = value
                        field = currentPosition
                    }
                }
        }
    }

    LaunchedEffect(currentPosition, playState, block = {
        delay(500)
        if (state.player != null) {
            currentPosition = state.player!!.currentPosition
        }
    })

    DisposableEffect(key1 = path) {
        // Initializing Media Player
        state.initialize(path) {
            // Setting Audio Attributes
            duration = it.duration
            currentPosition = it.currentPosition

            // Calling onInitialized
            onInitialized(state)
        }
        onDispose {
            // Destroying Media Player
            state.destroy()
        }
    }

    content(scope)
}