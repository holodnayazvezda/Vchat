package com.example.vchatmessenger.data

import android.content.Context
import com.example.vchatmessenger.data.network.NetworkVchatRepository
import com.example.vchatmessenger.data.network.VchatApiService
import com.example.vchatmessenger.data.network.VchatRepository
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.VchatSharedViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface AppContainer {
    val vchatRepository: VchatRepository
    
    val signUpSharedViewModel: SignUpSharedViewModel
    val logInSharedViewModel: LogInSharedViewModel
    val vchatSharedViewModel: VchatSharedViewModel
}

class DefaultAppContainer(
    context: Context
): AppContainer {
    private val baseUrl = "https://v-chat.ru/"

    private val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: VchatApiService by lazy {
        retrofit.create(VchatApiService::class.java)
    }

    override val vchatRepository: VchatRepository by lazy {
        NetworkVchatRepository(retrofitService)
    }
    override val signUpSharedViewModel: SignUpSharedViewModel by lazy {
        SignUpSharedViewModel()
    }
    override val logInSharedViewModel: LogInSharedViewModel by lazy {
        LogInSharedViewModel()
    }
    override val vchatSharedViewModel: VchatSharedViewModel by lazy {
        VchatSharedViewModel(Storage(context))
    }
}