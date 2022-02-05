package com.jc.app.blog.domain.repository

import com.jc.app.blog.data.BlogModelData
import com.jc.app.blog.data.repository.PostRepositoryImpl
import com.jc.app.blog.data.source.local.dao.PostDao
import com.jc.app.blog.data.source.local.ds.PostLocalDataSource
import com.jc.app.blog.data.source.local.ds.PostLocalDataSourceImpl
import com.jc.app.blog.data.source.local.entity.PostEntity
import com.jc.app.blog.data.source.remote.ds.PostRemoteDataSource
import com.jc.app.blog.data.source.remote.ds.PostRemoteDataSourceImpl
import com.jc.app.blog.data.source.remote.response.CommentResponse
import com.jc.app.blog.data.source.remote.response.PostResponse
import com.jc.app.blog.data.source.remote.service.RestService
import com.jc.app.blog.data.utils.ConnectionUtils
import com.jc.app.blog.domain.model.CommentModel
import com.jc.app.blog.domain.model.PostModel
import com.jc.app.blog.domain.model.UserModel
import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

class PostRepositoryTest {

    private val restService = mockk<RestService>()
    private val postDao = mockk<PostDao>()
    private val connectionUtils = mockk<ConnectionUtils> {
        every { isNetworkAvailable() } returns true
    }
    private val remoteDataSource: PostRemoteDataSource = PostRemoteDataSourceImpl(
        restService = restService,
        connectionUtils = connectionUtils
    )
    private val localDataSource: PostLocalDataSource = PostLocalDataSourceImpl(
        dao = postDao
    )

    private val repository: PostRepository = PostRepositoryImpl(
        remoteDS = remoteDataSource,
        localDS = localDataSource
    )

    @Test
    fun `Assert repository getPostsFromRemote return postList when remote service works as expected`() {
        val postResponseList: List<PostResponse> = BlogModelData.provideRemotePostsFromAssets()
        coEvery {
            restService.getPosts()
        } returns Response.success(postResponseList)
        lateinit var res: Either<Failure, List<PostModel>>

        runBlocking {
            res = repository.getPostsFromRemote()
        }

        assertTrue(postResponseList.isNotEmpty())
        assertTrue(res.isSuccess)
    }

    @Test
    fun `Assert repository getPostsFromRemote return network service call exception properly`() {
        val errorBody = "{\"error\": \"Invalid Request\",\"status_code\": 404}"
            .toResponseBody("application/json".toMediaTypeOrNull())

        coEvery {
            restService.getPosts()
        } throws HttpException(Response.error<Any>(404, errorBody))

        lateinit var res: Either<Failure, List<PostModel>>

        runBlocking {
            res = repository.getPostsFromRemote()
        }
        assertTrue(res.isError)
    }

    @Test
    fun `Assert repository getUser return user when remote service works as expected`() {
        val userResponse = BlogModelData.provideUserFromAssets()
        coEvery {
            restService.getUser(any())
        } returns Response.success(userResponse)
        lateinit var res: Either<Failure, UserModel>

        runBlocking {
            res = repository.getUser(1)
        }
        assertTrue(userResponse != null)
        assertTrue(res.isSuccess)
    }

    @Test
    fun `Assert repository getUser return network service call exception properly`() {
        val errorBody = "{\"error\": \"Invalid Request\",\"status_code\": 404}"
            .toResponseBody("application/json".toMediaTypeOrNull())

        coEvery {
            restService.getUser(any())
        } throws HttpException(Response.error<Any>(404, errorBody))

        lateinit var res: Either<Failure, UserModel>

        runBlocking {
            res = repository.getUser(1)
        }
        assertTrue(res.isError)
    }

    @Test
    fun `Assert repository getCommentsByPost return commentList when remote service works as expected`() {
        val commentResponseList: List<CommentResponse> = BlogModelData.provideCommentsFromAssets()
        coEvery {
            restService.getComments(any())
        } returns Response.success(commentResponseList)
        lateinit var res: Either<Failure, List<CommentModel>>

        runBlocking {
            res = repository.getCommentsByPost(1)
        }

        assertTrue(commentResponseList.isNotEmpty())
        assertTrue(res.isSuccess)
    }

    @Test
    fun `Assert repository getCommentsByPost return network service call exception properly`() {
        val errorBody = "{\"error\": \"Invalid Request\",\"status_code\": 404}"
            .toResponseBody("application/json".toMediaTypeOrNull())

        coEvery {
            restService.getComments(any())
        } throws HttpException(Response.error<Any>(404, errorBody))

        lateinit var res: Either<Failure, List<CommentModel>>

        runBlocking {
            res = repository.getCommentsByPost(1)
        }
        assertTrue(res.isError)
    }

    @Test
    fun `Assert repository getPostsFromLocal return postModel  when remote service works as expected`() {
        val postEntityList = listOf(
            PostEntity(id = 1, userId = 1, title = "title", body = "body", favorite = false),
            PostEntity(id = 2, userId = 1, title = "title", body = "body", favorite = false),
            PostEntity(id = 3, userId = 1, title = "title", body = "body", favorite = true)
        )
        coEvery {
            localDataSource.getPosts()
        } returns flow { emit(postEntityList) }

        lateinit var res: List<PostModel>

        runBlocking {
            repository.getPostsFromLocal().collect {
                res = it
            }
        }

        assertTrue(res.isNotEmpty())
        assertTrue(res.first().id == 1)
    }

    @Test
    fun `When saveAllPosts is called then deletePost is called once by localDS`() {
        coEvery { localDataSource.saveAllPosts(any()) } returns Unit
        runBlocking {
            repository.saveAllPosts(emptyList())
        }
        coVerify(exactly = 1) { localDataSource.saveAllPosts(any()) }
    }

    @Test
    fun `When deleteAllPosts is called then deleteAllPosts is called once by localDS`() {
        coEvery { localDataSource.deleteAllPosts() } returns Unit
        runBlocking {
            repository.deleteAllPosts()
        }
        coVerify(exactly = 1) { localDataSource.deleteAllPosts() }
    }

    @Test
    fun `When deletePost is called then deletePost is called once by localDS`() {
        coEvery { localDataSource.deletePost(any()) } returns Unit
        runBlocking {
            repository.deletePost(1)
        }
        coVerify(exactly = 1) { localDataSource.deletePost(any()) }
    }


    @Test
    fun `When updatePost is called then updatePost is called once by localDS`() {
        coEvery { localDataSource.updatePost(any()) } returns Unit
        runBlocking {
            repository.updatePost(PostModel(1, 1, "", "", false))
        }
        coVerify(exactly = 1) { localDataSource.updatePost(any()) }
    }

}