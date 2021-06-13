package com.dipesh.kotlin.model.networkschemas

class MemberLocation {
    var street: LocationStreet = LocationStreet()
    var city: String = ""
    var state: String = ""
    var country: String = ""
    var postcode: String = ""
    var coordinates: LocationCordinates = LocationCordinates()
    var timezone: LocationTimezone = LocationTimezone()

    inner class LocationStreet {
        var number: String = ""
        var name: String = ""
    }

    inner class LocationCordinates {
        var latitude: String = ""
        var longitude: String = ""
    }

    inner class LocationTimezone {
        var offset: String = ""
        var description: String = ""
    }
}