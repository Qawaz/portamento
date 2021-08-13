package com.wakaztahir.portamento

enum class PlayState {
    Stopped,
    Playing,
    Paused
}

interface PortamentoScope {
    val state: PortamentoState
    val duration: Int
    val currentPosition: Int
}