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
            IconButton(
                onClick = {
                    when (state.playState) {
                        PlayState.Paused, PlayState.Stopped -> state.play()
                        PlayState.Playing -> state.pause()
                    }
                }
            ) {
                Icon(
                    modifier = Modifier.size(56.dp),
                    painter = when (state.playState) {
                        PlayState.Paused, PlayState.Stopped -> painterResource(id = R.drawable.play_circle_filled)
                        PlayState.Playing -> painterResource(id = R.drawable.pause_circle_filled)
                    },
                    contentDescription = null
                )
            }
        }
    }
}