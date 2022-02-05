package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.model.PostModel
import com.jc.app.blog.domain.repository.PostRepository
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdatePostTest {

    private lateinit var updatePost: UpdatePost
    private var mockkRepository: PostRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        updatePost = UpdatePost(mockkRepository)
    }

    @Test
    fun `When use case is executed then deletePost is called once by repository`() {
        runBlocking {
            updatePost.invoke(PostModel(1,3, title = "titleTest", body = "BodyTest"))
        }
        coVerify(exactly = 1) { mockkRepository.updatePost(any()) }
    }
}