package com.example.composechatapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "messages_table")
data class Message (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val text: String,
    val recipient_id: String,
    val timestamp: Long = Calendar.getInstance().timeInMillis,
    val isOutGoing: Boolean,
)

val replies_list = listOf(
    Message(
        id = 0,
        text = "This message won't be posted due to the set delay!",
        recipient_id = "User",
        isOutGoing = false
    ),
    Message(
        id = 0,
        text = "Hi!",
        recipient_id = "User",
        isOutGoing = false
    ),
    Message(
        id = 0,
        text = "All good on my side, and you?",
        recipient_id = "User",
        isOutGoing = false
    ),
    Message(
        id = 0,
        text = "Oh, that's good to hear! Nice to meet you!",
        recipient_id = "User",
        isOutGoing = false
    ),
    Message(
        id = 0,
        text = "How's your day been so far?",
        recipient_id = "User",
        isOutGoing = false
    ),
    Message(
        id = 0,
        text = "Oh nice! Mine's just been the usual as well, just finished work and going to the park now to walk the dog!",
        recipient_id = "User",
        isOutGoing = false
    )
)
