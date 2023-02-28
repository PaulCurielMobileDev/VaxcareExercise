package com.mexicandeveloper.vaxcareexercise.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val id:Int,
    val title:String,
    val author:String,
    val status:String,
    val fee:Double,
    val lastEdited:String
):Parcelable
