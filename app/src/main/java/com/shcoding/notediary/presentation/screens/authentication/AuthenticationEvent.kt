package com.shcoding.notediary.presentation.screens.authentication

sealed class AuthenticationEvent {
    data class setLoading(val loading: Boolean) : AuthenticationEvent()
    data class signInWithMongoDbAtlas(
        val tokenId: String,
        val onSuccess: (Boolean) -> Unit,
        val onError: (Exception) -> Unit
    ): AuthenticationEvent()
}