package com.example.edutasker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.edutasker.db.entities.ProfessorEntity
@Dao
interface ProfessorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfessor(professor: ProfessorEntity)

    @Query("SELECT * FROM ProfessorEntity WHERE email = :email AND password = :password")
    suspend fun loginProfessor(email: String, password: String): ProfessorEntity?

    @Query("SELECT COUNT(*) FROM ProfessorEntity WHERE email = :email")
    suspend fun isEmailExists(email: String): Int

    @Query("SELECT * FROM ProfessorEntity WHERE email = :email LIMIT 1")
    suspend fun getProfessorByEmail(email: String): ProfessorEntity?

    @Query("SELECT * FROM ProfessorEntity WHERE profId = :professorId")
    suspend fun getProfessorById(professorId: String): ProfessorEntity?

}

