package com.dipesh.kotlin.data.local

import com.dipesh.kotlin.model.tables.MemberTable
import io.reactivex.Single

/**
 * Created by Dipesh on 8/31/2017.
 */
class DbHelperImpl(private val mAppDatabase: AppDatabase) : DbHelper {
    override fun insertMembers(memberTables: List<MemberTable>): Single<List<Long>> {
        return mAppDatabase.memberDao().insertAll(memberTables)
    }

    override suspend fun getAllMembers(): List<MemberTable> {
        return mAppDatabase.memberDao().getAllMembers();
    }

    override fun updateMemberStatus(memberId: Int, status: String): Single<Int> {
        return mAppDatabase.memberDao().updateMemberStatus(memberId, status)
    }
}