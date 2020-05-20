package com.example.jobforstudent.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey(autoGenerate = true)
        val userId: Long = 0L,
        var position: Boolean = true,
        var employersId: Long = 0L,
        var seekersId: Long = 0L
)

@Entity
data class Employer(
        @PrimaryKey(autoGenerate = true)
        var employerId: Long = 0L,
        var loginEmployer: String = "",
        var password: String = ""
)

@Entity
data class Seeker(
        @PrimaryKey(autoGenerate = true)
        var seekerId: Long = 0L,
        var loginSeeker: String = "",
        var password: String = ""
)


