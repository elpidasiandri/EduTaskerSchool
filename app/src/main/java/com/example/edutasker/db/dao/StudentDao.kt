package com.example.edutasker.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.edutasker.db.entities.StudentEntity
import com.example.edutasker.model.StudentBasicInfoForPreviewIntoList
import com.example.edutasker.model.StudentPreviewAsListModel

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: StudentEntity)

    @Query("SELECT * FROM StudentEntity WHERE email = :email AND password = :password")
    suspend fun loginStudent(email: String, password: String): StudentEntity?

    @Query("SELECT COUNT(*) FROM StudentEntity WHERE email = :email")
    suspend fun isEmailExists(email: String): Int

    @Query("SELECT * FROM StudentEntity WHERE email = :email LIMIT 1")
    suspend fun getStudentByEmail(email: String): StudentEntity?

    @Query("SELECT studentId, username, image, subjects FROM StudentEntity")
    suspend fun getAllStudentsBySubject(): List<StudentBasicInfoForPreviewIntoList>

    @Query("SELECT studentId, username, image FROM StudentEntity")
    suspend fun getAllIdsNamesImageOfStudents(): List<StudentPreviewAsListModel>

    @Query(
        """
    SELECT studentId, username, image 
    FROM StudentEntity 
  WHERE name LIKE '%' || :keyword || '%' 
       OR username LIKE '%' || :keyword || '%'
"""
    )
    suspend fun searchStudents(keyword: String): List<StudentPreviewAsListModel>

    @Query("SELECT COUNT(*) FROM StudentEntity WHERE studentId = :studentId")
    suspend fun isStudentIdExists(studentId: String): Int

    @Query("SELECT * FROM StudentEntity WHERE studentId = :studentId")
    suspend fun getStudentById(studentId: String): StudentEntity?


}