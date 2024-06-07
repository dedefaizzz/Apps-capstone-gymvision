package com.dicoding.gymvision.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.gymvision.data.model.UserModel
import com.dicoding.gymvision.data.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val storyRepository: Repository) : ViewModel() {


    fun userLogin(email: String, password: String) = storyRepository.userLogin(email, password)

    fun userRegister(name: String, email: String, password: String) = storyRepository.userRegister(name, email, password)


    fun storeUserSession(user: UserModel) {
        viewModelScope.launch {
            storyRepository.storeUserSession(user)
        }
    }

    fun retrieveUserSession(): LiveData<UserModel> {
        return storyRepository.retrieveUserSession().asLiveData()
    }

    fun userLogout() {
        viewModelScope.launch {
            storyRepository.userLogout()
        }
    }
}
