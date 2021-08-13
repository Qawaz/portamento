package com.wakaztahir.portamento

import android.media.MediaPlayer
import androidx.compose.runtime.*

class PortamentoState {
    var player: MediaPlayer? = null

    var isPrepared by mutableStateOf(false)
        internal set

    var playState by mutableStateOf(PlayState.Stopped)
        internal set

    var duration by mutableStateOf(0)
        internal set

    var currentPosition by mutableStateOf(0)
        internal set
}

@Composable
fun rememberPortamentoState(): PortamentoState {
    return remember { PortamentoState() }
}