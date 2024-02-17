package com.example.newsappcompose.domain.usecases

import com.example.newsappcompose.domain.manger.LocalUserManger

class SaveAppEntryUseCase(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }
}