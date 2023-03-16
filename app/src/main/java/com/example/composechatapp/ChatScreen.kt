package com.example.composechatapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composechatapp.db.Message

var user_input = mutableStateOf("")

private val BotChatBubbleShape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 0.dp)
private val AuthorChatBubbleShape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 16.dp)

@Composable
fun ActionBarSection(
    username: String,
    profilePicture: Painter
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        backgroundColor = Color(0xFFFAFAFA),
        elevation = 4.dp
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = profilePicture,
                contentDescription = "profile picture",
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = username, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun ChatSection(
    modifier: Modifier,
    list: List<Message>
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        reverseLayout = true
    ) {
        items(list) { chat ->

            MessageItem(
                messageText = chat.text,
                time = chat.timestamp,
                isOutgoing = chat.isOutGoing)

        }
    }
}

@Composable
fun MessageItem(
    messageText: String?,
    time: Long,
    isOutgoing: Boolean
){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isOutgoing) Alignment.End else Alignment.Start
    ) {
        if (messageText != null) {
            if (messageText.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .background(
                            if (isOutgoing) MaterialTheme.colors.primary else Color(0xFFd3d3d3),
                            shape = if (isOutgoing) AuthorChatBubbleShape else BotChatBubbleShape
                        )
                        .padding(
                            vertical = 8.dp,
                            horizontal = 16.dp
                        )
                ) {
                    Text(
                        text = messageText,
                        color = if (isOutgoing) Color.White else Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Composable
fun MessageInputSection(
    messageViewModel: MessageViewModel,
) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = Color.White,
            elevation = 10.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    placeholder = {
                        Text(text = "Message...")
                    },
                    value = user_input.value,
                    onValueChange = {
                        user_input.value = it
                    },
                    shape = RoundedCornerShape(25.dp),
                    modifier = Modifier
                        .wrapContentHeight()
                        .align(alignment = Alignment.CenterVertically)
                        .fillMaxWidth(0.85f),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = MaterialTheme.colors.primary
                    ),
                    singleLine = false,
                    maxLines = 4
                )

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (user_input.value.isNotEmpty()) {
                            messageViewModel.addMessageToDb(user_input.value)
                            user_input.value = ""
                            messageViewModel.addRandomMessageToDb()
                        }
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    modifier = Modifier
                        .height(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = "send button"
                    )
                }
            }
        }
    }
}