package com.wakaztahir.portamento.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wakaztahir.portamento.*
import com.wakaztahir.portamento.R

@Composable
fun PortamentoScope.DefaultPlayer(
    modifier: Modifier = Modifier,
    slider: @Composable () -> Unit = { DefaultSlider() },
    positionDuration: @Composable () -> Unit = { PositionDurationText() }
) {

    val portamento = this
    val state = portamento.state

    Column(modifier = modifier) {
        slider()
        positionDuration()
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            when (state.playState) {
                PlayState.Paused, PlayState.Stopped -> {
                    IconButton(onClick = { state.play() }) {
                        Icon(
                            modifier = Modifier.size(56.dp),
                            painter = painterResource(id = R.drawable.play_circle_filled),
                            contentDescription = null
                        )
                    }
                }
                PlayState.Playing -> {
                    IconButton(onClick = { state.pause() }) {
                        Icon(
                            modifier = Modifier.size(56.dp),
                            painter = painterResource(id = R.drawable.pause_circle_filled),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}