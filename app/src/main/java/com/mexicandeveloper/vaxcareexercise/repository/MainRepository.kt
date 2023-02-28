package com.mexicandeveloper.vaxcareexercise.repository

import com.mexicandeveloper.vaxcareexercise.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getBooks() = apiHelper.getBooks()
}