package com.example.githubchallenge

import com.example.githubchallenge.model.User
import junit.framework.TestCase
import org.junit.Test

class UserTest {

    @Test
    fun testOwnerCreation() {
        val user = User(
            1,
            "AlanBastos",
            "https://avatars.githubusercontent.com/u/29357758?v=4"
        )
        TestCase.assertEquals(1, user.id)
        TestCase.assertEquals("AlanBastos", user.login)
        TestCase.assertEquals(
            "https://avatars.githubusercontent.com/u/29357758?v=4",
            user.avatar_url
        )

    }

    @Test
    fun testEquality() {
        val user1 = User(
            1,
            "AlanBastos",
            "https://avatars.githubusercontent.com/u/29357758?v=4"
        )
        val user2 = User(
            1,
            "AlanBastos",
            "https://avatars.githubusercontent.com/u/29357758?v=4"
        )

        TestCase.assertEquals(user1, user2)
    }

    @Test
    fun testCopy() {
        val user1 = User(
            1,
            "AlanBastos",
            "https://avatars.githubusercontent.com/u/29357758?v=4"
        )
        val user2 = user1.copy(login = "AlanBastos")
        TestCase.assertEquals(1, user2.id)
        TestCase.assertEquals("AlanBastos", user2.login)
        TestCase.assertEquals(
            "https://avatars.githubusercontent.com/u/29357758?v=4",
            user2.avatar_url
        )
    }

}