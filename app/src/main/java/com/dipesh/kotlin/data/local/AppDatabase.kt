
package com.dipesh.kotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dipesh.kotlin.data.local.dao.MemberDao
import com.dipesh.kotlin.model.tables.MemberTable

@Database(entities = [MemberTable::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun memberDao(): MemberDao
}