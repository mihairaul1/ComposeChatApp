package com.example.composechatapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDao {
    @Query("SELECT * FROM messages_table")
    fun getAllMessages(): LiveData<List<Message>>

    @Query("SELECT * FROM messages_table")
    fun getAllMessagesForTesting(): List<Message>

    @Insert
    suspend fun insert(message: Message)

    @Query("DELETE FROM messages_table")
    suspend fun deleteAllMessages()
}