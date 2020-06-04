package com.example.jobforstudent.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public abstract class UserDatabaseDao {

    @Insert
    abstract fun insertEmployer(employer: Employer)

    @Insert
    abstract fun insertEmployerId(employer: Employer): Long

    @Insert
    abstract fun insertSeeker(seeker: Seeker)

    @Insert
    abstract fun insertAdvert(advert: Advert)

    @Insert
    abstract fun insertAdverts(adverts: List<Advert>)

    @Insert
    abstract fun insertSessionSeeker(session: SessionSeeker)

    @Insert
    abstract fun insertSessionEmployer(session: SessionEmployer)

    @Update
    abstract fun updateAdvert(advert: Advert)

    @Update
    abstract fun updateAdverts(adverts: List<Advert>)

    @Query("DELETE FROM SessionSeeker")
    abstract fun deleteSessionSeeker()

    @Query("DELETE FROM SessionEmployer")
    abstract fun deleteSessionEmployer()

    @Query("SELECT * FROM SessionSeeker ORDER BY sessionId DESC LIMIT 1")
    abstract fun getSessionSeeker(): SessionSeeker?

    @Query("SELECT * FROM SessionEmployer ORDER BY sessionId DESC LIMIT 1")
    abstract fun getSessionEmployer(): SessionEmployer?

    @Query("SELECT * FROM Employer")
    abstract fun getAllEmployer(): LiveData<List<Employer>>

    @Query("SELECT * FROM Employer WHERE loginEmployer = :login AND password = :password")
    abstract fun getEmployer(login: String, password: String): Employer?

    @Query("SELECT * FROM Employer WHERE employerId = :key")
    abstract fun getEmployerById(key: Long): Employer?

    @Query("SELECT * FROM Seeker WHERE loginSeeker = :login AND password = :password")
    abstract fun getSeeker(login: String, password: String): Seeker?

    @Query("SELECT * FROM Seeker WHERE seekerId = :key")
    abstract fun getSeekerById(key: Long): Seeker?

    @Transaction
    @Query("SELECT * FROM Employer WHERE employerId = :key")
    abstract fun getEmployerWithAdverts(key: Long): EmployerWithAdverts?

    @Transaction
    @Query("SELECT * FROM Seeker WHERE seekerId = :key")
    abstract fun getSeekerWithAdverts(key: Long): SeekerWithAdverts?
}