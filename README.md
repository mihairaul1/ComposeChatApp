# ComposeChatApp

## Description
This Android application is designed to showcase how a simple, single-activity Chat App would
look like. The app uses A Room DB to store the messages and it is also set up to provide a 
list of predefined replies when a message is sent, therefore attempting to mock the receiver
for the sen messages.
- Disclaimer: This is my first ever app written in compose as I have had no compose exposure up until now
- This app was written from scratch in approximately 2 hours

## Assumptions
As part of the assumptions made for this app, they are as follows:
- The app should use a way of storing the data: RoomDB
- The app must use an efficient way of displaying a list of items: LazyColumn
- The app must be able to resume the conversation where it was left of upon relaunch

## Features
The Android application provides the following features:
- Displays sent and received messages in different coloured bubbles, at opposite ends of the screen
- Stores the messages in a local database using Room
- Uses Repository Pattern
- Use JUnit4 to test the DatabaseDao functionality

- Has a list of automated replies as follows:
  - "Hi!"
  - "All good on my side, and you?"
  - "Oh, that's good to hear! Nice to meet you!"
  - "How's your day been so far?"
  - "Oh nice! Mine's just been the usual as well, just finished work and going to the park now to walk the dog!"
  
- The above list is currently limited to 6 replies, however it can be modified with any
  other possible replies by changing the `replies_list` that can be found in `Message.kt`
- Once the replies list runs out of items, the following reply will be shown:
  - "This is an automatically generated reply!"

## Installation
To install the Android application, follow these steps:
- Open ComposeChatApp project in Android Studio
- Run the app on Android Device or emulator

## Usage
To use the Android application, follow these steps:
- Open the app on an Android device or emulator
- The app will display a Chat View with an input text field at the bottom
- Tap the input text and compose your message
- Tap the send button and see that your message appears in a bubble and you are 
  able to see an automated reply 2 seconds after it is sent

## Dependencies
The Android application uses the following dependencies:
- Compose
- Room
- LiveData
- JUnit4
- ViewModel
- Coroutines

## Possible improvements given more time spent:
- Improvements on the UI
- Adding different handling for when a message should have a tail and when not
- Sectioning the chat using {day}{timestamp} when conditions are met
- Addition of more Unit tests around Repository and ViewModel
- Addition of UI tests
