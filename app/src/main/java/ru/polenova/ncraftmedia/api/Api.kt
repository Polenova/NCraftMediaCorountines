package ru.polenova.ncraftmedia.api

import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    // URL запроса (без учета основного адресса)
    @POST("api/v1/authentication")
    suspend fun authenticate(@Body authRequestParams: AuthRequestParams): retrofit2.Response<Token>

    @POST("api/v1/registration")
    suspend fun register(@Body registrationRequestParams: RegistrationRequestParams): retrofit2.Response<Token>
}