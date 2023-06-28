package com.revok.project

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.revok.project.adapter.MovieListAdapter
import com.revok.project.databinding.ActivityMainBinding
import com.revok.project.models.StringModel
import com.revok.project.utilities.Utils
import com.revok.project.view_model.ViewModel

class MainActivity : AppCompatActivity(), MovieListAdapter.MovieClickInterface,
    MovieListAdapter.MovieDoubleClickInterface {
    private var list = mutableListOf<Int>()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val homeViewModel = ViewModelProvider(this)[ViewModel::class.java]
        Utils.status = 1

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recyclerView
        val btnFab: FloatingActionButton = binding.btnFab
        val btnDelete: Button = binding.btnDelete

        val adapter = MovieListAdapter(StringModel.itemCallback, this, this)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter

        btnFab.setOnClickListener {
            showInputDialog()
        }

        btnDelete.setOnClickListener {
            if (list.size > 0) {
                list.sortDescending()

                for (item in list) {
                    homeViewModel.deleteMovie(item)
                }


            } else {
                Toast.makeText(applicationContext, "Select Something first", Toast.LENGTH_SHORT)
                    .show()
            }
            list = mutableListOf()
        }
        homeViewModel.getMovieList()!!
            .observe(this) { t ->
                adapter.submitList(t as List<StringModel?>?)
                Log.d(ContentValues.TAG, "updatedList: ${t?.size} ")
            }
    }

    override fun onClick(position: Int) {
        if (!list.contains(position)) {
            list.add(position)
        } else {
            Toast.makeText(applicationContext, "Item is already in the list", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun showInputDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter a value")

        // Set up the input field
        val input = EditText(this)
        builder.setView(input)

        // Set up the buttons
        builder.setPositiveButton("OK") { dialog, _ ->
            val enteredValue = input.text.toString()
            // Process the entered value
            Toast.makeText(
                applicationContext,
                enteredValue,
                Toast.LENGTH_SHORT
            ).show()
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    override fun onDoubleClick(position: Int) {
        if (list.contains(position)) {
            list.removeAt(position)
        } else {
            Toast.makeText(applicationContext, "Select first to Unselect", Toast.LENGTH_SHORT)
                .show()
        }
    }

}