package com.example.todoapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(private val tasks: ArrayList<ToDo>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val task=tasks[position]

        holder.itemView.apply{
            tv.text= task.Task
            cb1.isChecked= task.Cb

            cb1.setOnClickListener{
                //update the value
                task.Cb = !task.Cb
                if (cb1.isChecked()) {
                    //checkBox clicked and checked
                    holder.itemView.tv.setTextColor(Color.rgb(194, 198, 194))
                } else {
                    //checkBox clicked and unchecked
                    holder.itemView.tv.setTextColor(Color.rgb(0, 0, 0 ))
                }
            }

        }

/*  // another way
        holder.itemView.cb1.setOnClickListener {
            //update the value
            task.Cb = !task.Cb
            val isChecked: Boolean = holder.itemView.cb1.isChecked()
            if (isChecked) {
                //checkBox clicked and checked
                holder.itemView.tv.setTextColor(Color.rgb(194, 198, 194))
            } else {
                //checkBox clicked and unchecked
                holder.itemView.tv.setTextColor(Color.rgb(0, 0, 0 ))

            }
       }
*/
        }


    override fun getItemCount(): Int = tasks.size

}