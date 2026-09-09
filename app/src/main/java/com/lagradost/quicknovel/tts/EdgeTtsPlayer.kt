package com.lagradost.quicknovel.tts

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Build
import java.io.File

class EdgeTtsPlayer(private val context: Context) {
    private var player: MediaPlayer? = null
    private var tempFile: File? = null

    fun play(audio: ByteArray, speed: Float, pitch: Float, onDone: () -> Unit) {
        stop()
        if (audio.isEmpty()) {
            onDone()
            return
        }

        val file = File.createTempFile("qn_edge_tts", ".mp3", context.cacheDir)
        file.writeBytes(audio)
        tempFile = file

        val mp = MediaPlayer()
        player = mp
        mp.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build()
        )
        mp.setOnCompletionListener {
            onDone()
            cleanupFile()
        }
        mp.setOnErrorListener { _, _, _ ->
            onDone()
            cleanupFile()
            true
        }
        mp.setDataSource(file.absolutePath)
        mp.prepare()
        applyParams(mp, speed, pitch)
        mp.start()
    }

    fun stop() {
        player?.apply {
            setOnCompletionListener(null)
            setOnErrorListener(null)
            try {
                if (isPlaying) stop()
            } catch (_: IllegalStateException) {
            }
            reset()
            release()
        }
        player = null
        cleanupFile()
    }

    fun release() {
        stop()
    }

    private fun applyParams(mp: MediaPlayer, speed: Float, pitch: Float) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
        try {
            val params = mp.playbackParams
            params.speed = speed.coerceIn(0.5f, 2.0f)
            params.pitch = pitch.coerceIn(0.5f, 2.0f)
            mp.playbackParams = params
        } catch (_: IllegalArgumentException) {
        } catch (_: IllegalStateException) {
        }
    }

    private fun cleanupFile() {
        tempFile?.delete()
        tempFile = null
    }
}
