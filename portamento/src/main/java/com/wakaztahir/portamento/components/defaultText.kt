package com.wakaztahir.portamento.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wakaztahir.portamento.PortamentoScope

private fun getFormattedText(totalSeconds: Int): String {

    val append0: (number: Int) -> String = {
        if (it.toString().length == 1) {
            "0$it"
        } else {
            it.toString()
        }
    }

    val hours = (totalSeconds / 60) / 60
    val mins = totalSeconds / 60
    val seconds = totalSeconds % 60
    var text = "${append0(mins)}:${append0(seconds)}"
    if (hours != 0) {
        text = "${append0(hours)}:$text"
    }
    return text
}

@Composable
fun PortamentoScope.PositionDurationText(modifier : Modifier = Modifier) {
    val portamento = this

    val formattedCurrentPosition = remember(portamento.currentPosition) {
        getFormattedText(portamento.currentPosition / 1000)
    }
    val formattedDuration = remember(portamento.duration) {
        getFormattedText(portamento.duration / 1000)
    }
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.align(Alignment.TopStart),
            text = formattedCurrentPosition
        )
        Text(
            modifier = Modifier.align(Alignment.TopEnd),
            text = formattedDuration
        )
    }
}

