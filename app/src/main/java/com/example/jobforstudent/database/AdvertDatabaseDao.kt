package com.example.jobforstudent.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface AdvertDatabaseDao {

    // Добавление advert в бд
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(advert: Advert)

    // Удаление advert из бд
    @Query("DELETE FROM Advert WHERE advertId = :key")
    fun clear(key: Long)

    // Обновление advert в бд
    @Update
    fun update(advert: Advert)

    // Получение всех Person из бд за ключем
    @Query("SELECT * from Advert WHERE advertId = :key")
    fun get(key: Long): Advert?

    @Query("SELECT * FROM Advert ORDER BY advertId DESC")
    fun getAllAdvert(): LiveData<List<Advert>>

    @Query("SELECT * FROM Advert ORDER BY advertId DESC LIMIT 1")
    fun getCreateAdvert(): Advert?

    @Transaction
    @Query("SELECT * FROM Advert WHERE advertId = :key")
    fun getAdvertWithSeekers(key: Long): AdvertWithSeekers?

    @Transaction
    @Query("SELECT * FROM Seeker WHERE seekerId = :key")
    fun getSeekerWithAdverts(key: Long): SeekerWithAdverts?

    @Query("SELECT * FROM SessionEmployer ORDER BY sessionId DESC LIMIT 1")
    fun getSessionEmployer(): SessionEmployer?

    @Query("SELECT * FROM SessionSeeker ORDER BY sessionId DESC LIMIT 1")
    fun getSessionSeeker(): SessionSeeker?

    @Query("SELECT * FROM Seeker WHERE seekerId = :key")
    fun getSeekerById(key: Long): Seeker?

    @Insert
    fun insertAdvertsSeekers(advertsSeekers: AdvertsSeekers)

    @Delete
    fun deleteAdvertsSeekers(advertsSeekers: AdvertsSeekers)
}