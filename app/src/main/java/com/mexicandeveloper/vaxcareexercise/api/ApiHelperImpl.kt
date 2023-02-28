package com.mexicandeveloper.vaxcareexercise.api

import com.mexicandeveloper.vaxcareexercise.models.Book
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getBooks(): Response<List<Book>> = apiService.getBooks()

}