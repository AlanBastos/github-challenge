package com.example.githubchallenge

import com.example.githubchallenge.model.Owner
import junit.framework.TestCase.assertEquals
import org.junit.Test

class OwnerTest {

    @Test
    fun testOwnerCreation() {
        val owner = Owner(
            1,
            "AlanBastos",
            "https://avatars.githubusercontent.com/u/29357758?v=4"
        )
        assertEquals(1,owner.id)
        assertEquals("AlanBastos",owner.login)
        assertEquals("https://avatars.githubusercontent.com/u/29357758?v=4", owner.avatar_url)

    }

    @Test
    fun testEquality() {
        val owner1 = Owner(
            1,
            "AlanBastos",
            "https://avatars.githubusercontent.com/u/29357758?v=4"
        )
        val owner2 = Owner(
            1,
            "AlanBastos",
            "https://avatars.githubusercontent.com/u/29357758?v=4"
        )

        assertEquals(owner1, owner2)
    }

    @Test
    fun testCopy() {
        val owner1 = Owner(
            1,
            "AlanBastos",
            "https://avatars.githubusercontent.com/u/29357758?v=4"
        )
        val owner2 = owner1.copy(login = "AlanBastos")
        assertEquals(1,owner2.id)
        assertEquals("AlanBastos", owner2.login)
        assertEquals("https://avatars.githubusercontent.com/u/29357758?v=4",
            owner2.avatar_url)
    }

}