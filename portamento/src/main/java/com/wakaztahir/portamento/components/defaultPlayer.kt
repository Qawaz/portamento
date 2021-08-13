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
fun PortamentoScope.DefaultPlayer(modifier: Modifier = Modifier) {

    val portamento = this

    Column(modifier = modifier) {
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = if (portamento.currentPosition != 0 && portamento.duration != 0) {
                portamento.currentPosition.toFloat() / portamento.duration
            } else {
                0f
            },
            onValueChange = {

            }
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                text = portamento.currentPosition.toString()
            )
            Text(
                modifier = Modifier.align(Alignment.TopEnd),
                text = portamento.duration.toString()
            )
        }
        Row(horizontalArrangement = Arrangement.Center) {
            when (portamento.playState) {
                PlayState.Paused, PlayState.Stopped -> {
                    IconButton(onClick = { portamento.state.play() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.play_circle_filled),
                            contentDescription = null
                        )
                    }
                }
                PlayState.Playing -> {
                    IconButton(onClick = { portamento.state.pause() }) {
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