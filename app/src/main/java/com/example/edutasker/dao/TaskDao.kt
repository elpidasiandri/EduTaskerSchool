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
import com.example.edutasker.entities.TaskStudentCrossRef

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
}

data class TaskWithStudents(
    @Embedded val task: TaskEntity,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "studentId",
        associateBy = Junction(TaskStudentCrossRef::class)
    )
    val students: List<StudentEntity>,
)

data class SubjectWithTasks(
    val subjectName: String,
    val taskCount: Int,
    val tasks: List<TaskEntity>,
)

data class SubjectTaskCount(
    val subjectName: String,
    val taskCount: Int,
)