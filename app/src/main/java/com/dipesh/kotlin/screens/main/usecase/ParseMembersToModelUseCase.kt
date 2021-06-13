package com.dipesh.kotlin.screens.main.usecase

import com.dipesh.kotlin.model.networkschemas.MembersListResult
import com.dipesh.kotlin.model.tables.MemberTable
import java.util.*
import javax.inject.Inject

class ParseMembersToModelUseCase @Inject constructor() {
    fun parseSchema(membersListResult: MembersListResult): List<MemberTable> {
        val memberTables: MutableList<MemberTable> = ArrayList()
        for (singleMember in membersListResult.results) {
            val memberTable = MemberTable(
                    0,
                    singleMember.name?.first + " " + singleMember.name.last,
                    singleMember.Gender,
                    (singleMember.location.street.number + ", " + singleMember.location.street.name
                            + ", " + singleMember.location.city + ", "
                            + singleMember.location.state + ", " + singleMember.location.country),
                    singleMember.email,
                    singleMember.dob.age,
                    singleMember.phone,
                    singleMember.picture.large,
                    ""
            )
            memberTables.add(memberTable)
        }
        return memberTables
    }
}