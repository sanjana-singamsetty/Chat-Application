package com.example.chattingapplication

enum class MessageType {
    TEXT,
    IMAGE,
    VIDEO,
    // Add more message types as needed
}

enum class MessageStatus {
    SENT,
    DELIVERED,
    READ,
    // Add more message statuses as needed
}

data class Attachment(
    val type: AttachmentType,
    val url: String
)

enum class AttachmentType {
    IMAGE,
    VIDEO,
    FILE,
    // Add more attachment types as needed
}

class Message {
    var message: String? = ""
    var senderId: String? = ""
    var timestamp: Long = 0
    var messageType: MessageType = MessageType.TEXT
    var messageStatus: MessageStatus = MessageStatus.SENT
    var attachments: List<Attachment> = emptyList()
    var repliedToMessageId: String? = null // ID of the message being replied to
    var isEditable: Boolean = false // Indicates whether the message is editable
    var originalMessage: String? = null // Stores the original message text for editing

    constructor(message: String?, senderId: String?) {
        this.message = message
        this.senderId = senderId
    }

    constructor()
}
