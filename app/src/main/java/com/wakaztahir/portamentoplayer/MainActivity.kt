package com.wakaztahir.portamentoplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import com.wakaztahir.portamento.Portamento
import com.wakaztahir.portamento.components.DefaultPlayer
import com.wakaztahir.portamento.play
import com.wakaztahir.portamentoplayer.ui.theme.PortamentoPlayerTheme
import kotlinx.coroutines.delay
import java.io.File

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val x = assets.open(currentFile)
//        val tempFile = openFileOutput("active.mp3", Context.MODE_PRIVATE)
//        tempFile.write(x.readBytes())
//        tempFile.close()
//        x.close()

        val file = File(filesDir.absolutePath + File.separator + "active.mp3")

        setContent {

            var displaying by remember { mutableStateOf(true) }

            PortamentoPlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    if (displaying) {
                        Portamento(
                            path = file.absolutePath,
                            onInitialized = {
                                play()
                            }
                        ) {
                            DefaultPlayer()
                        }
                    }
                    LaunchedEffect(key1 = null, block = {
                        delay(5000)
                        displaying = false
                        delay(2000)
                        displaying = true
                    })
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
//        portamentoState.destroy()
    }
}