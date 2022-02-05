package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.model.CommentModel
import com.jc.app.blog.domain.repository.PostRepository
import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetCommentsByPostTest {

    private lateinit var getCommentsByPost: GetCommentsByPost
    private var mockkRepository: PostRepository = mockk(relaxed = true)

    @Before
    fun setUp() {
        getCommentsByPost = GetCommentsByPost(mockkRepository)
    }

    @Test
    fun `When use case is executed then getCommentsByPost is called once by repository`() {
        runBlocking {
            getCommentsByPost.invoke(postId = 1)
        }
        coVerify(exactly = 1) { mockkRepository.getCommentsByPost(any()) }
    }

    @Test
    fun `When getCommentsByPost returns Success then use case returns Success`() {

        coEvery { mockkRepository.getCommentsByPost(any()) } returns Either.Success(emptyList())

        val expectedRes = Either.Success(emptyList<CommentModel>())

        lateinit var res: Either<Failure, List<CommentModel>>

        runBlocking {
            res = getCommentsByPost.invoke(postId = 1)
        }

        coVerify(exactly = 1) { mockkRepository.getCommentsByPost(any()) }
        Assert.assertEquals(expectedRes, res)
    }

}