package com.fni421.studytasktracker.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
private val Context.settingsDataStore by preferencesDataStore(name ="settings")
  class SettingsStore(context: Context) {
      private val appcon = context.applicationContext
     private val darkmo = booleanPreferencesKey("dark_mode")
       val darkM: Flow<Boolean> =
        appcon.settingsDataStore.data.map { preferences -> preferences[darkmo] ?:false }
     suspend fun setDarkMode(enabled: Boolean) {
        appcon.settingsDataStore.edit { preferences -> preferences[darkmo] = enabled
        }
    }
  }