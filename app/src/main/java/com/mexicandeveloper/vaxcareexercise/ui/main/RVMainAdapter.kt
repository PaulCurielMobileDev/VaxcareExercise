package com.mexicandeveloper.vaxcareexercise.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mexicandeveloper.vaxcareexercise.databinding.RowBookBinding
import com.mexicandeveloper.vaxcareexercise.models.Book

class RVMainAdapter(var theBooksList: List<Book>, var listener: ListenerInteraction) :
    RecyclerView.Adapter<RVMainAdapter.BookViewHolder>() {

    private lateinit var binding: RowBookBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newBookList: List<Book>) {
        theBooksList = newBookList
        notifyDataSetChanged()
    }

    inner class BookViewHolder(binding: RowBookBinding) : ViewHolder(binding.root) {

        fun bind(book: Book, onSelect: (Book) -> Unit) {
            binding.book = book

            binding.root.setOnClickListener {
                onSelect(book)

            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        binding = RowBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun getItemCount(): Int = theBooksList.size


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(theBooksList[position]) {
            listener.onItemClick(it)
        }
    }

    interface ListenerInteraction {
        fun onItemClick(item: Book)
    }
}