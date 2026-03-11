package com.mememaker.app.data.repository

import com.mememaker.app.data.api.RetrofitBuilder
import com.mememaker.app.model.Meme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemeRepository {
    private val api = RetrofitBuilder.api

    suspend fun fetchMemes(): Result<List<Meme>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getMemes()
                if (response.success && response.data != null) {
                    Result.success(response.data.memes)
                } else {
                    Result.failure(Exception("Imgflip API returned success=false"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
