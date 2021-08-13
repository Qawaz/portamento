package com.wakaztahir.portamento

import android.media.AudioAttributes
import android.media.MediaPlayer

fun PortamentoState.initialize(path : String,onPrepared : (MediaPlayer)->Unit = {}) {

    val state = this

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
                state.player?.reset()
                state.isPrepared = false
                state.player = null
            }
        }
    }
    this.player!!.setDataSource(path)
    this.player!!.prepareAsync()
}