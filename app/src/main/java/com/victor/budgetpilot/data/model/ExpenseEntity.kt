package com.victor.budgetpilot.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "expense_table")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title:String,
    val amount:Double,
    val date: String,
    val category: String,
    val type:String
)
