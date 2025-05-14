package com.example.edutasker.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

object FileHelper {
    fun getFileNameFromUri(context: Context, uri: Uri): String {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        return cursor?.use {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            it.moveToFirst()
            it.getString(nameIndex)
        } ?: "attached_file"
    }

}