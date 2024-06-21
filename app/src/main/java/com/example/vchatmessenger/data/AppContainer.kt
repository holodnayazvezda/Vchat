package com.example.vchatmessenger.data

import com.example.vchatmessenger.data.network.NetworkVchatRepository
import com.example.vchatmessenger.data.network.VchatApiService
import com.example.vchatmessenger.data.network.VchatRepository
import com.example.vchatmessenger.ui.sharedViewModel.LogInSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.SignUpSharedViewModel
import com.example.vchatmessenger.ui.sharedViewModel.VchatSharedViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val vchatRepository: VchatRepository
    
    val signUpSharedViewModel: SignUpSharedViewModel
    val logInSharedViewModel: LogInSharedViewModel
    val vchatSharedViewModel: VchatSharedViewModel
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
    override val signUpSharedViewModel: SignUpSharedViewModel by lazy {
        SignUpSharedViewModel()
    }
    override val logInSharedViewModel: LogInSharedViewModel by lazy {
        LogInSharedViewModel()
    }
    override val vchatSharedViewModel: VchatSharedViewModel by lazy {
        VchatSharedViewModel()
    }
}