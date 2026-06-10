package com.nachinbombin.midimanipulator2.theme

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

object ThemeManager {
    private const val PREF_THEME_INDEX = "preferred_theme_index"
    
    var currentTheme by mutableStateOf(ThemePresets.ALL[0])
        private set

    fun init(context: Context) {
        val prefs = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
        val index = prefs.getInt(PREF_THEME_INDEX, 0)
        currentTheme = ThemePresets.ALL[index.coerceIn(0, ThemePresets.ALL.size - 1)]
    }

    fun setTheme(context: Context, index: Int) {
        val prefs = context.getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
        prefs.edit().putInt(PREF_THEME_INDEX, index).apply()
        currentTheme = ThemePresets.ALL[index.coerceIn(0, ThemePresets.ALL.size - 1)]
    }

    fun getColor(role: TokenRole): Color {
        val hex = currentTheme.colors[role] ?: "#FFFFFF"
        return Color(android.graphics.Color.parseColor(hex))
    }
}
