package com.example.edutasker.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Junction
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.example.edutasker.entities.StudentEntity
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.entities.relations.TaskStudentCrossRef
import com.example.edutasker.entities.relations.TaskWithStudents
import com.example.edutasker.model.SubjectTaskCount

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Insert
    suspend fun insertTaskStudentCrossRef(crossRef: TaskStudentCrossRef)

    @Query("SELECT COUNT(*) FROM TaskStudentCrossRef WHERE taskId = :taskId AND studentId = :studentId")
    suspend fun isTaskStudentCrossRefExists(taskId: String, studentId: String): Int

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    suspend fun getTaskWithStudents(taskId: String): TaskWithStudents

    @Query(
        """
        SELECT subjectName, COUNT(*) as taskCount 
        FROM tasks
        GROUP BY subjectName
    """
    )
    suspend fun getTaskCountPerSubject(): List<SubjectTaskCount>

    @Query("SELECT * FROM tasks WHERE subjectName = :subjectName")
    suspend fun getTasksForSubject(subjectName: String): List<TaskEntity>

    @Query("SELECT COUNT(*) FROM tasks WHERE assignBy = :profId")
    suspend fun getTaskCountForProfessor(profId: String): Int

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskEntity>

    @Query("SELECT COUNT(*) FROM tasks WHERE taskId = :taskId")
    suspend fun isTaskIdExists(taskId: String): Int

    @Query(
        """
    SELECT taskId FROM tasks 
    WHERE taskId LIKE 't%' 
    ORDER BY CAST(SUBSTR(taskId, 2) AS INTEGER) DESC 
    LIMIT 1
"""
    )
    suspend fun getLastTaskId(): String?

    @Transaction
    @Query("""
        SELECT * FROM tasks
    """)
    suspend fun getAllTasksWithStudentImages(): List<TaskWithStudents>

    @Transaction
    @Query("""
        SELECT * FROM tasks
        WHERE assignBy = :assignerId
    """)
    suspend fun getTasksByAssignerWithStudentImages(assignerId: String): List<TaskWithStudents>

    @Transaction
    @Query("""
        SELECT * FROM tasks 
        WHERE assignBy = :assignerId 
        AND taskId IN (
            SELECT taskId FROM TaskStudentCrossRef WHERE studentId = :studentId
        )
    """)
    suspend fun getTasksByAssignerAndStudent(
        assignerId: String,
        studentId: String
    ): List<TaskWithStudents>
}