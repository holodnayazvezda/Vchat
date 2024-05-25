package com.example.vchatmessenger.data

import com.example.vchatmessenger.data.network.NetworkVchatRepository
import com.example.vchatmessenger.data.network.VchatApiService
import com.example.vchatmessenger.data.network.VchatRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val vchatRepository: VchatRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://v-chat.ru/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: VchatApiService by lazy {
        retrofit.create(VchatApiService::class.java)
    }

    override val vchatRepository: VchatRepository by lazy {
        NetworkVchatRepository(retrofitService)
    }
}