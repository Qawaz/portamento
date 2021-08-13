package com.wakaztahir.portamentoplayer

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
                    val currentFile by mutableStateOf("1.mp3")

                    val mediaPlayer = remember { MediaPlayer() }

                    LaunchedEffect(key1 = null, block = {
                        kotlin.runCatching {

                            val x = assets.open(currentFile)
                            val tempFile = openFileOutput("active.mp3", Context.MODE_PRIVATE)
                            tempFile.write(x.readBytes())
                            tempFile.close()
                            x.close()
                            val file = File(filesDir.absolutePath + File.separator + "active.mp3")

                            if (file.canRead()) {
                                Log.d("TL_CreatingCopyFile", "Can Read")
                            }

                            mediaPlayer.setAudioAttributes(
                                AudioAttributes.Builder()
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build()
                            )
                            mediaPlayer.setDataSource(file.absolutePath)
                            Log.d("TL_CreatingCopyFile", "Data Source Set")
                            mediaPlayer.prepareAsync()
                            mediaPlayer.setOnPreparedListener {
                                it.start()
                            }
                            Log.d("TL_CreatingCopyFile", "Work Ended")
                        }
                    })
                }
            }
        }
    }
}