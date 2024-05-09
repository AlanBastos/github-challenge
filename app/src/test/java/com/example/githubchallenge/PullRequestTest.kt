package com.example.githubchallenge

import com.example.githubchallenge.model.PullRequest
import com.example.githubchallenge.model.User
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.ParseException

class PullRequestTest {

    @Test
    fun testPullRequestCreation() {
        val pullRequest = PullRequest(
            1,
            "fix bug #123",
            User(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4"),
            "2024-05-09T08:47:37Z",
            "This is a PR for fixes a bug.",
            "open"
        )
        assertEquals(1, pullRequest.id)
        assertEquals("fix bug #123", pullRequest.title)
        assertEquals(User(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4"), pullRequest.user)
        assertEquals("This is a PR for fixes a bug.", pullRequest.body)
        assertEquals("open", pullRequest.state)
    }

    @Test
    fun testEquality() {
        val user = User(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4")
        val pullRequest1 = PullRequest(1,"fix bug #123",user,"2024-05-09T08:47:37Z",
            "This is a PR for fixes a bug.", "open")
        val pullRequest2 = PullRequest(1,"fix bug #123",user,"2024-05-09T08:47:37Z",
            "This is a PR for fixes a bug.", "open")

        assertEquals(pullRequest1, pullRequest2)
    }

    @Test
    fun testCopy() {
        val user = User(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4")
        val pullRequest1 = PullRequest(1,"fix bug #123",user,"2024-05-09T08:47:37Z",
            "This is a PR for fixes a bug.", "open")
        val pullRequest2 = pullRequest1.copy(title = "Feature implementation")

        assertEquals(1, pullRequest2.id)
        assertEquals("Feature implementation", pullRequest2.title)
        assertEquals("2024-05-09T08:47:37Z", pullRequest2.created_at)
        assertEquals("This is a PR for fixes a bug.", pullRequest2.body)
        assertEquals("open", pullRequest2.state)
    }

    @Test
    fun testGetFormattedDate() {
        val pullRequest = PullRequest(
            1,
            "fix bug #123",
            User(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4"),
            "2024-05-09T08:47:37Z",
            "This is a PR for fixes a bug.",
            "open"
        )

        val formattedDate = pullRequest.getFormattedDate()
        Assert.assertEquals("09/05/2024 - 05:47", formattedDate)
    }


    @Test(expected = ParseException::class)
    fun testGetFormattedDate_ParseException() {
        // Criação de uma instância de PullRequest com data em um formato inválido
        val pullRequest = PullRequest(
            1,
            "Pull request",
            null,
            "2024/05/09 08:47:37", // Formato de data inválido
            null,
            "open"
        )

        // Chamada do método que queremos testar
        pullRequest.getFormattedDate()
    }
}