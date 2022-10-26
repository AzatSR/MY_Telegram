package com.azat.telegramaz.ui.message_recycler_view.views

import com.azat.telegramaz.models.CommonModel
import com.azat.telegramaz.utilits.TYPE_MESSAGE_FILE
import com.azat.telegramaz.utilits.TYPE_MESSAGE_IMAGE
import com.azat.telegramaz.utilits.TYPE_MESSAGE_VOICE

class AppViewFactory {
    companion object{
        fun getView(message: CommonModel): MessageView {
            return when(message.type){
                TYPE_MESSAGE_IMAGE -> ViewImageMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.photoUrl
                )
                TYPE_MESSAGE_VOICE -> ViewVoiceMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.photoUrl
                )
                TYPE_MESSAGE_FILE -> ViewFileMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.photoUrl,
                    message.text
                )
                else -> ViewTextMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.photoUrl,
                    message.text
                )
            }
        }
    }
}