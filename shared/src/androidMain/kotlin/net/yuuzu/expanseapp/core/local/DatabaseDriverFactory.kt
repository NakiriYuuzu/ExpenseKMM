package net.yuuzu.expanseapp.core.local

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import net.yuuzu.expanseapp.data.ExpenseDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            ExpenseDatabase.Schema,
            context,
            "expense.db"
        )
    }
}