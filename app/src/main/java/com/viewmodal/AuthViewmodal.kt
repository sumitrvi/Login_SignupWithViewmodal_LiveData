package com.viewmodal

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notesapi.modals.UserRequest
import com.notesapi.modals.UserResponse
import com.notesapi.utils.NetworkResult
import com.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class AuthViewmodal @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = userRepository.mutableLiveData

    fun loginUser(userRequest: UserRequest) {
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }
    }

    fun signupUser(userRequest: UserRequest) {
        viewModelScope.launch {
            userRepository.signup(userRequest)
        }
    }

    fun validDateUser(userRequest: UserRequest): Pair<Boolean, String> {
        var result = Pair(true, "");
        if (TextUtils.isEmpty(userRequest.username) || TextUtils.isEmpty(userRequest.email) ||
            TextUtils.isEmpty(userRequest.password)
        ) {
            result = Pair(false, "Kindly enter all the required fields")
        } else if (userRequest.username.length < 4) {
            result = Pair(false, "Username should be among 5 digit")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(userRequest.email).matches()) {
            result = Pair(false, "Kindly enter valid email address")
        } else if (userRequest.password.length < 4) {
            result = Pair(false, "Password should be greater 5 digit")
        }
        return result
    }
}