package com.wakaztahir.portamento

import android.media.AudioAttributes
import android.media.MediaPlayer

/**
 * This initializes the player , sets the datasource calls prepare
 * You don't need to call this manually , [Portamento] calls it automatically
 */
fun PortamentoState.initialize(path: String, onPrepared: (MediaPlayer) -> Unit = {}) {

    val state = this

    this.playingPath = path

    if (this.player == null) {
        this.player = MediaPlayer().apply {
            //Setting Attributes
            this.setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )

            //Setting listeners
            this.setOnPreparedListener {
                state.isPrepared = true
                onPrepared(it)
            }
            this.setOnCompletionListener {
                state.playState = PlayState.Stopped
            }
        }
    }
    this.player!!.setDataSource(path)
    this.player!!.prepareAsync()
}

/**
 * This will reinitialize the player from state if it was destroyed
 */
fun PortamentoState.reinitialize(path: String, onPrepared: (MediaPlayer) -> Unit = {}) {
    initialize(path = path, onPrepared = {
        seekTo(this.currentPosition)
        onPrepared(it)
    })

}