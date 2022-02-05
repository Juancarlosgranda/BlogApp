package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.repository.PostRepository
import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAndSavePostsFromRemoteTest {

    private lateinit var getPoAndSavePostsFromRemote: GetAndSavePostsFromRemote
    private var mockkRepository: PostRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        getPoAndSavePostsFromRemote = GetAndSavePostsFromRemote(mockkRepository)
    }

    @Test
    fun `When getPostsFromRemote result is success then saveAllPosts is called once`() {
        coEvery { mockkRepository.getPostsFromRemote() } returns Either.Success(emptyList())
        runBlocking {
            getPoAndSavePostsFromRemote.invoke()
        }
        coVerify(exactly = 1) { mockkRepository.saveAllPosts(any()) }
    }

    @Test
    fun `When getPostsFromRemote result is Error then saveAllPosts is called zero times`() {
        coEvery { mockkRepository.getPostsFromRemote() } returns Either.Error(Failure.None)
        runBlocking {
            getPoAndSavePostsFromRemote.invoke()
        }
        coVerify(exactly = 0) { mockkRepository.saveAllPosts(any()) }
    }
}