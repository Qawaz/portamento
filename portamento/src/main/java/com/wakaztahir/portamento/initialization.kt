package com.wakaztahir.portamento

import android.media.AudioAttributes
import android.media.MediaPlayer

fun PortamentoState.initialize() {
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
                this@initialize.isPrepared = true
                this@initialize.onPrepare()
            }
            this.setOnCompletionListener {
                this@initialize.player?.reset()
                this@initialize.isPrepared = false
                this@initialize.player = null
            }
        }
    }
}

internal fun PortamentoState.onPrepare() {
    this.onPrepared.forEach {
        it()
    }
    this.onPrepared.clear()
}