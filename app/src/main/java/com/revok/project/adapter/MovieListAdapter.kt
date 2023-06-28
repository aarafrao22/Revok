package com.revok.project.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pedromassango.doubleclick.DoubleClick
import com.pedromassango.doubleclick.DoubleClickListener
import com.revok.project.R
import com.revok.project.models.StringModel


class MovieListAdapter constructor(
    diffCallback: DiffUtil.ItemCallback<StringModel?>,
    var movieClickInterface: MovieClickInterface,
    var doubleClickInterface: MovieDoubleClickInterface
) :
    ListAdapter<StringModel?, MovieListAdapter.MovieViewHolder?>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: StringModel? = getItem(position)
        holder.bind(movie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var nameTextView: TextView
        fun bind(movie: StringModel?) {
            nameTextView.text = movie?.name
        }


        init {
            nameTextView = itemView.findViewById(R.id.nameTextview)
            itemView.setOnClickListener(DoubleClick(object : DoubleClickListener {
                override fun onSingleClick(view: View?) {
                    movieClickInterface.onClick(adapterPosition)
                    itemView.setBackgroundColor(Color.CYAN)

                }

                override fun onDoubleClick(view: View?) {
                    doubleClickInterface.onDoubleClick(adapterPosition)
                    itemView.setBackgroundColor(Color.WHITE)
                }

            }))
        }
    }

    interface MovieClickInterface {
        fun onClick(position: Int)
    }

    interface MovieDoubleClickInterface {
        fun onDoubleClick(position: Int)
    }
}
