package com.revok.project.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revok.project.database.Database
import com.revok.project.models.StringModel


class ViewModel(private val myDatabase: Database) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Listviews Fragment"
    }
//

    private var mutableLiveData: MutableLiveData<MutableList<StringModel>?>? = null

    fun getMovieList(): LiveData<MutableList<StringModel>?>? {
        if (mutableLiveData == null) {
            mutableLiveData = MutableLiveData()
            initMovieList()
        }
        return mutableLiveData
    }

    private fun initMovieList() {
//        val movieList: MutableList<StringModel> = ArrayList()
//        movieList.add(StringModel("Item 1"))
//        movieList.add(StringModel("Item 2"))
//        movieList.add(StringModel("Item 3"))

        val data = myDatabase.yourDao().getAllData()

        mutableLiveData = data
    }

    fun deleteMovie(position: Int) {
        if (mutableLiveData!!.value != null) {
            val movieList: MutableList<StringModel> = ArrayList(mutableLiveData!!.value!!)
            movieList.removeAt(position)
            mutableLiveData!!.value = movieList
        }
    }

    fun addMovie(movie: StringModel) {
        if (mutableLiveData!!.value != null) {
            val movieList: MutableList<StringModel> = ArrayList(mutableLiveData!!.value!!)
            movieList.add(movie)
            mutableLiveData!!.value = movieList
        }
    }

    val text: LiveData<String> = _text

}