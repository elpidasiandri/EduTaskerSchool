package com.example.edutasker.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.entities.TaskEntity

@Dao
interface ProfessorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfessor(professor: ProfessorEntity)

    @Query("""
        SELECT COUNT(*) FROM tasks WHERE assignBy = :professorId
    """)
    suspend fun getTaskCountForProfessor(professorId: String): Int

    @Query("""
        SELECT * FROM tasks WHERE assignBy = :professorId
    """)
    suspend fun getTasksByProfessor(professorId: String): List<TaskEntity>

    @Query("SELECT * FROM ProfessorEntity WHERE email = :email AND password = :password")
    suspend fun loginProfessor(email: String, password: String): ProfessorEntity?

    @Query("SELECT COUNT(*) FROM ProfessorEntity WHERE email = :email")
    suspend fun isEmailExists(email: String): Int

    @Query("SELECT * FROM ProfessorEntity WHERE email = :email LIMIT 1")
    suspend fun getProfessorByEmail(email: String): ProfessorEntity?
}
