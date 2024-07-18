package com.indranil.musicplayerjetpackcompose.data.repository

import com.indranil.musicplayerjetpackcompose.data.locale.ContentResolverHelper
import com.indranil.musicplayerjetpackcompose.data.locale.model.Audio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AudioRepository @Inject constructor(
    private val contentResolver: ContentResolverHelper
){
    suspend fun getAudio(): List<Audio> = withContext(Dispatchers.IO){
        contentResolver.getAudioData()
    }
}