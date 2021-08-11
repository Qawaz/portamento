package com.wakaztahir.portamento

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Portamento(modifier: Modifier = Modifier, state: PortamentoState, path: String) {
    Column {
        Row {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null
                )
            }
        }
    }
}