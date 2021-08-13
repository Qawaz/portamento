package com.wakaztahir.portamento

import android.media.MediaPlayer
import androidx.compose.runtime.*

class PortamentoState {
    var player: MediaPlayer? = null

    var isPrepared by mutableStateOf(false)
        internal set
}

@Composable
fun rememberPortamentoState(): PortamentoState {
    return remember { PortamentoState() }
}