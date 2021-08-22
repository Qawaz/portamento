package com.wakaztahir.portamento

import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

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

    var playingPath by mutableStateOf<String?>(null)
        internal set
}