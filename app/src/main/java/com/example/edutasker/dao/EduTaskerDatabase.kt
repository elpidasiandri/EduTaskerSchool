package com.example.edutasker.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity

@Database(
    entities = [TaskEntity::class,
        StudentEntity::class,
        ProfessorEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class EduTaskerDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun studentDao(): StudentDao
    abstract fun professorDao(): ProfessorDao
}