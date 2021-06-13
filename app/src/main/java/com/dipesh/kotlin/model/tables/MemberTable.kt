package com.dipesh.kotlin.model.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member")
data class MemberTable(
        @PrimaryKey(autoGenerate = true) var id: Int,
        val name: String,
        val gender: String,
        val address: String,
        val email: String,
        val age: Int,
        val phone: String,
        val picture: String,
        var memberInterested: String
)