package com.mexicandeveloper.vaxcareexercise.api

import com.mexicandeveloper.vaxcareexercise.models.Book
import retrofit2.Response

interface ApiHelper {
    suspend fun getBooks():Response<List<Book>>
}