package com.dicoding.gymvision.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.liveData
import com.dicoding.gymvision.data.api.ApiService
import com.dicoding.gymvision.data.model.UserModel
import com.dicoding.gymvision.data.preference.UserPreferences
import com.dicoding.gymvision.data.preference.userPreferencesDataStore
import com.dicoding.gymvision.data.response.ErrorResponse
import com.dicoding.gymvision.data.response.LoginResponse
import com.dicoding.gymvision.data.result.ResultState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class Repository private constructor(
    private val apiService: ApiService,
    private val preferences: UserPreferences
) {


    fun userRegister(name: String, email: String, password: String) = liveData {
        emit(ResultState.Loading)
        try {
            val response = apiService.register(name, email, password)
            emit(ResultState.Success(response.message))
        } catch (e: HttpException) {
            val errorMessage = when (e.code()) {
                400 -> "Email is already taken"
                else -> e.response()?.errorBody()?.let { errorBody ->
                    Gson().fromJson(errorBody.string(), ErrorResponse::class.java).message
                } ?: e.message()
            }
            emit(ResultState.Error(Throwable(errorMessage)))
        }
    }

    fun userLogin(email: String, password: String) = liveData {
        emit(ResultState.Loading)
        try {
            val response = apiService.login(email, password)
            emit(ResultState.Success(response.loginResult?.token))
        } catch (e: HttpException) {
            val errorMessage = e.response()?.errorBody()?.let { errorBody ->
                Gson().fromJson(errorBody.string(), LoginResponse::class.java).message
            } ?: e.message()
            emit(ResultState.Error(Throwable(errorMessage)))
        }
    }



    fun retrieveUserSession(): Flow<UserModel> = preferences.getUserSession()

    suspend fun storeUserSession(user: UserModel) {
        preferences.saveUserSession(user)
    }

    suspend fun userLogout() {
        preferences.clearUserSession()
    }

    private fun logError(message: String?) {
        Log.e(TAG, "Error: ${message.orEmpty()}")
    }

    companion object {
        private const val TAG = "StoryRepository"
        @Volatile
        private var instance: Repository? = null

        fun getInstance(apiService: ApiService, context: Context): Repository {
            return instance ?: synchronized(this) {
                instance ?: Repository(apiService, UserPreferences.apply {
                    init(context.userPreferencesDataStore)
                }).also { instance = it }
            }
        }
    }
}
