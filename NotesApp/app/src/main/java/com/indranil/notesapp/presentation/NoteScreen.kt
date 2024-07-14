package com.indranil.notesapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Sort
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.indranil.notesapp.ui.theme.Pink80

@Composable
fun NoteScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent) -> Unit
) {
    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(Pink80)
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
            ) {
            Text(
                text = "Note App", modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Bold, color = Color.White
            )
            IconButton(onClick = { NotesEvent.SortNotes }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Sort, contentDescription = null,
                    modifier = Modifier.size(35.dp),
                    tint = Color.White
                )
                }
            }
            }, floatingActionButton = {
            FloatingActionButton(
                containerColor = Pink80,
                onClick = {
                    state.title.value = ""
                    state.description.value = ""
                    navController.navigate("AddNoteScreen")
                }
            ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add Note",
                tint = Color.White // Set icon tint to white for contrast
            )
        }
        }
        ) { innerPadding -> // Use innerPadding from Scaffold
            LazyColumn(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                contentPadding = innerPadding, // Apply innerPadding here
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.notes.size) { index ->
                    NoteItem(state = state, index = index, onEvent = onEvent)
                }
            }
    }

}

@Composable
fun NoteItem(state: NoteState, index: Int, onEvent: (NotesEvent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Pink80)
            .padding(12.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = state.notes.get(index = index).title, // Use index directly
                fontSize = 18.sp,color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.notes.get(index = index).description, // Use index directly
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        // Corrected Icon composable
        IconButton(onClick = { onEvent(NotesEvent.DeleteNote(
            state.notes.get(index = index)
        )) }) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Note", // Corrected typo
                modifier = Modifier.size(35.dp),
                tint = Color.White
            )
        }
    }
}
