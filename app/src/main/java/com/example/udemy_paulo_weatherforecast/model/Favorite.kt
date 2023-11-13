package com.example.udemy_paulo_weatherforecast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(
    tableName = "favorite_table"
)
data class Favorite(
    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "country")
    val country: String,

    )
