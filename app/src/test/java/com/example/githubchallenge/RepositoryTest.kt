package com.example.githubchallenge

import com.example.githubchallenge.model.Owner
import com.example.githubchallenge.model.Repository
import com.example.githubchallenge.model.User
import org.junit.Assert
import org.junit.Test

class RepositoryTest {

    @Test
    fun testRepositoryCreation() {
        val repository = Repository(
            1,
            "github-challenge",
            "AlanBastos/github-challenge",
            "Android Kotlin",
            1,
            1,
            Owner(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4")
        )

        Assert.assertEquals(1, repository.id)
        Assert.assertEquals("github-challenge", repository.name)
        Assert.assertEquals("AlanBastos/github-challenge", repository.full_name)
        Assert.assertEquals("Android Kotlin", repository.description)
        Assert.assertEquals(1, repository.stargazers_count)
        Assert.assertEquals(1, repository.forks_count)
        Assert.assertEquals(
            Owner(
                1,
                "AlanBastos",
                "https://avatars.githubusercontent.com/u/29357758?v=4"
            ), repository.owner
        )
    }

    @Test
    fun testEquality() {
        val owner = Owner(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4")
        val repository1 = Repository(
            1,
            "github-challenge",
            "AlanBastos/github-challenge",
            "Android Kotlin",
            1,
            1,
            owner
        )

        val repository2 = Repository(1,
            "github-challenge",
            "AlanBastos/github-challenge",
            "Android Kotlin",
            1,
            1,
            owner)

        Assert.assertEquals(repository1, repository2)
    }

    @Test
    fun testCopy() {
        val owner = Owner(1,"AlanBastos", "https://avatars.githubusercontent.com/u/29357758?v=4")

        val repository1 = Repository(
            1,
            "github-challenge",
            "AlanBastos/github-challenge",
            "Android Kotlin",
            1,
            1,
            owner
        )

        val repository2 = repository1.copy(full_name = "AlanBastos/github-challenge")

        Assert.assertEquals(1, repository2.id)
        Assert.assertEquals("github-challenge", repository2.name)
        Assert.assertEquals("AlanBastos/github-challenge", repository2.full_name)
        Assert.assertEquals("Android Kotlin", repository2.description)
        Assert.assertEquals(1, repository2.stargazers_count)
        Assert.assertEquals(1, repository2.forks_count)
    }

}