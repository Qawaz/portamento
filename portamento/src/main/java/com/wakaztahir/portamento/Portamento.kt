package com.wakaztahir.portamento

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*

@ExperimentalAnimationApi
@Composable
fun Portamento(
    state: PortamentoState = remember { PortamentoState() },
    path: String,
    onInitialized: PortamentoState.() -> Unit = {},
    content: @Composable PortamentoScope.() -> Unit,
) {

    var playState by remember { mutableStateOf(PlayState.Stopped) }
    var duration by remember { mutableStateOf(0) }
    var currentPosition by remember { mutableStateOf(0) }

    //Creating Scope
    val scope = object : PortamentoScope {
        override val state: PortamentoState = state
        override val playState: PlayState = playState
        override val duration: Int = duration
        override var currentPosition: Int
            get() = currentPosition
            set(value) {
                currentPosition = value
                if (state.isPrepared) {
//                    state.player?.seekTo(value)
                }
            }
    }

    //Initializing Player
    LaunchedEffect(key1 = path, block = {

        state.initialize(path)

        //Setting Player Attributes
//        if (state.player != null) {
//            duration = state.player!!.duration
//            currentPosition = state.player!!.currentPosition
//        }

        onInitialized(state)
    })

    DisposableEffect(key1 = null, effect = {
        object : DisposableEffectResult {
            override fun dispose() {
                state.destroy()
            }
        }
    })

    content(scope)
}