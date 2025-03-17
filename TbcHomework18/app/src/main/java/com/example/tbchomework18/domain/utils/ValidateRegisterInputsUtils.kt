package com.example.tbchomework18.domain.utils

class ValidateRegisterInputsUtils {
    sealed class RegisterValidationResult {
        data object Success : RegisterValidationResult()
        data class Error(val errorType: RegisterValidationError) : RegisterValidationResult()
    }

    enum class RegisterValidationError {
        EMAIL_EMPTY,
        PASSWORD_EMPTY,
        REPEAT_PASSWORD_EMPTY,
        PASSWORD_MISMATCH
    }
}