package com.wakaztahir.portamento.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wakaztahir.portamento.*

@Composable
fun PortamentoScope.DefaultPlayButton(modifier: Modifier = Modifier) {
    IconButton(
        modifier = modifier,
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