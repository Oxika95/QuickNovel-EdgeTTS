package com.lagradost.quicknovel.tts

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.speech.tts.TextToSpeech
import com.lagradost.quicknovel.R

sealed class ReaderTtsEngine {
    data object Default : ReaderTtsEngine()
    data object Edge : ReaderTtsEngine()
    data class Installed(val packageName: String, val label: String) : ReaderTtsEngine()
}

object TtsEngines {
    const val DEFAULT_ID = "default"
    const val EDGE_ID = "edge"

    fun id(engine: ReaderTtsEngine): String = when (engine) {
        ReaderTtsEngine.Default -> DEFAULT_ID
        ReaderTtsEngine.Edge -> EDGE_ID
        is ReaderTtsEngine.Installed -> engine.packageName
    }

    fun resolveStored(storedEngine: String?, storedVoice: String?): String {
        if (storedEngine == EDGE_ID) return EDGE_ID
        if (!storedEngine.isNullOrBlank()) return storedEngine
        if (storedVoice?.startsWith(EdgeTtsVoices.PREFIX) == true) return EDGE_ID
        return DEFAULT_ID
    }

    fun installed(context: Context): List<ReaderTtsEngine.Installed> {
        val intent = Intent(TextToSpeech.Engine.INTENT_ACTION_TTS_SERVICE)
        val resolved = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.queryIntentServices(
                intent,
                PackageManager.ResolveInfoFlags.of(0)
            )
        } else {
            @Suppress("DEPRECATION")
            context.packageManager.queryIntentServices(intent, 0)
        }
        return resolved.mapNotNull { info ->
            val packageName = info.serviceInfo?.packageName ?: return@mapNotNull null
            val label = info.loadLabel(context.packageManager).toString().ifBlank { packageName }
            ReaderTtsEngine.Installed(packageName, label)
        }.distinctBy { it.packageName }.sortedBy { it.label.lowercase() }
    }

    fun pickerItems(context: Context): List<Pair<String, ReaderTtsEngine>> {
        val items = mutableListOf(
            context.getString(R.string.default_text) to ReaderTtsEngine.Default,
            context.getString(R.string.tts_engine_edge) to ReaderTtsEngine.Edge,
        )
        for (engine in installed(context)) {
            items.add(engine.label to engine)
        }
        return items
    }
}
