package com.wakaztahir.portamento.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.wakaztahir.portamento.PortamentoScope

@Composable
fun PortamentoScope.DefaultPlayer(
    modifier: Modifier = Modifier,
    slider: @Composable () -> Unit = { DefaultSlider() },
    positionDuration: @Composable () -> Unit = { PositionDurationText() },
    mainButtons: @Composable BoxScope.() -> Unit = {
        DefaultPlayButton(modifier = Modifier.align(Alignment.Center))
    }
) {
    Column(modifier = modifier) {
        slider()
        positionDuration()
        Box(modifier = Modifier.fillMaxWidth()) {
            mainButtons(this)
        }
    }
}