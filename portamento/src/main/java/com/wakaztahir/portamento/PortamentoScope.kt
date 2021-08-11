package com.wakaztahir.portamento

enum class PlayState {
    Stopped,
    Playing,
    Paused
}

interface PortamentoScope {
    val state: PortamentoState
    val playState: PlayState
    val duration: Int
    var currentPosition: Int
}