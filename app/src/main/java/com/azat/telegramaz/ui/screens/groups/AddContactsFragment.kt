package com.azat.telegramaz.ui.screens.groups

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.azat.telegramaz.R
import com.azat.telegramaz.database.*
import com.azat.telegramaz.models.CommonModel
import com.azat.telegramaz.ui.screens.base.BaseFragment
import com.azat.telegramaz.utilits.*
import kotlinx.android.synthetic.main.fragment_add_contacts.*
import kotlinx.android.synthetic.main.fragment_main_list.*

/** Главный фрагмент,который содержит все чаты, груупы и каналы  с которым взаимодействует пользователь*/

class AddContactsFragment : BaseFragment(R.layout.fragment_add_contacts) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AddContactsAdapter
    private val mRefMainList = REF_DATABASE_ROOT.child(NODE_MAIN_LIST).child(CURRENT_UID)
    private val mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS)
    private val mRefMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID)
    private var mListItems = listOf<CommonModel>()

    override fun onResume() {
        listContacts.clear()
        super.onResume()
        APP_ACTIVITY.title = "Добавить участника"
        hideKeyboard()
        initRecyclerView()
        add_contacts_btn_next.setOnClickListener{
            if (listContacts.isEmpty()) showToast("Добавьте участника")
            else replaceFragment(CreateGroupFragment(listContacts))
        }
    }

    private fun initRecyclerView() {
        mRecyclerView = add_contacts_recycler_view
        mAdapter = AddContactsAdapter()

        /** 1-й запрос */
        mRefMainList.addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot ->
            mListItems = dataSnapshot.children.map { it.getCommonModel() }
            mListItems.forEach { model ->

                /** 2-й запрос */
                mRefUsers.child(model.id)
                    .addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot1 ->
                        val newModel = dataSnapshot1.getCommonModel()

                        /** 3-й запрос */
                        mRefMessages.child(model.id).limitToLast(1)
                            .addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot2 ->
                                val tempList = dataSnapshot2.children.map { it.getCommonModel() }

                                if (tempList.isEmpty()) {
                                    newModel.lastMessage = "Чат очищен"
                                } else {
                                    newModel.lastMessage = tempList[0].text
                                }

                                if (newModel.fullname.isEmpty()) {
                                    newModel.fullname = newModel.phone
                                }
                                mAdapter.updateListItems(newModel)
                            })
                    })
            }
        })
        mRecyclerView.adapter = mAdapter
    }
    companion object{
        val listContacts = mutableListOf<CommonModel>()
    }
}