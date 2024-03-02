package com.example.newsappcompose.domain.usecases.appentry

import com.example.newsappcompose.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }
}