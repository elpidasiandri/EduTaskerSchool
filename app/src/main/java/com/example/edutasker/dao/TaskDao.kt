package com.example.edutasker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.entities.relations.TaskWithStudent
import com.example.edutasker.entities.relations.TaskWithStudentAndProfessor
import com.example.edutasker.model.SubjectTaskCount
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

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
    @Query(
        """
        SELECT * FROM tasks
    """
    )
    fun getAllTasksWithStudentImages(): Flow<List<TaskWithStudent>>

    @Transaction
    @Query(
        """
        SELECT * FROM tasks
        WHERE assignBy = :assignerId
    """
    )
    suspend fun getTasksByAssignerWithStudentImages(assignerId: String): List<TaskWithStudent>

    @Transaction
    @Query(
        """
    SELECT * FROM tasks
    WHERE assignBy = :assignerId
    AND assignTo = :studentId
"""
    )
    fun getTasksByAssignerAndStudent(
        assignerId: String,
        studentId: String,
    ): Flow<List<TaskWithStudent>>

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    suspend fun getTaskWithBasicStudentInfo(taskId: String): TaskWithStudent

    @Transaction
    @Query("SELECT * FROM tasks WHERE taskId = :taskId")
    suspend fun getTaskWithStudentAndProfessor(taskId: String): TaskWithStudentAndProfessor

    @Query(
        """
        UPDATE tasks 
        SET taskTitle = :taskTitle, 
            description = :description, 
            deadlineDate = :deadlineDate ,
            progress = :progress
        WHERE taskId = :taskId
    """
    )
    suspend fun updateTaskDetails(
        taskId: String,
        taskTitle: String,
        description: String,
        deadlineDate: String,
        progress: String,
    )
}