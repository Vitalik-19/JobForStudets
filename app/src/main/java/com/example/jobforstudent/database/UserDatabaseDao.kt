package com.example.jobforstudent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
public interface UserDatabaseDao {

    @Insert
    fun insertEmployer(employer: Employer)

    @Insert
    fun insertSeeker(seeker: Seeker)

    @Query("SELECT * FROM Employer")
    fun getAllEmployer(): LiveData<List<Employer>>

    @Query("SELECT * FROM Employer WHERE loginEmployer = :login AND password = :password")
    fun getEmployer(login: String, password: String): Employer?

    @Query("SELECT * FROM Seeker")
    fun getAllSeeker(): LiveData<List<Seeker>>

    @Query("SELECT * FROM Seeker WHERE loginSeeker = :login AND password = :password")
    fun getSeeker(login: String, password: String): Seeker?

    @Transaction
    @Query("SELECT * FROM Employer")
    fun getEmployerWithAdverts(): LiveData<List<EmployerWithAdverts>>

    @Transaction
    @Query("SELECT * FROM Seeker")
    fun getSeekerWithAdverts(): List<SeekerWithAdverts>
}