package com.example.edutasker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskStudentCrossRef

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    @Insert
    suspend fun insertTaskStudentCrossRef(crossRef: TaskStudentCrossRef)

    @Transaction
    @Query(
        """
    SELECT * FROM tasks 
    INNER JOIN TaskStudentCrossRef ON tasks.taskId = TaskStudentCrossRef.taskId
    WHERE TaskStudentCrossRef.studentId = :studentId
"""
    )

    suspend fun getTasksForStudent(studentId: String): List<TaskWithStudents>

    @Query("SELECT * FROM StudentEntity WHERE email = :email AND password = :password")
    suspend fun loginStudent(email: String, password: String): StudentEntity?

    @Query("SELECT COUNT(*) FROM StudentEntity WHERE email = :email")
    suspend fun isEmailExists(email: String): Int

    @Query("SELECT * FROM StudentEntity WHERE email = :email LIMIT 1")
    suspend fun getStudentByEmail(email: String): StudentEntity?
}