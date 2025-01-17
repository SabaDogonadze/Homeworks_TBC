package com.example.tbchomework16.fragment

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tbchomework16.R
import com.example.tbchomework16.databinding.NestedRecyclerChooserViewholderBinding
import com.example.tbchomework16.databinding.NestedRecyclerViewholderBinding
import com.example.tbchomework16.model.Data

class MainFragmentChildAdapterListAdapter() :
    ListAdapter<Data, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.fieldId == newItem.fieldId
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }) {

    companion object {
        const val INPUT = 1
        const val CHOOSER = 2
    }
   private var onItemClicked: ((Data) -> Unit)? = null

    fun setOnItemClickedListener(listener: (Data) -> Unit) {
        onItemClicked = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == INPUT ){NestedRecyclerInputViewHolder(
            NestedRecyclerViewholderBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )}else{
            NestedRecyclerChooserViewHolder(
                NestedRecyclerChooserViewholderBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NestedRecyclerInputViewHolder) {
            holder.bind(getItem(position))
        }
        if (holder is NestedRecyclerChooserViewHolder) {
            holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].fieldType == "chooser") {
            CHOOSER
        } else INPUT
    }


    inner class NestedRecyclerInputViewHolder(private var binding: NestedRecyclerViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            val item = getItem(adapterPosition)
            d("InnerAdapter", "${item} ")
            binding.etUserInput.hint = item.hint
            if(item.required){
               val isFieldEmpty = binding.etUserInput.text.isNullOrEmpty()
                if (isFieldEmpty){
                    binding.etUserInput.doAfterTextChanged {    // works on afterTextChanged ....Kinda , does not work on click listener
                        onItemClicked?.invoke(item)
                        Toast.makeText(binding.root.context,
                            binding.root.context.getString(R.string.field_is_not_filled), Toast.LENGTH_SHORT).show()
                        d("in inner recycler","${item}")
                    }
                }
            }
        }
    }

    inner class NestedRecyclerChooserViewHolder(private var binding: NestedRecyclerChooserViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            if (item.hint == "Gender") {
                binding.tvInput.text = item.hint
                chooseGender()
            }
            if (item.hint == "Birthday") {
                binding.tvInput.text = item.hint
                binding.spinner.visibility = View.GONE
                binding.tvInput.setOnClickListener {
                    showDatePicker()
                }
            }

        }

        private fun chooseGender() {
            val genderOptions = itemView.context.resources.getStringArray(R.array.gender_options)
            val adapter = ArrayAdapter(
                itemView.context,
                android.R.layout.simple_spinner_item,
                genderOptions
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter

            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedGender = genderOptions[position]
                    binding.tvInput.text = selectedGender
                    d("Spinner", "Selected: $selectedGender")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //
                }
            }
        }

        private fun showDatePicker() {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                itemView.context,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val date = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    binding.tvInput.text = date
                },
                year, month, day
            )
            datePickerDialog.show()

        }

    }
}


