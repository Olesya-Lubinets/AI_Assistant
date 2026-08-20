package com.example.ai_assistant.API.authAPI

import com.example.ai_assistant.models.AuthModels.AuthTokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import java.util.UUID

interface AuthAPIService {

    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("api/v2/oauth")
    suspend fun getToken(
        @Header("RqUID") rqUID:String = UUID.randomUUID().toString(),
        @Field("scope") scope:String = "GIGACHAT_API_PERS"
    ): AuthTokenResponse
}