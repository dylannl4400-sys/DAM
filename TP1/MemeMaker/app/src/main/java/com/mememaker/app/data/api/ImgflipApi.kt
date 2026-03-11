package com.mememaker.app.data.api

import com.mememaker.app.model.ImgflipResponse
import retrofit2.http.GET

interface ImgflipApi {
    @GET("get_memes")
    suspend fun getMemes(): ImgflipResponse
}
