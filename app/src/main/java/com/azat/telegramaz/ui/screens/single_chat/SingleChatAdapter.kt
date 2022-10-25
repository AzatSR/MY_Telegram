package com.azat.telegramaz.ui.screens.single_chat

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azat.telegramaz.ui.message_recycler_view.view_holders.*
import com.azat.telegramaz.ui.message_recycler_view.views.MessageView

class SingleChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListMessageCache = mutableListOf<MessageView>()
    private var mListHolders = mutableListOf<MessageHolder>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppHolderFactory.getHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return mListMessageCache[position].getTypeView()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MessageHolder).drawMessage(mListMessageCache[position])
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        (holder as MessageHolder).onAttach(mListMessageCache[holder.adapterPosition])
        mListHolders.add((holder as MessageHolder))
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        (holder as MessageHolder).onDetach()
        mListHolders.remove((holder as MessageHolder))
        super.onViewDetachedFromWindow(holder)
    }

    override fun getItemCount(): Int = mListMessageCache.size


    fun addItemToBottom(
        item: MessageView,
        onSuccess: () -> Unit,
    ) {
        if (!mListMessageCache.contains(item)) {
            mListMessageCache.add(item)
            notifyItemInserted(mListMessageCache.size)
        }
        onSuccess()
    }

    fun addItemToTop(
        item: MessageView,
        onSuccess: () -> Unit,
    ) {
        if (!mListMessageCache.contains(item)) {
            mListMessageCache.add(item)
            mListMessageCache.sortBy { it.timeStamp }
            notifyItemInserted(0)
        }
        onSuccess()
    }

    fun onDestroy() {
        mListHolders.forEach {
            it.onDetach()
        }
    }
}

