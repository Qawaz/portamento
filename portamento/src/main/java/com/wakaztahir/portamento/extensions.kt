package com.wakaztahir.portamento

import android.util.Log

val onFailure: (Throwable) -> Unit = {
    Log.d("PP_Extensions", "Extension Failure", it)
}

/**
 * plays the media if it has been initialized
 */
fun PortamentoState.play() = kotlin.runCatching {
    if (this.player != null && this.player?.isPlaying == false && this.isPrepared) {
        this.player!!.start()
        this.playState = PlayState.Playing
    }
}.onFailure(onFailure)

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
        this.playState = PlayState.Paused
    }
}.onFailure(onFailure)

/**
 * stops the player , if initialized
 */
fun PortamentoState.stop() = kotlin.runCatching {
    if (this.player != null && this.isPrepared) {
        this.player!!.stop()
        this.playState = PlayState.Stopped
    }
}.onFailure(onFailure)

/**
 * Resets Player , Releases Resources &
 */
fun PortamentoState.destroy() = kotlin.runCatching {
    if (this.player != null) {
        stop()
        this.player!!.reset()
        isPrepared = false
        this.player!!.release()
        this.player = null
    }
}.onFailure(onFailure)

/**
 * Seeks The Player To Given Position , If initialized and given number < duration
 */
fun PortamentoState.seekTo(position: Int) = kotlin.runCatching {
    if (this.player != null && this.isPrepared && position > -1 && position < this.duration) {
        this.player!!.seekTo(position)
        currentPosition = position
    }
}