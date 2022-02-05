package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.repository.PostRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserTest {

    private lateinit var getUser: GetUser
    private var mockkRepository: PostRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        getUser = GetUser(mockkRepository)
    }

    @Test
    fun `When use case is executed then getUser is called once by repository`() {
        runBlocking {
            getUser.invoke(userId = 1)
        }
        coVerify(exactly = 1) { mockkRepository.getUser(any()) }
    }
}