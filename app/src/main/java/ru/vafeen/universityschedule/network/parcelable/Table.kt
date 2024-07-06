package ru.vafeen.universityschedule.network.parcelable

import com.google.gson.annotations.SerializedName

data class Table(
    @SerializedName("cols") val cols: List<Column>,
    @SerializedName("rows") val rows: List<Row>,
    @SerializedName("parsedNumHeaders") val parsedNumHeaders: Int
)