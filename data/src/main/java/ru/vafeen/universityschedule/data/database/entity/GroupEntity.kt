package ru.vafeen.universityschedule.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Group")
internal data class GroupEntity(
    @PrimaryKey val id: Int,
    val course: Int,
    val group: String
)
