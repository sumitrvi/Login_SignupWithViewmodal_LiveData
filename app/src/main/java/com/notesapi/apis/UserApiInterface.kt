package com.notesapi.apis

import com.notesapi.modals.UserRequest
import com.notesapi.modals.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiInterface {

    @POST("/users/signin")
    suspend fun loginApi(@Body userReq:UserRequest):Response<UserResponse>

    @POST("/users/signup")
    suspend fun userRegister(@Body userReq: UserRequest):Response<UserResponse>


}