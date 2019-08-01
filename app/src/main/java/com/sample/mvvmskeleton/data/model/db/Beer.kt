package com.sample.mvvmskeleton.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "beers")
data class Beer(
    @PrimaryKey
    @Expose
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val beerId: Int,
    @Expose
    @ColumnInfo(name = "abv")
    @SerializedName("abv")
    val abv: String,
    @Expose
    @ColumnInfo(name = "ibu")
    @SerializedName("ibu")
    val ibu: String,
    @Expose
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,
    @Expose
    @ColumnInfo(name = "style")
    @SerializedName("style")
    val style: String,
    @Expose
    @ColumnInfo(name = "ounces")
    @SerializedName("ounces")
    val ounces: Double
)
