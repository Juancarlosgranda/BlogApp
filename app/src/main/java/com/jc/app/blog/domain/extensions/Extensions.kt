package com.jc.app.blog.domain.extensions

import com.jc.app.blog.domain.utils.Either

fun <R> R.toSuccess(): Either.Success<R> {
    return Either.Success(this)
}

