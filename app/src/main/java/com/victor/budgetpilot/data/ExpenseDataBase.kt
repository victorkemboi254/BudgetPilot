package com.victor.budgetpilot.data

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.victor.budgetpilot.data.dao.ExpenseDao
import com.victor.budgetpilot.data.model.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val currentTimeMillis = System.currentTimeMillis()
@SuppressLint("ConstantLocale")
val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
val dateString: String = dateFormat.format(Date(currentTimeMillis))

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class ExpenseDataBase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        private const val DATABASE_NAME = "expense_database"

        @JvmStatic
        fun getDatabase(context: Context): ExpenseDataBase {
            return Room.databaseBuilder(
                context,
                ExpenseDataBase::class.java,
                DATABASE_NAME
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    InitBasicData(context)
                }

                private fun InitBasicData(context: Context) {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val dao = getDatabase(context).expenseDao()
                            Log.d("DatabaseInit", "Inserting initial data with date: $dateString")
                            dao.insertExpense(ExpenseEntity(1, "Salary", 50000.00, dateString, "Salary", "Income"))
                            dao.insertExpense(ExpenseEntity(2, "Paypal", 3000.00, dateString, "Paypal", "Income"))
                            dao.insertExpense(ExpenseEntity(3, "Netflix", 1500.00, dateString, "Netflix", "Expense"))
                            dao.insertExpense(ExpenseEntity(4, "Starbucks", 350.00, dateString, "Starbucks", "Expense"))
                        } catch (e: Exception) {
                            Log.e("DatabaseInit", "Error inserting initial data", e)
                        }
                    }
                }
            }).build()
        }
    }
}
