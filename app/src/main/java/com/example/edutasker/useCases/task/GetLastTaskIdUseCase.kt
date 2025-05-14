package com.example.edutasker.useCases.task

import com.example.edutasker.repo.IDatabaseRepository

class GetLastTaskIdUseCase(
    private val repo: IDatabaseRepository,
) {
    suspend operator fun invoke(): String {
        return repo.getLastTaskId()
    }
}
