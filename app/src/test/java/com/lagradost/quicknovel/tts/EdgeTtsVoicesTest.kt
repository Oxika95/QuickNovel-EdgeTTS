package com.lagradost.quicknovel.tts

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Locale


class EdgeTtsVoicesTest {
    @Test
    fun matchingUsesExactLocaleWhenAvailable() {
        val voices = EdgeTtsVoices.matching(Locale.US)
        assertTrue(voices.isNotEmpty())
        assertTrue(voices.all { it.lang == "en-US" })
        assertTrue(voices.any { it.id == "en-US-AriaNeural" })
    }

    @Test
    fun voiceNamesStripLocaleAndNeuralSuffix() {
        val aria = EdgeTtsVoices.fromId("en-US-AriaNeural")
        assertEquals("Aria", aria?.name)
    }

    @Test
    fun localeKeysIgnoreCase() {
        assertEquals(
            EdgeTtsVoices.localeKey(Locale.US),
            EdgeTtsVoices.localeKey(Locale.forLanguageTag("en-US"))
        )
    }

    @Test
    fun storedEngineMigratesLegacyEdgeVoice() {
        assertEquals(
            TtsEngines.EDGE_ID,
            TtsEngines.resolveStored(null, "edge:en-US-AriaNeural")
        )
        assertEquals(
            TtsEngines.DEFAULT_ID,
            TtsEngines.resolveStored(null, null)
        )
        assertEquals(
            "com.google.android.tts",
            TtsEngines.resolveStored("com.google.android.tts", "edge:en-US-AriaNeural")
        )
    }
}
