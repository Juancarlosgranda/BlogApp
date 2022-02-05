package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.repository.PostRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPostsFromLocalTest {

    private lateinit var getPostsFromLocal: GetPostsFromLocal
    private var mockkRepository: PostRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        getPostsFromLocal = GetPostsFromLocal(mockkRepository)
    }

    @Test
    fun `When use case is executed then getPostsFromLocal is called once by repository`() {
        runBlocking {
            getPostsFromLocal.invoke()
        }
        coVerify(exactly = 1) { mockkRepository.getPostsFromLocal() }
    }
}