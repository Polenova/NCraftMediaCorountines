package ru.polenova.ncraftmedia

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.polenova.ncraftmedia.api.API
import ru.polenova.ncraftmedia.api.AuthRequestParams
import ru.polenova.ncraftmedia.api.RegistrationRequestParams

object Repository {
    // Ленивое создание Retrofit экземпляра
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://ktor-crud-auth.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    // Ленивое создание API
    private val API: API by lazy {
        retrofit.create(ru.polenova.ncraftmedia.api.API::class.java)
    }

    suspend fun authenticate(login: String, password: String) = API.authenticate(
        AuthRequestParams(login, password)
    )

    suspend fun register(login: String, password: String) =
        API.register(
            RegistrationRequestParams(
                login,
                password
            )
        )
}