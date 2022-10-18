package com.azat.telegramaz.ui.fragments.single_chat

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azat.telegramaz.database.CURRENT_UID
import com.azat.telegramaz.ui.fragments.message_recycler_view.view_holders.AppHolderFactory
import com.azat.telegramaz.ui.fragments.message_recycler_view.view_holders.HolderImageMessage
import com.azat.telegramaz.ui.fragments.message_recycler_view.view_holders.HolderTextMessage
import com.azat.telegramaz.ui.fragments.message_recycler_view.views.MessageView
import com.azat.telegramaz.utilits.asTime
import com.azat.telegramaz.utilits.downloadAndSetImage

class SingleChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mlistMessageCach = mutableListOf<MessageView>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppHolderFactory.getHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return mlistMessageCach[position].getTypeView()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HolderImageMessage -> drawMessageImage(holder, position)
            is HolderTextMessage -> drawMessageText(holder, position)
            else -> {}
        }
    }

    private fun drawMessageImage(holder: HolderImageMessage, position: Int) {

        if (mlistMessageCach[position].from == CURRENT_UID) {
            holder.blockReceivedImageMessage.visibility = View.GONE
            holder.blockUserImageMessage.visibility = View.VISIBLE
            holder.chatUserImage.downloadAndSetImage(mlistMessageCach[position].fileUrl)
            holder.chatUserImageMessageTime.text =
                mlistMessageCach[position].timeStamp.asTime()
        } else {
            holder.blockReceivedImageMessage.visibility = View.VISIBLE
            holder.blockUserImageMessage.visibility = View.GONE
            holder.chatReceivedImage.downloadAndSetImage(mlistMessageCach[position].fileUrl)
            holder.chatReceivedImageMessageTime.text =
                mlistMessageCach[position].timeStamp.asTime()
        }
    }

    private fun drawMessageText(holder: HolderTextMessage, position: Int) {

        if (mlistMessageCach[position].from == CURRENT_UID) {
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.blockReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mlistMessageCach[position].text
            holder.chatUserMessageTime.text =
                mlistMessageCach[position].timeStamp.asTime()
        } else {
            holder.blockUserMessage.visibility = View.GONE
            holder.blockReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.text = mlistMessageCach[position].text
            holder.chatReceivedMessageTime.text =
                mlistMessageCach[position].timeStamp.asTime()
        }
    }

    override fun getItemCount(): Int = mlistMessageCach.size


    fun addItemToBottom(
        item: MessageView,
        onSuccess: () -> Unit,
    ) {
        if (!mlistMessageCach.contains(item)) {
            mlistMessageCach.add(item)
            notifyItemInserted(mlistMessageCach.size)
        }
        onSuccess()
    }

    fun addItemToTop(
        item: MessageView,
        onSuccess: () -> Unit,
    ) {
        if (!mlistMessageCach.contains(item)) {
            mlistMessageCach.add(item)
            mlistMessageCach.sortBy { it.timeStamp }
            notifyItemInserted(0)
        }
        onSuccess()
    }
}

