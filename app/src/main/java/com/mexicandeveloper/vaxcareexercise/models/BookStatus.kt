package com.mexicandeveloper.vaxcareexercise.models

data class BookStatus(
    val id: Int,
    val displayText: StatusDisplayText,
    val timeCheckedIn: String?,
    val timeCheckedOut: String?,
    val dueDate: String?
) {

    enum class StatusDisplayText {
        OnShelf,
        CheckedOut
    }
}