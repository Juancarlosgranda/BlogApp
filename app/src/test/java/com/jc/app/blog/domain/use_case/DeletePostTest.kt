package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.repository.PostRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeletePostTest {

    private lateinit var deletePost: DeletePost
    private var mockkRepository: PostRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        deletePost = DeletePost(mockkRepository)
    }

    @Test
    fun `When use case is executed then deletePost is called once by repository`() {
        runBlocking {
            deletePost.invoke(postId = 1)
        }
        coVerify(exactly = 1) { mockkRepository.deletePost(any()) }
    }
}