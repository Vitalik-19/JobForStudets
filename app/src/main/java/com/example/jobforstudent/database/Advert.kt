package com.example.jobforstudent.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Advert(
        @PrimaryKey(autoGenerate = true)
        var advertId: Long = 0L,
        var workName: String = "Work Name",
        var salary: Int = 0,
        var companyName: String = "Company Name",
        var location: String = "Location",
        var observersId: Long = 0L,
        var ownerId: Long = 0L
)

@Entity(primaryKeys = ["seekerId", "observersId"])
data class AdvertsSeekers(
        val seekerId: Long,
        val observersId: Long
)