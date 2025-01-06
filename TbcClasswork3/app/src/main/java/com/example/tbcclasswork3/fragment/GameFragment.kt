package com.example.tbcclasswork3.fragment

import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tbcclasswork3.R
import com.example.tbcclasswork3.databinding.FragmentGameBinding
import com.example.tbcclasswork3.model.ButtonType
import com.example.tbcclasswork3.model.ItemModel

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ItemRecyclerView
    private var board: MutableList<ItemModel> = emptyList<ItemModel>().toMutableList()
    private var numberResult: Int = 3
    private var isXTurn = true
    private var gameOver = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() {
        getDataFromMainFragment()
        setUpRecycler()
        listeners()
    }
    private fun getDataFromMainFragment(){
        val message = arguments?.getInt("number")
        numberResult = message?:3
    }
    private fun listeners() {
        adapter.setonItemClickedListener { item ->
            playerMove(item.id)
            d("q12345","${isXTurn}")
            d("q12345","${item.id}")
        }
    }
        // this was asynchronous and numberResult was not getting value fast enough

   /* private fun getDataFromMainFragment() {
        parentFragmentManager.setFragmentResultListener("number", viewLifecycleOwner) { _, bundle ->
            val result = bundle.getInt("number")
            if (result != numberResult) {
                numberResult = result
                setUpRecycler()
                d("q12345", "$numberResult")
            }
            d("q12345", "$numberResult")
        }
    }*/


    private fun setUpRecycler() {
        adapter = ItemRecyclerView()
        board.clear()
        for (i in 0 until numberResult) {
            for (j in 0 until numberResult) {
                board.add(
                    ItemModel(
                        id = i * numberResult + j,
                        buttonImage = R.drawable.item_background,
                        type = ButtonType.EMPTY_BUTTON
                    )
                )
            }
        }
        adapter.setData(board)
        binding.apply {
            d("q12345", "$numberResult")
            recyclerView.layoutManager = GridLayoutManager(context, numberResult)
            recyclerView.adapter = adapter
        }

    }

    private fun playerMove(position:Int){
        if(!gameOver && board[position].type == ButtonType.EMPTY_BUTTON){
             if(isXTurn) {
                 board[position] = board[position].copy(type = ButtonType.X_BUTTON)
                 isXTurn = false
            }else{
                 board[position] = board[position].copy(type = ButtonType.O_BUTTON)
                 isXTurn = true
            }
        }
        adapter.submitList(board.toMutableList())
        if(checkForWin()){
            gameOver = true
            Toast.makeText(context, "Player ${if (isXTurn) "O" else "X"} wins!", Toast.LENGTH_SHORT).show()

        }
    }

    private fun checkForWin(): Boolean {
        // Horizontal and Vertical Checks
        for (i in 0 until numberResult) {
            // Horizontal
            if (board.slice(i * numberResult until (i + 1) * numberResult)
                    .all { it.type == ButtonType.X_BUTTON }
            ) return true
            if (board.slice(i * numberResult until (i + 1) * numberResult)
                    .all { it.type == ButtonType.O_BUTTON }
            ) return true

            // Vertical
            if (board.filterIndexed { index, _ -> index % numberResult == i }
                    .all { it.type == ButtonType.X_BUTTON }) return true
            if (board.filterIndexed { index, _ -> index % numberResult == i }
                    .all { it.type == ButtonType.O_BUTTON }) return true
        }

        // Diagonal (Top-left to Bottom-right)
        if (board.filterIndexed { index, _ -> index % (numberResult + 1) == 0 }
                .all { it.type == ButtonType.X_BUTTON }) return true
        if (board.filterIndexed { index, _ -> index % (numberResult + 1) == 0 }
                .all { it.type == ButtonType.O_BUTTON }) return true

        // Diagonal (Top-right to Bottom-left)
        if (board.filterIndexed { index, _ -> (index + 1) % (numberResult - 1) == 0 && index < (numberResult * numberResult - 1) }
                .all { it.type == ButtonType.X_BUTTON }) return true
        if (board.filterIndexed { index, _ -> (index + 1) % (numberResult - 1) == 0 && index < (numberResult * numberResult - 1) }
                .all { it.type == ButtonType.O_BUTTON }) return true

        return false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
