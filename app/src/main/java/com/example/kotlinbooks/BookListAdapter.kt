package com.example.kotlinbooks

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import okhttp3.internal.notify

class BookListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var books = emptyList<RoomBook>()

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitleView: TextView = itemView.findViewById(R.id.libraryTitleView)
        val smallThumbnailView: ImageView = itemView.findViewById(R.id.libraryThumbnailView)
        val detailsBtn: Button = itemView.findViewById(R.id.detailsBtn)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BookViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return BookViewHolder(itemView)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val current = books[position]
        holder.bookTitleView.text = current.title + "\nby " + current.leadAuthor
        Glide
            .with(holder.smallThumbnailView.context)
            .load(current.smallThumbnail)
            .into(holder.smallThumbnailView)
    }

    fun setBooks(books: List<RoomBook>) {
        this.books = books
        notifyDataSetChanged()
    }
}