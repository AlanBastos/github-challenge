package com.example.githubchallenge

import com.example.githubchallenge.model.GithubService
import com.example.githubchallenge.presenter.GithubRepository
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test


class GithubRepositoryTest {

    private lateinit var mockWebService: MockWebServer
    private lateinit var githubRepository: GithubRepository

    @Before
    fun setup() {
        mockWebService = MockWebServer()
        mockWebService.start()

        githubRepository = GithubRepository()
    }

    @After
    fun tearDown() {
        mockWebService.shutdown()
    }

    @Test
    fun testGithubService() {
        val githubService = githubRepository.getGithubService()
        assertNotNull(githubService)
        assertTrue(true)
    }
}