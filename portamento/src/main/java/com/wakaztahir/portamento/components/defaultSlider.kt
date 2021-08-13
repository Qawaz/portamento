package com.wakaztahir.portamento.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.wakaztahir.portamento.PortamentoScope
import com.wakaztahir.portamento.seekTo

@Composable
fun PortamentoScope.DefaultSlider(modifier : Modifier = Modifier){
    val portamento = this
    val state = portamento.state
    var isChanging by remember { mutableStateOf(false) }
    var sliderValue by remember { mutableStateOf(0f) }

    Slider(
        modifier = modifier.fillMaxWidth(),
        value = if (!isChanging) {
            if (portamento.currentPosition != 0 && portamento.duration != 0) {
                portamento.currentPosition.toFloat() / portamento.duration
            } else {
                0f
            }
        } else {
            sliderValue
        },
        onValueChange = {
            isChanging = true
            sliderValue = it
        },
        onValueChangeFinished = {
            state.seekTo((sliderValue * portamento.duration).toInt())
            isChanging = false
        }
    )
}