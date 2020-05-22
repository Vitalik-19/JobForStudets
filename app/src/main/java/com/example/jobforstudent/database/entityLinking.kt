package com.example.jobforstudent.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EmployerWithAdverts(
        @Embedded
        val employer: Employer,
        @Relation(
                parentColumn = "employerId",
                entity = Advert::class,
                entityColumn = "ownerId"
        )
        val advertList: List<Advert>
)

data class AdvertWithSeekers(
        @Embedded
        val advert: Advert,
        @Relation(
                parentColumn = "advertId",
                entity = Seeker::class,
                entityColumn = "seekerId",
                associateBy = Junction(AdvertsSeekers::class)
        )
        val seekerList: List<Seeker>
)

data class SeekerWithAdverts(
        @Embedded
        val seeker: Seeker,
        @Relation(
                parentColumn = "seekerId",
                entity = Advert::class,
                entityColumn = "advertId",
                associateBy = Junction(AdvertsSeekers::class)
        )
        val advertList: List<Advert>
)