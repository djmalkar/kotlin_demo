package com.dipesh.kotlin.data.local

import com.dipesh.kotlin.model.tables.MemberTable
import io.reactivex.Single

/**
 * Created by Dipesh on 8/28/2017.
 */
interface DbHelper {
    fun insertMembers(memberTables: List<MemberTable>): Single<List<Long>>
    suspend fun getAllMembers(): List<MemberTable>
    fun updateMemberStatus(memberId: Int, status: String): Single<Int>
}