package com.wakaztahir.portamento

import android.util.Log

val onFailure: (Throwable) -> Unit = {
    Log.d("PP_Extensions","Extension Failure",it)
}

/**
 * plays the media if it has been initialized
 */
fun PortamentoState.play() = kotlin.runCatching {
//    if (this.player != null && this.player?.isPlaying == false && this.isPrepared) {
//        this.player!!.start()
//    }
}.onFailure(onFailure)

/**
 * same as calling [play]
 */
fun PortamentoState.resume() = this.play()

/**
 * pauses the player , if playing & initialized
 */
fun PortamentoState.pause() = kotlin.runCatching {
//    if (this.player != null && this.player?.isPlaying == true && this.isPrepared) {
//        this.player!!.pause()
//    }
}.onFailure(onFailure)

/**
 * stops the player , if playing & initialized
 */
fun PortamentoState.stop() = kotlin.runCatching {
//    if (this.player != null && this.player?.isPlaying == true && this.isPrepared) {
//        this.player!!.stop()
//    }
}.onFailure(onFailure)

/**
 * Resets Player , Releases Resources &
 */
fun PortamentoState.destroy() = kotlin.runCatching {
    Log.d("TL_DESTROY", "DestroyingPlayer")
}.onFailure(onFailure)