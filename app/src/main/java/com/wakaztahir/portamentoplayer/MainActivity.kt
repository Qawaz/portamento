package com.wakaztahir.portamentoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.wakaztahir.portamento.Portamento
import com.wakaztahir.portamento.components.defaultPlayer
import com.wakaztahir.portamento.play
import com.wakaztahir.portamento.rememberPortamentoState
import com.wakaztahir.portamentoplayer.ui.theme.PortamentoPlayerTheme
import java.io.File

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortamentoPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    //Portamento States
                    val state = rememberPortamentoState()
                    val currentFile by mutableStateOf(File("//android_asset/1.mp3"))

                    Portamento(
                        state = state,
                        path = currentFile.absolutePath,
                        onInitialized = {
                            play()
                        }
                    ) {
                        defaultPlayer()
                    }
                }
            }
        }
    }
}