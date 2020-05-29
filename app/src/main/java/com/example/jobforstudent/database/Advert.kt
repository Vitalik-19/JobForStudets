package com.example.jobforstudent.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Advert(
        @PrimaryKey(autoGenerate = true)
        var advertId: Long = 0,
        var workName: String = "Work Name",
        var salary: Int = 0,
        var companyName: String = "Company Name",
        var location: String = "Location",
        var description: String = "Description",
        var phone: String = "",
        var observersId: Long = 0,
        var ownerId: Long = 0L
)

@Entity(primaryKeys = ["seekerId", "advertId"])
data class AdvertsSeekers(
        var seekerId: Long,
        var advertId: Long
)