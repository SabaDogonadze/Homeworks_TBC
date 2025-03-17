package com.example.tbchomework18.domain.usecase.validation

interface ValidateEmailUseCase {
    operator fun invoke(email: String): Boolean
}

class ValidateEmailUseCaseImpl : ValidateEmailUseCase {
    override fun invoke(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
        return emailRegex.toRegex().matches(email)
    }
}
