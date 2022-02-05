package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.repository.PostRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteAllPostsTest {

    private lateinit var deleteAllPosts: DeleteAllPosts
    private var mockkRepository: PostRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        deleteAllPosts = DeleteAllPosts(mockkRepository)
    }

    @Test
    fun `When use case is executed then deleteAllPosts is called once by repository`() {
        runBlocking {
            deleteAllPosts.invoke()
        }
        coVerify(exactly = 1) { mockkRepository.deleteAllPosts() }
    }
}