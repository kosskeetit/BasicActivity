package com.example.basicapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteConfig(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null,1) {

        /**
         * Our onCreate() method.
         * Called when the database is created for the first time. This is
         * where the creation of tables and the initial population of the tables
         * should happen.
         */
        /**
         * Our onCreate() method.
         * Called when the database is created for the first time. This is
         * where the creation of tables and the initial population of the tables
         * should happen.
         */
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE $TABLE_NAME (ID INTEGER PRIMARY KEY " +
                    "AUTOINCREMENT,NAME TEXT,PROFESSION TEXT,RESIDENCE TEXT,PASSWORD TEXT)")
        }
        /**
         * Let's create Our onUpgrade method
         * Called when the database needs to be upgraded. The implementation should
         * use this method to drop tables, add tables, or do anything else it needs
         * to upgrade to the new schema version.
         */
        /**
         * Let's create Our onUpgrade method
         * Called when the database needs to be upgraded. The implementation should
         * use this method to drop tables, add tables, or do anything else it needs
         * to upgrade to the new schema version.
         */
        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
            onCreate(db)
        }
        /**
         * Let's create our insertData() method.
         * It Will insert data to SQLIte database.
         */
        /**
         * Let's create our insertData() method.
         * It Will insert data to SQLIte database.
         */
        fun insertData(name: String, profession: String, password:String) {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(COL_2, name)
            contentValues.put(COL_3, profession)
            contentValues.put(COL_5, password)
            db.insert(TABLE_NAME, null, contentValues)
        }

        /**
         * Let's create  a method to update a row with new field values.
         */
        /**
         * Let's create  a method to update a row with new field values.
         */
        fun updateData(id:String, name: String, profession: String,  password:String):
                Boolean {
            val db = this.writableDatabase
            val contentValues = ContentValues()
            contentValues.put(COL_2, name)
            contentValues.put(COL_3, profession)
            contentValues.put(COL_5, password)
            db.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
            db.close()
            return true
        }


        /**
         * Let's create a function to delete a given row based on the id.
         */
        /**
         * Let's create a function to delete a given row based on the id.
         */
        fun deleteData(name:String){
            val db = this.writableDatabase
            db.delete("users","NAME"+"=?", arrayOf(name))
            return db.close()
        }


        /**
         * The below getter property will return a Cursor containing our dataset.
         */
        /**
         * The below getter property will return a Cursor containing our dataset.
         */
        val allData : Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
            return res
        }

        /**
         * Let's create a companion object to hold our static fields.
         * A Companion object is an object that is common to all instances of a given
         * class.
         */

        /**
         * Let's create a companion object to hold our static fields.
         * A Companion object is an object that is common to all instances of a given
         * class.
         */
        companion object {
            val DATABASE_NAME = "coronadb.db"
            val TABLE_NAME = "users"
            val COL_1 = "ID"
            val COL_2 = "NAME"
            val COL_3 = "EMAIL"
            val COL_5 = "PASSWORD"
        }
}