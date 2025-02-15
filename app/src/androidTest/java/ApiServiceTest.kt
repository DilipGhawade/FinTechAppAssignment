//package com.dilip.ghawade.fintechappassignment2
//
//import com.dilip.ghawade.fintechappassignment2.api.ApiService
//import com.dilip.ghawade.fintechappassignment2.model.Post
//import com.dilip.ghawade.fintechappassignment2.network.ApiService
//import kotlinx.coroutines.runBlocking
//import okhttp3.OkHttpClient
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.GET
//
//class ApiServiceTest {
//    private lateinit var mockWebServer: MockWebServer
//    private lateinit var apiService: ApiService
//
//    @Before
//    fun setUp() {
//        mockWebServer = MockWebServer()
//        mockWebServer.start()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(mockWebServer.url("/"))
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(OkHttpClient())
//            .build()
//
//        apiService = retrofit.create(ApiService::class.java)
//    }
//
//    @Test
//    fun `fetchPosts returns posts list`() = runBlocking {
//        // Given a mocked response
//        val mockResponse = """
//            [
//                {"id": 1, "userId": 1, "title": "Post Title", "body": "Post Body"}
//            ]
//        """.trimIndent()
//        mockWebServer.enqueue(MockResponse().setBody(mockResponse).setResponseCode(200))
//
//        // When fetching posts
//        val response = apiService.fetchPosts()
//
//        // Then check the response
//        assertEquals(1, response.size)
//        assertEquals("Post Title", response[0].title)
//    }
//
//    @After
//    fun tearDown() {
//        mockWebServer.shutdown()
//    }
//}
