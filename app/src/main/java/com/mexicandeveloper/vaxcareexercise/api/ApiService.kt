package com.mexicandeveloper.vaxcareexercise.api

import com.mexicandeveloper.vaxcareexercise.models.Book
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("02cb5f20bf3398ca46884e6c8e18ce89/raw/462e69054eaef1ac92386c549f66324e4b89dbde/local-database.json")
    suspend fun getBooks(): Response<List<Book>>

}