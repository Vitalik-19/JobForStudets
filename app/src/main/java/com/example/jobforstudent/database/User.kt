package com.example.jobforstudent.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
        @PrimaryKey(autoGenerate = true)
        val userId: Long,
        val position: Boolean,
        val employersId: Long,
        val seekersId: Long
)

@Entity
data class Employer(
        @PrimaryKey(autoGenerate = true)
        var employerId: Long = 0L,
        var loginEmployer: String,
        var password: String
)

@Entity
data class Seeker(
        @PrimaryKey(autoGenerate = true)
        val seekerId: Long,
        val loginSeeker: String,
        val password: String
)


