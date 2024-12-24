package com.example.tbchomework10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tbchomework10.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private lateinit var adapter: ItemRecyclerView
    private lateinit var searchAdapter: ItemSearchRecyclerView
    private var item = mutableListOf<Items>()
     var buttonList = mutableListOf<ItemSearch>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }


    private fun setUp() {
        setUpItemsData()
        setUpSearchItems()
        setUpSearchItemRecycler()
        setUpItemRecycler()
    }

    private fun setUpItemRecycler() {
        adapter = ItemRecyclerView()
        binding!!.apply {
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = adapter
        }
        adapter.setData(item)
    }

    private fun setUpSearchItemRecycler() {
        searchAdapter = ItemSearchRecyclerView()
        binding!!.apply {
            recyclerViewSearch.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false,)
            recyclerViewSearch.adapter = searchAdapter
        }

        searchAdapter.setData(buttonList)
        searchAdapter.setOnItemClickListener { category ->
            filterItemsByCategory(category.title)
        }

    }

    private fun filterItemsByCategory(category: String) {
        val filteredItems = item.filter { it.categoryType.contains(category) }
        adapter.setData(filteredItems.toMutableList())
    }

    private fun setUpSearchItems() {
        buttonList.also {
            it.add(
                ItemSearch(
                    id = 1,
                    title = "All",
                    icon = R.drawable.ic_party,
                    buttonType = ButtonType.ALL
                )
            )
            it.add(
                ItemSearch(
                    id = 1,
                    title = "Party",
                    icon = R.drawable.ic_party,
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                ItemSearch(
                    id = 1,
                    title = "Category1",
                    icon = R.drawable.ic_party,
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                ItemSearch(
                    id = 1,
                    title = "Camping",
                    icon = R.drawable.ic_camping,
                    buttonType = ButtonType.NORMAL
                )
            )
        }
    }

    private fun setUpItemsData() {
        item.also {
            it.add(
                Items(
                    id = 1,
                    image = R.drawable.image_girl_in_black,
                    title = "Belt suit blazer",
                    price = 110,
                    categoryType = listOf("Party", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 2,
                    image = R.drawable.image_girl_in_yellow,
                    title = "Nice Clothes",
                    price = 200,
                    categoryType = listOf("Party", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 3,
                    image = R.drawable.image_girl_in_light_blue,
                    title = "Belt suit blazer",
                    price = 150,
                    categoryType = listOf("Camping", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 4,
                    image = R.drawable.image_girl_in_red,
                    title = "Red Hood",
                    price = 100,
                    categoryType = listOf("Category1", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 5,
                    image = R.drawable.image_girl_in_black,
                    title = "Belt suit blazer",
                    price = 4410,
                    categoryType = listOf("Category2", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 6,
                    image = R.drawable.image_girl_in_black,
                    title = "Black Jacket",
                    price = 1140,
                    categoryType = listOf("Category1", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 7,
                    image = R.drawable.image_girl_in_yellow,
                    title = "Belt suit blazer",
                    price = 180,
                    categoryType = listOf("Camping", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 8,
                    image = R.drawable.image_girl_in_red,
                    title = "Red Hood",
                    price = 180,
                    categoryType = listOf("Category3", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 9,
                    image = R.drawable.image_girl_in_light_blue,
                    title = "Belt suit blazer",
                    price = 180,
                    categoryType = listOf("Category2", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 10,
                    image = R.drawable.image_girl_in_black,
                    title = "Black Jacket",
                    price = 180,
                    categoryType = listOf("Party", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 11,
                    image = R.drawable.image_girl_in_yellow,
                    title = "Belt suit blazer",
                    price = 180,
                    categoryType = listOf("Camping", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )
            it.add(
                Items(
                    id = 12,
                    image = R.drawable.image_girl_in_red,
                    title = "Belt suit blazer",
                    price = 180,
                    categoryType = listOf("Party", "All"),
                    buttonType = ButtonType.NORMAL
                )
            )

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}