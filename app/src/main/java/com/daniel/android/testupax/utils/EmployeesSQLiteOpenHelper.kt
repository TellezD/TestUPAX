package com.daniel.android.testupax.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class EmployeesSQLiteOpenHelper(
    context: Context,
    name: String,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $EMPLOYEES($ID int primary key, $NAME text, $BIRTH text, $ROL text)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    companion object {
        const val EMPLOYEES = "employees"
        const val ID = "id"
        const val NAME = "name"
        const val BIRTH = "birth"
        const val ROL = "rol"
    }
}