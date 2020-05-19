package com.example.jobforstudent.database

import androidx.room.*

@Dao
public interface UserDatabaseDao {

    // Добавление user в бд
    @Insert
    fun insert(user: User)

    // Удаление user из бд
    @Query("DELETE FROM User")
    fun clear()

    // Обновление user в бд
    @Update
    fun update(user: User)

    @Transaction
    @Query("SELECT * FROM User")
    fun getUserWithEmployers(): List<UserWithEmployers>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUserWithSeekers(): List<UserWithSeekers>

    @Transaction
    @Query("SELECT * FROM Employer")
    fun getEmployerWithAdverts(): List<EmployerWithAdverts>

    @Transaction
    @Query("SELECT * FROM Seeker")
    fun getSeekerWithAdverts(): List<SeekerWithAdverts>

}