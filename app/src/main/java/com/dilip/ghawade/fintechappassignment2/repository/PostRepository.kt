package com.dilip.ghawade.fintechappassignment2.repository

import com.dilip.ghawade.fintechappassignment2.model.Post
import com.dilip.ghawade.fintechappassignment2.network.ApiService
import com.dilip.ghawade.fintechappassignment2.room.PostDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor(
    private val apiService: ApiService,
    private val postDao: PostDao
) {
    val posts: Flow<List<Post>> = postDao.getAllPosts()

    suspend fun fetchAndStorePosts() {
        try {
            val postsFromApi = apiService.getPosts()
            postDao.insertPosts(postsFromApi)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
