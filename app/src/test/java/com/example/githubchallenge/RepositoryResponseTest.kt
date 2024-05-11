package com.example.githubchallenge

import com.example.githubchallenge.model.Owner
import com.example.githubchallenge.model.Repository
import com.example.githubchallenge.model.RepositoryResponse
import org.junit.Assert
import org.junit.Test

class RepositoryResponseTest {

    @Test
    fun testRepositoryResponseCreation() {

        val repository1 = Repository(
            1,
            "github-challenge",
            "AlanBastos/github-challenge",
            "Android Kotlin",
            1,
            1,
            Owner(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4")
        )
        val repository2 = Repository(
            1,
            "github-challenge",
            "AlanBastos/github-challenge",
            "Android Kotlin",
            1,
            1,
            Owner(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4")
        )
        val items = listOf(repository1, repository2)

        val repositoryResponse = RepositoryResponse(2, false, items)

        Assert.assertEquals(2,repositoryResponse.totalCount)
        Assert.assertEquals(false, repositoryResponse.incompleteResults)
        Assert.assertEquals(items, repositoryResponse.items)
    }

    @Test
    fun testEquality() {
        val repository1 = Repository(
            1,
            "github-challenge",
            "AlanBastos/github-challenge",
            "Android Kotlin",
            1,
            1,
            Owner(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4")
        )
        val repository2 = Repository(
            1,
            "github-challenge",
            "AlanBastos/github-challenge",
            "Android Kotlin",
            1,
            1,
            Owner(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4")
        )
        val items1 = listOf(repository1, repository2)
        val repositoryResponse1 = RepositoryResponse(2, false, items1)

        val items2 = listOf(repository1, repository2)
        val repositoryResponse2 = RepositoryResponse(2, false, items2)

        Assert.assertEquals(repositoryResponse1, repositoryResponse2)

    }

}