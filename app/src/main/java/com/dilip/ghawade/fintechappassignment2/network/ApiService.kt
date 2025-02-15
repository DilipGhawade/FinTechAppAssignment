package com.dilip.ghawade.fintechappassignment2.network

import com.dilip.ghawade.fintechappassignment2.model.Post
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
