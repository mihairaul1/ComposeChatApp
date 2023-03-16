package com.example.composechatapp.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Calendar

@RunWith(AndroidJUnit4::class)
class MessageDaoTest {

    private lateinit var database: MessagesDatabase
    private lateinit var messageDao: MessageDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context = context,
            MessagesDatabase::class.java
        ).build()

        messageDao = database.messageDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun getAllShouldReturnAllItemsFromTheDatabase() = runBlocking {
        val messageToInsert = Message(
            1,
            "Test message",
            "Test",
            Calendar.getInstance().timeInMillis,
        true)
        messageDao.insert(messageToInsert)

        val messagesFromDb = messageDao.getAllMessagesForTesting()
        assertEquals(messageToInsert.id, messagesFromDb[0].id)
        assertEquals(messageToInsert.text, messagesFromDb[0].text)
        assertEquals(messageToInsert.recipient_id, messagesFromDb[0].recipient_id)
        assertEquals(messageToInsert.timestamp, messagesFromDb[0].timestamp)
        assertEquals(listOf(messageToInsert), messagesFromDb)
    }

    @Test
    fun deleteAllMessagesShouldClearTheDatabase() = runBlocking {
        val messageToInsert = Message(
            1,
            "Test message",
            "Test",
            Calendar.getInstance().timeInMillis,
            true)
        messageDao.insert(messageToInsert)

        var messagesFromDb = messageDao.getAllMessagesForTesting()
        assertEquals(listOf(messageToInsert), messagesFromDb)

        messageDao.deleteAllMessages()
        messagesFromDb = messageDao.getAllMessagesForTesting()
        assertTrue(messagesFromDb.isEmpty())
    }

}