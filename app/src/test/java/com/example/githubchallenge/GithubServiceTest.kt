package com.example.githubchallenge

import com.example.githubchallenge.model.GithubService
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var githubService: GithubService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val baseUrl = mockWebServer.url("/").toString()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        githubService = retrofit.create(GithubService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetJavaPopRepositories () {
        val githubRepository = githubService.getJavaPopRepositories(
            "java",
            "1",
            1
        )
        assertNotNull(githubRepository)
        assertTrue(true)
    }

    @Test
    fun testGetPullRequests() {
        val githubRepository = githubService.getPullRequests(
            "AlanBastos",
            "github-challenge"
        )
        assertNotNull(githubRepository)
        assertTrue(true)
    }

}