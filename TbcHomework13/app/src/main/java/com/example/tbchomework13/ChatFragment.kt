package com.example.tbchomework13

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework13.databinding.FragmentChatBinding


class ChatFragment : Fragment() {
    private var _binding:FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ChatRecyclerView
    private lateinit var status : MessageType
    var index:Int = 4
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp(){
        setUpRecycler()
        listeners()
    }
    private fun listeners(){
        binding.btnSend.setOnClickListener{
            submitNewMessage()
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
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

    private fun submitNewMessage():Boolean{
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
            MessageData.messageList.add(ChatMessage(index,message,"today,13:45", status))
            adapter.setData(MessageData.messageList.toMutableList())
            binding.chatRecyclerView.scrollToPosition(MessageData.messageList.size - 1) // this is not working
            binding.etChat.text?.clear()
            return true
        }else{
            Toast.makeText(context,"Message is Empty",Toast.LENGTH_SHORT).show()
            return false
        }

    }

}