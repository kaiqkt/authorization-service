package me.kaique.application.web.validator

import io.javalin.http.Context

fun Context.notAcceptable() {
    status(406)
    json("Invalid value for Content-Type header")
    contentType("application/json")
}

fun Context.getContentTypeWithoutCharset(): String? = this.contentType()?.split(";")?.first()


