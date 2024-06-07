package com.dicoding.gymvision.data.di

import android.content.Context
import com.dicoding.gymvision.data.api.ApiConfig
import com.dicoding.gymvision.data.preference.UserPreferences
import com.dicoding.gymvision.data.preference.userPreferencesDataStore
import com.dicoding.gymvision.data.repository.Repository


object StoryInjection {
    fun provideRepository(context: Context): Repository {
        val dataStore = context.userPreferencesDataStore
        UserPreferences.init(dataStore)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService, context)
    }
}
