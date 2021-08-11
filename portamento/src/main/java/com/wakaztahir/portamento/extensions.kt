package com.wakaztahir.portamento

// Scope Bindings
fun PortamentoScope.play() = state.play()
fun PortamentoScope.resume() = state.resume()
fun PortamentoScope.pause() = state.pause()
fun PortamentoScope.stop() = state.stop()

/**
 * plays the media if it has been initialized
 */
fun PortamentoState.play() = kotlin.runCatching {
    if (this.player != null && this.player?.isPlaying == false && this.isPrepared) {
        this.player!!.start()
    }
}

/**
 * same as calling [play]
 */
fun PortamentoState.resume() = this.play()

/**
 * pauses the player , if playing & initialized
 */
fun PortamentoState.pause() = kotlin.runCatching {
    if (this.player != null && this.player?.isPlaying == true && this.isPrepared) {
        this.player!!.pause()
    }
}

/**
 * stops the player , if playing & initialized
 */
fun PortamentoState.stop() = kotlin.runCatching {
    if (this.player != null && this.player?.isPlaying == true && this.isPrepared) {
        this.player!!.stop()
    }
}