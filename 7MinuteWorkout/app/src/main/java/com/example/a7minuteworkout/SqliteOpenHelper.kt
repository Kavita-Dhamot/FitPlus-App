package com.example.a7minuteworkout
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

open class SqliteOpenHelper(context:Context, factory: SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    companion object{
        private val DATABASE_VERSION=3
        private val DATABASE_NAME="SevenMinutesWorkout.db"
        val TABLE_HISTORY="history"
        val COLUMN_ID="_id"
        val COLUMN_COMPLETED_DATE="completed_date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_HISTORY + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_COMPLETED_DATE + " TEXT" + ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_HISTORY)
        onCreate(db)
    }

    fun addDate(date:String)
    {
        Log.e("Date",date);
        val values=ContentValues()
        values.put(COLUMN_COMPLETED_DATE,date)
        val db=this.writableDatabase
        db.insert(TABLE_HISTORY,null,values)
        db.close()

    }

    fun getAllCompleteDatesList(): ArrayList<String>
    {
        val list=ArrayList<String>()
        val db=this.readableDatabase
        val cursor=db.rawQuery("SELECT * FROM $TABLE_HISTORY",null)
        while(cursor.moveToNext())
        {
            val dateValue=(cursor.getString(cursor.getColumnIndex
                (COLUMN_COMPLETED_DATE)))
            list.add(dateValue)
        }
        cursor.close()
        return list
    }

}
