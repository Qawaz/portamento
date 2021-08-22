package com.wakaztahir.portamentoplayer

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.wakaztahir.portamento.PortamentoState
import com.wakaztahir.portamento.components.DefaultPlayer
import com.wakaztahir.portamento.destroy
import com.wakaztahir.portamento.play
import com.wakaztahir.portamento.rememberPortamentoPlayer
import com.wakaztahir.portamentoplayer.ui.theme.PortamentoDevTheme
import java.io.File

class MainActivity : ComponentActivity() {

    val state = PortamentoState()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val x = assets.open("1.mp3")
        val tempFile = openFileOutput("active.mp3", Context.MODE_PRIVATE)
        tempFile.write(x.readBytes())
        tempFile.close()
        x.close()

        val file = File(filesDir.absolutePath + File.separator + "active.mp3")

        setContent {

            var displaying by remember { mutableStateOf(false) }

            PortamentoDevTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Button(onClick = { displaying = !displaying }) {
                            Text(text = "Change Display")
                        }
                        val player = rememberPortamentoPlayer(
                            path = file.absolutePath,
                            state = state,
                            onInitialized = { play() }
                        )
                        
                        if (displaying) {
                            player.DefaultPlayer()
                        }
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        state.destroy()
    }
}