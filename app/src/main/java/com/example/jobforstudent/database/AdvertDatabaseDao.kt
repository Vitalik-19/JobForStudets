package com.example.jobforstudent.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface AdvertDatabaseDao {

    // Добавление advert в бд
    @Insert
    fun insert(advert: Advert)

    // Удаление advert из бд
    @Query("DELETE FROM advert_info_table")
    fun clear()

    // Обновление advert в бд
    @Update
    fun update(advert: Advert)

    // Получение всех Person из бд за ключем
    @Query("SELECT * from advert_info_table WHERE advertId = :key")
    fun get(key: Long): Advert?

    @Query("SELECT * FROM advert_info_table ORDER BY advertId DESC")
    fun getAllAdvert(): LiveData<List<Advert>>

    @Query("SELECT * FROM advert_info_table ORDER BY advertId DESC LIMIT 1")
    fun getCreateAdvert(): Advert?
}