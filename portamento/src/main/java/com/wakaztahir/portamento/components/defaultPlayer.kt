package com.wakaztahir.portamento.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.wakaztahir.portamento.*
import com.wakaztahir.portamento.R

@Composable
fun PortamentoScope.defaultPlayer(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = this@defaultPlayer.currentPosition.toFloat() / this@defaultPlayer.duration,
            onValueChange = {

            }
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                text = this@defaultPlayer.currentPosition.toString()
            )
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = this@defaultPlayer.duration.toString()
            )
        }
        Row(horizontalArrangement = Arrangement.Center) {
            when (this@defaultPlayer.playState) {
                PlayState.Paused, PlayState.Stopped -> {
                    IconButton(onClick = { this@defaultPlayer.play() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.play_circle_filled),
                            contentDescription = null
                        )
                    }
                }
                PlayState.Playing -> {
                    IconButton(onClick = { this@defaultPlayer.pause() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.pause_circle_filled),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}