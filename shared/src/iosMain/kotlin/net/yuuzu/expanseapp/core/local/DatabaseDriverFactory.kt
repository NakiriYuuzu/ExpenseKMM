package net.yuuzu.expanseapp.core.local

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import net.yuuzu.expanseapp.data.ExpenseDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(ExpenseDatabase.Schema, "expense.db")
    }
}