package com.example.ai_assistant.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(/*private val api:AuthAPIService*/) : ViewModel() {

//    private val _token = MutableLiveData<AuthTokenResponse>()
//    val token: LiveData<AuthTokenResponse> = _token
//
//     fun getToken() {
//         viewModelScope.launch {
//         val token = api.getToken()
//         _token.value = token}
//    }
}