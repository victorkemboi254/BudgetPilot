package com.victor.budgetpilot

import java.text.SimpleDateFormat
import java.util.*



//object Utils {
//    fun formatDateToHumanReadableForm(timestamp: String): String {
//        val date = (timestamp)
//        val format = SimpleDateFormat("yyyy-MM-dd ", Locale.getDefault())
//        return format.format(date)
//    }
//}

object Utils {
    fun formatDateToHumanReadableForm(date: String): String {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val dateObj = Date(date.toLong())
            sdf.format(dateObj)
        } catch (e: Exception) {
            // Handle invalid date formats or parsing errors
            "Invalid date"
        }
    }
}
