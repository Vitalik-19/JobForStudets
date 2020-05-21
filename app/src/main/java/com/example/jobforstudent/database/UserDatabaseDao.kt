package com.example.jobforstudent.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface UserDatabaseDao {

    @Insert
    fun insertEmployer(employer: Employer)

    @Insert
    fun insertSeeker(seeker: Seeker)

    @Insert
    fun insertAdvert(advert: Advert)

    @Update
    fun updateAdvert(advert: Advert)

    @Insert
    fun insertSessionSeeker(session: SessionSeeker)

    @Insert
    fun insertSessionEmployer(session: SessionEmployer)

    @Query("DELETE FROM SessionSeeker")
    fun deleteSessionSeeker()

    @Query("DELETE FROM SessionEmployer")
    fun deleteSessionEmployer()

    @Query("SELECT * FROM SessionSeeker ORDER BY sessionId DESC LIMIT 1")
    fun getSessionSeeker(): SessionSeeker?

    @Query("SELECT * FROM SessionEmployer ORDER BY sessionId DESC LIMIT 1")
    fun getSessionEmployer(): SessionEmployer?

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

    @Transaction
    fun getInsertSeekerWithAdverts(seeker: Seeker, advert: Advert) {
        insertSeeker(seeker)
        insertAdvert(advert)
    }

    @Transaction
    fun getUpdateObservableAdverts(advert: Advert) {
        updateAdvert(advert)
    }

}