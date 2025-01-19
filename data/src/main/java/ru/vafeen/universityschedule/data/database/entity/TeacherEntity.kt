package ru.vafeen.universityschedule.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Teacher")
internal data class TeacherEntity(
    @PrimaryKey val name: String
)
