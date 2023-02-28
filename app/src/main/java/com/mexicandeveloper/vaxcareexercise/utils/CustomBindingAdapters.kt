package com.mexicandeveloper.vaxcareexercise.utils

import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mexicandeveloper.vaxcareexercise.R
import com.mexicandeveloper.vaxcareexercise.models.BookStatus
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("bindFormatDate")
fun TextView.bindFormatDate(lastEdited: String) {
    var outputDate: String? = null

    try {
        val curFormater = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SS'Z'", Locale.US)
        val postFormater = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val dateObj = curFormater.parse(lastEdited)
        outputDate = postFormater.format(dateObj)
        this.text = outputDate

    } catch (e: ParseException) {
        e.printStackTrace()
    }
}

@BindingAdapter("setStatus")
fun TextView.setStatus(status: String) {

    val data = status.split(",")
    val display = "displayText:"
    for (i in data) {
        if (i.contains(display)) {
            val text = i.substring(display.length)
            when(enumValueOf<BookStatus.StatusDisplayText>(text)){
                BookStatus.StatusDisplayText.CheckedOut ->this.setTextColor(ContextCompat.getColor(this.context,
                    R.color.dark))
                BookStatus.StatusDisplayText.OnShelf ->this.setTextColor(ContextCompat.getColor(this.context,
                    R.color.main))
            }
            this.text = text
        }
    }
}