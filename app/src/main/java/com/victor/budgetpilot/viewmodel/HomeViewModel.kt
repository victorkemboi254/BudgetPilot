package com.victor.budgetpilot.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victor.budgetpilot.R
import com.victor.budgetpilot.data.ExpenseDataBase
import com.victor.budgetpilot.data.dao.ExpenseDao
import com.victor.budgetpilot.data.model.ExpenseEntity
import java.lang.IllegalArgumentException

class HomeViewModel(dao: ExpenseDao) : ViewModel() {
    val expenses = dao.getAllExpenses()

    fun getBalance(list: List<ExpenseEntity>): String {
        var totalIncome = 0.0
        var totalExpense = 0.0

        list.forEach {
            if (it.type == "Income") {
                totalIncome += it.amount
            } else {
                totalExpense += it.amount
            }
        }

        val balance = totalIncome - totalExpense
        return "Ksh $balance"
    }



    fun getTotalExpense(list: List<ExpenseEntity>): String {
        var total = 0.0
        list.forEach {
            if (it.type == "Expense") {
                total += it.amount
            }
        }
        return "Ksh $total"
    }




    fun getTotalIncome(list: List<ExpenseEntity>): String {
        var total = 0.0
        list.forEach {
            if (it.type == "Income"){
                total += it.amount
            }
        }
        return "Ksh $total"
    }


    fun getItemIcon(item: ExpenseEntity): Int {
        return when (item.category) {
            "Paypal" -> {
                R.drawable.item
            }
            "Netflix" -> {
                R.drawable.item
            }
            "Starbucks" -> {
                R.drawable.item
            }
            else -> {
                R.drawable.item
            }
        }

    }
}

class HomeViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

