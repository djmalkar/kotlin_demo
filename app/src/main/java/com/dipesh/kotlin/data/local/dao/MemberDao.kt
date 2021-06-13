package com.dipesh.kotlin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dipesh.kotlin.model.tables.MemberTable
import io.reactivex.Single

@Dao
interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(members: List<MemberTable>): Single<List<Long>>

    @Query("SELECT * FROM member")
    suspend fun getAllMembers(): List<MemberTable>

    @Query("UPDATE member SET memberInterested = :status WHERE id = :memberId")
    fun updateMemberStatus(memberId: Int, status: String): Single<Int>
}