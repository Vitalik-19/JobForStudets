package com.example.jobforstudent.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface UserDatabaseDao {

    // Добавление user в бд
    @Insert
    fun insertUser(user: User)

    @Insert
    fun insertEmployer(employer: Employer)

    @Insert
    fun insertSeeker(seeker: Seeker)

    // Удаление user из бд
    @Query("DELETE FROM User")
    fun clear()

    // Обновление user в бд
    @Update
    fun update(user: User)

    @Query("SELECT * FROM Employer")
    fun getAllEmployer(): LiveData<List<Employer>>

    @Query("SELECT * FROM Employer WHERE loginEmployer = :login")
    fun getEmployer(login: String): Employer?

    @Query("SELECT * FROM Seeker")
    fun getAllSeeker(): LiveData<List<Seeker>>

    @Query("SELECT * FROM Seeker WHERE loginSeeker = :login")
    fun getSeeker(login: String): Seeker?

    @Transaction
    @Query("SELECT * FROM User")
    fun getUserWithEmployers(): List<UserWithEmployers>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUserWithSeekers(): List<UserWithSeekers>

    @Transaction
    @Query("SELECT * FROM Employer")
    fun getEmployerWithAdverts(): LiveData<List<EmployerWithAdverts>>

    @Transaction
    @Query("SELECT * FROM Seeker")
    fun getSeekerWithAdverts(): List<SeekerWithAdverts>

}