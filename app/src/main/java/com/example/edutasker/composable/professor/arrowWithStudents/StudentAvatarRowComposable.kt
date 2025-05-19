package com.example.edutasker.composable.professor.arrowWithStudents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.edutasker.R
import com.example.edutasker.model.StudentPreviewAsListModel
import kotlinx.coroutines.launch

@Composable
fun StudentAvatarRowComposable(
    students: List<StudentPreviewAsListModel>,
    modifier: Modifier = Modifier,
    selectedStudentIdFromSearch: String,
    selectStudent: (StudentPreviewAsListModel) -> Unit,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxWidth()) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 35.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(students) { (id, username, imageUrl) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        if (id == selectedStudentIdFromSearch) {
                            selectStudent(
                                StudentPreviewAsListModel(
                                    studentId = "",
                                    username = "",
                                    image = ""
                                )
                            )
                        } else {
                            selectStudent(
                                StudentPreviewAsListModel(
                                    studentId = id,
                                    username = username,
                                    image = imageUrl
                                )
                            )
                        }
                    }) {
                    val imageModel = imageUrl.ifBlank { R.raw.studentavatar }

                    AsyncImage(
                        model = imageModel,
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.Gray, CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    if (id == selectedStudentIdFromSearch) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Selected",
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(16.dp)
                        )
                    } else {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = username,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        IconButton(
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(
                        (listState.firstVisibleItemIndex - 1).coerceAtLeast(
                            0
                        )
                    )
                }
            },
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterStart)
                .background(Color.White.copy(alpha = 0.8f), CircleShape)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Scroll Left")
        }

        IconButton(
            onClick = {
                coroutineScope.launch {
                    listState.animateScrollToItem(
                        (listState.firstVisibleItemIndex + 1).coerceAtMost(
                            students.size - 1
                        )
                    )
                }
            },
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterEnd)
                .background(Color.White.copy(alpha = 0.8f), CircleShape)
        ) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Scroll Right")
        }
    }
}


