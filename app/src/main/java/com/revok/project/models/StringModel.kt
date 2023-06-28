package com.revok.project.models

import androidx.recyclerview.widget.DiffUtil


data class StringModel (var name:String){
    companion object{
        var itemCallback: DiffUtil.ItemCallback<StringModel?> = object : DiffUtil.ItemCallback<StringModel?>() {
            override fun areItemsTheSame(oldItem: StringModel, newItem: StringModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: StringModel, newItem: StringModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}