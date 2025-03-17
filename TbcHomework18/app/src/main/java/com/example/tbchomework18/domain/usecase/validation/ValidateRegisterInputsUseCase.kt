package com.example.tbchomework18.domain.usecase.validation

import com.example.tbchomework18.domain.utils.ValidateRegisterInputsUtils


interface ValidateRegisterInputsUseCase {
    operator fun invoke(
        email: String,
        password: String,
        repeatPassword: String,
    ): ValidateRegisterInputsUtils.RegisterValidationResult
}

class ValidateRegisterInputsUseCaseImpl : ValidateRegisterInputsUseCase {
    override fun invoke(
        email: String,
        password: String,
        repeatPassword: String,
    ): ValidateRegisterInputsUtils.RegisterValidationResult {
        return when {
            email.isEmpty() -> ValidateRegisterInputsUtils.RegisterValidationResult.Error(
                ValidateRegisterInputsUtils.RegisterValidationError.EMAIL_EMPTY
            )

            password.isEmpty() -> ValidateRegisterInputsUtils.RegisterValidationResult.Error(
                ValidateRegisterInputsUtils.RegisterValidationError.PASSWORD_EMPTY
            )

            repeatPassword.isEmpty() -> ValidateRegisterInputsUtils.RegisterValidationResult.Error(
                ValidateRegisterInputsUtils.RegisterValidationError.REPEAT_PASSWORD_EMPTY
            )

            repeatPassword != password -> ValidateRegisterInputsUtils.RegisterValidationResult.Error(
                ValidateRegisterInputsUtils.RegisterValidationError.PASSWORD_MISMATCH
            )

            else -> ValidateRegisterInputsUtils.RegisterValidationResult.Success
        }
    }
}
