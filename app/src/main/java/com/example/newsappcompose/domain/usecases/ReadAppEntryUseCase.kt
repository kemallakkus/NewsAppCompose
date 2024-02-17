package com.example.newsappcompose.domain.usecases

import com.example.newsappcompose.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUseCase(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }
}