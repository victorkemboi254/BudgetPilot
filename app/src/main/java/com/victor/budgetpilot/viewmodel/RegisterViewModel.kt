package com.victor.budgetpilot.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victor.budgetpilot.data.ExpenseDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val context: Context) : ViewModel() {

    private val expenseDao = ExpenseDataBase.getDatabase(context).expenseDao()

    fun registerNewUser() {
        // Perform registration logic

        // Clear existing data after registration
        viewModelScope.launch(Dispatchers.IO) {
            expenseDao.deleteAllExpenses()
            // Add similar calls to clear other types of transactions if needed
        }
    }
}
