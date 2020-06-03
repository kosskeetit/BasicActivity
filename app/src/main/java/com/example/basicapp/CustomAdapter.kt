package com.example.basicapp

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.alert_layout_box.view.*

class CustomAdapter (var context: Context, var data:ArrayList<DataItem>): BaseAdapter()  {
    internal var dbHelper = SQLiteConfig(context)
    private class ViewHolder(row: View?){
        var name: TextView
        var profession: TextView
        var password: TextView
        var imgdelete: ImageView
        var imgedit: ImageView


        init {

            this.name = row?.findViewById(R.id.tvname) as TextView
            this.profession = row?.findViewById(R.id.tvprofession) as TextView
            this.password = row?.findViewById(R.id.tvpassword) as TextView
            this.imgdelete = row?.findViewById(R.id.imgdelete) as ImageView
            this.imgedit = row?.findViewById(R.id.imgedit) as ImageView


        }
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View?
        val viewHolder:ViewHolder
        if (convertView == null){
            val layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.card_row_layout,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val usermodel:DataItem = getItem(position) as DataItem
        viewHolder.name.text = usermodel.name
        viewHolder.profession.text = usermodel.profession
        viewHolder.password.text = usermodel.password
        val name = usermodel.name
        val profession = usermodel.profession
        val password = usermodel.password
        val id:String

        viewHolder.imgedit.setOnClickListener {
            var dialogBuilder = AlertDialog.Builder(context)
            var myInflater = LayoutInflater.from(context)
            var dialogview = myInflater.inflate(R.layout.alert_layout_box,parent,false)
            dialogBuilder.setView(dialogview)

//            populate the edittexts with data

            dialogview.detname.setText(name)
            dialogview.detproffession.setText(profession)
            dialogview.detpassword.setText(password)

            dialogBuilder.setTitle("Edit")
            dialogBuilder.setMessage("Editing ${name}?")
            dialogBuilder.setPositiveButton("Edit", {dialog, which ->

                val updatedname = dialogview.detname.text.toString()
                val updatedprofession = dialogview.detproffession.text.toString()
                val updateddetpassword = dialogview.detpassword.text.toString()

                val cursor = dbHelper.allData

                while (cursor.moveToNext()) {
                    if (name == cursor.getString(1)){
                        dbHelper.updateData(
                            cursor.getString(0),
                            updatedname,
                            updatedprofession,
                            updateddetpassword
                        )
                    }

                }
                this.notifyDataSetChanged()

                Toast.makeText(context, "${ name} Updated successfully", Toast.LENGTH_SHORT).show()

            })

            dialogBuilder.setNegativeButton("Cancel", {dialog, which -> dialog.dismiss() })
            dialogBuilder.create().show()

        }

        viewHolder.imgdelete.setOnClickListener {

            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setTitle("Delete")
            dialogBuilder.setMessage("Confirm Delete item ${name}?")

            dialogBuilder.setPositiveButton("Delete", {dialog, which ->

                dbHelper.deleteData(name)
                val users: ArrayList<DataItem> = ArrayList()

                val cursor = dbHelper.allData

//            check if there are any records from the database
                if (cursor.count == 0) {
                    Toast.makeText(context, "No data available", Toast.LENGTH_SHORT).show()
                } else {
                    while (cursor.moveToNext()) {
                        users.add(
                            DataItem(
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3)

                            )
                        )

                    }
                    this.notifyDataSetChanged()
                }

            })
            dialogBuilder.setNegativeButton("Cancel", {dialog, which -> dialog.dismiss() })
            dialogBuilder.create().show()

        }



        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}