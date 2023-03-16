package com.example.composechatapp

import android.app.Application
import androidx.lifecycle.*
import com.example.composechatapp.db.Message
import com.example.composechatapp.db.MessagesDatabase
import com.example.composechatapp.db.replies_list
import com.example.composechatapp.repo.MessagesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

open class MessageViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Message>>
    private val repository: MessagesRepo

    private var i = 0

    init {
        val messageDao = MessagesDatabase.getInstance(application).messageDao()
        repository = MessagesRepo(messageDao)
        readAllData = repository.readAllData
    }

    private fun addMessage(message: Message) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMessage(message)
        }
    }

    fun deleteAllMessages() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllMessages()
        }
    }

    fun addMessageToDb(messageText: String) {
        addMessage(Message(id = 0, text = messageText, recipient_id = "ChatBot", timestamp = Calendar.getInstance().timeInMillis, isOutGoing = true ))
    }

    fun addRandomMessageToDb() {
        android.os.Handler().postDelayed({
            if (i < replies_list.size) {
                addMessage(Message(id = 0, text = replies_list[i].text, recipient_id = "Bot", timestamp = Calendar.getInstance().timeInMillis, isOutGoing = false ))
            } else {
                addMessage(Message(id = 0, text = "This is an automatically generated reply!", recipient_id = "Bot", timestamp = Calendar.getInstance().timeInMillis, isOutGoing = false ))
            }
        }, 2000 )
        i++
    }

}

class MessageViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessageViewModel::class.java)) {
            return MessageViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}