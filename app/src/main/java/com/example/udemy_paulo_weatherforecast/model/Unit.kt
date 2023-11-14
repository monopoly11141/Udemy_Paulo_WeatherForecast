package com.example.udemy_paulo_weatherforecast.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "setting_table")
data class Unit(

    @PrimaryKey
    @ColumnInfo(name = "unit")
    val unit : String,


)
