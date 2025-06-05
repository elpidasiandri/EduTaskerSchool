package com.example.edutasker.db.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.edutasker.db.dao.convert.Converters
import com.example.edutasker.db.entities.NotificationEntity
import com.example.edutasker.db.entities.ProfessorEntity
import com.example.edutasker.db.entities.StudentEntity
import com.example.edutasker.db.entities.TaskEntity

@Database(
    entities = [TaskEntity::class,
        StudentEntity::class,
        NotificationEntity::class,
        ProfessorEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class EduTaskerDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun studentDao(): StudentDao
    abstract fun professorDao(): ProfessorDao
    abstract fun notificationDao(): NotificationDao
}