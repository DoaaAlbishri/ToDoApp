package com.example.todoapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var myLayout :ConstraintLayout
    var tasks = arrayListOf<ToDo>()
    lateinit var button :FloatingActionButton
    lateinit var myRv :RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myLayout = findViewById(R.id.clMain)
        button= findViewById(R.id.floatingActionButton)
        myRv = findViewById(R.id.myRv)

        tasks.add(ToDo("Task1",false))
        myRv.adapter = RecyclerViewAdapter(tasks)
        myRv.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener {
           addAlert()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menus, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.m1 -> {
                delete()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun delete(){
        //list for delete
        val deleteList = ArrayList<ToDo>()
        //loop in tasks array list
        for (i in tasks) {
            //if is checked
            if (i.Cb == true) {
                //add item to delete list
                deleteList.add(i)
                //remove all item in delete list from tasks list
            }
        }
        tasks.removeAll(deleteList)
        println(deleteList.toString())
        //toast
        Toast.makeText(applicationContext, "${deleteList.size} items deleted", Toast.LENGTH_SHORT).show()
        //update Recycler view
        myRv.adapter?.notifyDataSetChanged()
    }

    private fun addAlert(){
        // first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        // then we set up the input
        val input = EditText(this)
        // positive button text and action
        dialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener { dialog, id ->
            tasks.add(ToDo(input.text.toString(),false))
            myRv.adapter?.notifyDataSetChanged()
            //myRv.adapter = RecyclerViewAdapter(tasks)
            //myRv.layoutManager = LinearLayoutManager(this)
            })
            // negative button text and action
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("New Item")
        // add the Edit Text
        alert.setView(input)
        // show alert dialog
        alert.show()
    }

}