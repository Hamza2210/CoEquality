package com.example.coequality.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
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

    /*Password Table*/
    public val PasscodeTableName = "Passcode"
    public val Column_PassCodeID = "ID"
    public val Column_Passcode = "Passcode"


    //sql statements used to create the tables from the database into the application
    override fun onCreate(db: SQLiteDatabase?) {
        try {
            var sqlCreateStatement: String =
                "CREATE TABLE " + ImageTableName + " ( " + Column_ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_Images + " BLOB NOT NULL )"

            db?.execSQL(sqlCreateStatement)

            sqlCreateStatement = "CREATE TABLE " + PasscodeTableName + " ( " + Column_PassCodeID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_Passcode + " TEXT NOT NULL )"

            db?.execSQL(sqlCreateStatement)

        } catch (e: SQLException) {
        }
    }

    //method when upgrading the database if the version changes
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    //method used to return all of the images in the database from the images table
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

    fun addPasscode(passcode: Passcode): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val cv: ContentValues = ContentValues()

        cv.put(Column_Passcode, passcode.passcode)

        val success = db.insert(PasscodeTableName, null, cv)
        db.close()
        return success != 1L
    }

    fun getPasscode(): ArrayList<Passcode> {
        val passcodeList = ArrayList<Passcode>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $PasscodeTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)
        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val passcode: String = cursor.getString(1)

                val adding = Passcode(id, passcode)
                passcodeList.add(adding)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return passcodeList


    }

    fun isEmpty(): Boolean{
        val db: SQLiteDatabase = this.readableDatabase
        val noOfRows: Long = DatabaseUtils.queryNumEntries(db, PasscodeTableName)

        return noOfRows == 0L
    }

    fun deleteAll(){
        val db: SQLiteDatabase = this.writableDatabase
        db.delete(PasscodeTableName, null, null)
    }


}