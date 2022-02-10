package com.example.coequality.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private val DataBaseName = "CoEquality.db"
private val ver: Int = 1

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, DataBaseName, null, ver) {

    /* Image Table*/
    public val ImageTableName = "AACImages"
    public val Column_ID = "ID"
    public val Column_Images = "Image"


    //sql statements used to create the tables from the database into the application
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            var sqlCreateStatement: String =
                "CREATE TABLE " + ImageTableName + " ( " + Column_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_Images + " BLOB NOT NULL )"

            db?.execSQL(sqlCreateStatement)

        } catch (e: SQLException) {
        }
    }

    //method when upgrading the database if the version changes
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    //method used to return all of the topics in the database from the topics table
    fun getAllImages(): ArrayList<Image> {

        val imageList = ArrayList<Image>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $ImageTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val image: ByteArray = cursor.getBlob(1)
                val b = Image(id, image)
                imageList.add(b)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return imageList
    }
}