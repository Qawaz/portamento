package com.wakaztahir.portamento

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class PortamentoState {
//    var player: MediaPlayer? = null
    var isPrepared by mutableStateOf(false)
        internal set
    var onPrepared = mutableListOf<() -> Unit>()
        internal set
}