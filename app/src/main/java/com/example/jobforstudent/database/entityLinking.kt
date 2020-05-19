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
                parentColumn = "observersId",
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
                entityColumn = "observersId",
                associateBy = Junction(AdvertsSeekers::class)
        )
        val advertList: List<Advert>
)

data class UserWithEmployers(
        @Embedded
        val user: User,
        @Relation(
                parentColumn = "employersId",
                entity = Employer::class,
                entityColumn = "employerId"
        )
        val employerList: List<Employer>
)

data class UserWithSeekers(
        @Embedded
        val user: User,
        @Relation(
                parentColumn = "seekersId",
                entity = Seeker::class,
                entityColumn = "seekerId"
        )
        val seekerList: List<Seeker>
)