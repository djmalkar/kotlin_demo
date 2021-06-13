package com.dipesh.kotlin.model.networkschemas

class SingleMember {
    var Gender: String = ""
    var name: MemberName = MemberName()
    var location: MemberLocation = MemberLocation()
    var email: String = ""
    var login: MemberLogin = MemberLogin()
    var dob: MemberDob = MemberDob()
    var registered: MemberRegistered = MemberRegistered()
    var phone: String = ""
    var cell: String = ""
    var id: MemberId = MemberId()
    var picture: MemberPicture = MemberPicture()

    inner class MemberName {
        var title: String = ""
        var first: String = ""
        var last: String = ""
    }

    inner class MemberLogin {
        var uuid: String = ""
        var username: String = ""
        var password: String = ""
        var salt: String = ""
        var md5: String = ""
        var sha1: String = ""
        var sha256: String = ""
    }

    inner class MemberDob {
        var date: String = ""
        var age = 0
    }

    inner class MemberRegistered {
        var date: String = ""
        var age: String = ""
    }

    inner class MemberId {
        var name: String = ""
        var value: String = ""
    }

    inner class MemberPicture {
        var large: String = ""
        var medium: String = ""
        var thumbnail: String = ""
    }
}