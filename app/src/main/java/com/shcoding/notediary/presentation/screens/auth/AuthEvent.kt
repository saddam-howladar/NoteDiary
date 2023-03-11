package com.shcoding.notediary.presentation.screens.auth

sealed class AuthEvent {
    data class setLoading(val loading: Boolean) : AuthEvent()
    data class signInWithMongoDbAtlas(
        val tokenId: String,
        val onSuccess: () -> Unit,
        val onError: (Exception) -> Unit
    ): AuthEvent()
}