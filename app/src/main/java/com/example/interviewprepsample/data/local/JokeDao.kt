package com.example.interviewprepsample.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {
    @Query("SELECT * from JOKE_TABLE")
    fun observeAll(): Flow<List<JokeEntity>>

    @Query("SELECT * from JOKE_TABLE WHERE isFavourite=0")
    fun getFavouriteJokes(): Flow<List<JokeEntity>>

    @Query("SELECT * from JOKE_TABLE")
    suspend fun getAllJokes(): List<JokeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveJoke(jokeEntity: JokeEntity)

    @Query("UPDATE joke_table SET isFavourite=1 WHERE id=:id")
    suspend fun markJokeAsFavourite(id: Long)

    @Query("DELETE from JOKE_TABLE where id=:id")
    suspend fun deleteJokeById(id: Long)
}