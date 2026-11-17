package com.fni421.studytasktracker.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

// PLACEHOLDER: Member 6 (M. G) replaces the body with a real DataStore implementation.
// Keep the public members (darkMode, setDarkMode) exactly as they are.
class SettingsStore(context: Context) {
    private val _darkMode = MutableStateFlow(false)
    val darkMode: Flow<Boolean> = _darkMode

    suspend fun setDarkMode(enabled: Boolean) {
        _darkMode.value = enabled
    }
}
