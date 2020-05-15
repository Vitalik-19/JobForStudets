package com.example.jobforstudent.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "advert_info_table")
data class Advert(
        @PrimaryKey(autoGenerate = true)
        var advertId: Long = 0L,
        @ColumnInfo(name = "work_name")
        var workName: String = "Work Name",
        @ColumnInfo(name = "salary")
        var salary: Int = 0,
        @ColumnInfo(name = "company_name")
        var companyName: String = "Company Name",
        @ColumnInfo(name = "location")
        var location: String = "Location"
)