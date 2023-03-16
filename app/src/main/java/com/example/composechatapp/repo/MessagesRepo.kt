package com.example.composechatapp.repo

import androidx.lifecycle.LiveData
import com.example.composechatapp.db.MessageDao
import com.example.composechatapp.db.Message

class MessagesRepo(private val messageDao: MessageDao) {
    val readAllData : LiveData<List<Message>> = messageDao.getAllMessages()

    suspend fun addMessage(message: Message) {
        messageDao.insert(message)
    }

    suspend fun deleteAllMessages() {
        messageDao.deleteAllMessages()
    }
}