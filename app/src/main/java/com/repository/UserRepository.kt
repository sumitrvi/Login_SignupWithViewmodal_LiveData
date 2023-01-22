package com.repository

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import com.notesapi.apis.UserApiInterface
import com.notesapi.modals.UserRequest
import com.notesapi.modals.UserResponse
import com.notesapi.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response

class UserRepository @Inject constructor(val userApiInterface: UserApiInterface) {
    private val _mutableLiveData = MutableLiveData<NetworkResult<UserResponse>>();
    val mutableLiveData: MutableLiveData<NetworkResult<UserResponse>>
        get() = _mutableLiveData

    suspend fun loginUser(userRequest: UserRequest) {
        _mutableLiveData.postValue(NetworkResult.Loading())
        val response = userApiInterface.loginApi(userRequest);
        handleResponse(response);
    }

    suspend fun signup(userRequest: UserRequest) {
        _mutableLiveData
        val signUpResponse = userApiInterface.userRegister(userRequest);
        handleResponse(signUpResponse);
    }

    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _mutableLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            var errorText:String=""
            if(response.errorBody()!!.charStream().readText()!=null && response.errorBody()!!.charStream().readText().isNotEmpty()){
                errorText = JSONObject(response.errorBody()!!.charStream().readText()).toString();
            }else{
                errorText="Something went wrong"
            }
            _mutableLiveData.postValue(NetworkResult.Error(errorText))
        } else {
            _mutableLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}