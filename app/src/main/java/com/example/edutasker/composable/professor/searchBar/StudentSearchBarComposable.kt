package com.example.edutasker.composable.professor.searchBar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.edutasker.R

@Composable
fun StudentSearchBarComposable(
    selectedStudentIdFromSearch: String,
    keyword: String,
    onQueryChange: (String) -> Unit,
) {
    var localQuery by remember { mutableStateOf("") }
    TextField(
        value = if (selectedStudentIdFromSearch.isNotEmpty() && keyword.isEmpty()) {
            localQuery = ""
            ""
        } else localQuery,
        onValueChange = {
            localQuery = it
            onQueryChange(it)
        },
        placeholder = { Text(stringResource(R.string.search_student)) },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White
        )
    )
}
