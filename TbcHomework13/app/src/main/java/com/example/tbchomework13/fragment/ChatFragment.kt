package com.example.tbchomework13.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework13.data.MessageData
import com.example.tbchomework13.R
import com.example.tbchomework13.databinding.FragmentChatBinding
import com.example.tbchomework13.model.ChatMessage
import com.example.tbchomework13.model.MessageType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ChatFragment : Fragment() {
    private var _binding:FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ChatRecyclerView
    private lateinit var status : MessageType
    private var index:Int = 4
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUp(){
        setUpRecycler()
        listeners()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun listeners(){
        binding.btnSend.setOnClickListener{
            submitNewMessage()
        }
        binding.btnBack.setOnClickListener {
            requireActivity().finishAffinity()
        }
    }

    private fun setUpRecycler(){
        adapter = ChatRecyclerView()
        binding.apply {
            chatRecyclerView.layoutManager = LinearLayoutManager(context)
            chatRecyclerView.adapter = adapter
        }
        adapter.setData(MessageData.messageList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getCurrentTime():String{
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val formattedTime = currentTime.format(formatter)
        return formattedTime

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun submitNewMessage(){
        if(binding.etChat.text?.isNotEmpty() == true){
            val message = binding.etChat.text.toString()
            d("q12345","${MessageData.messageList}")
            /* var newList = MessageData.messageList*/
            d("q12345","${MessageData.messageList.size}")
            if (MessageData.messageList.size % 2 == 0) {
                status = MessageType.FRIEND
            }else {
                status = MessageType.USER
            }
            index += 1
            val realTime = getCurrentTime()
                                                          // add item on top of the chat
          /*  MessageData.messageList.add(0,ChatMessage(index,message,realTime, status)) // with this " message " is on Top of the conversation
             adapter.notifyItemInserted(0)
             binding.chatRecyclerView.scrollToPosition(0) // focus on position 0*/

                                                       // add item just like in chatting apps
           MessageData.messageList.add(ChatMessage(index,message,realTime, status)) // with this, it is normal , just like normal chatting app
           /* adapter.setData(MessageData.messageList.toMutableList())*/ // this is correct version but not working because scrollposition is faster and it need .post :DD
            binding.apply {
                chatRecyclerView.scrollToPosition(MessageData.messageList.size-1)
                etChat.text?.clear()
            }
        }else{
            Toast.makeText(context, getString(R.string.message_is_empty),Toast.LENGTH_SHORT).show()
        }

    }
}