package com.example.studijetpack.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, errorMessage = null) }
    }

    fun onTogglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onRememberMeChanged(checked: Boolean) {
        _uiState.update { it.copy(rememberMe = checked) }
    }

    fun login() {
//        val userName = _uiState.value.email
//        val password = _uiState.value.password
        val userName = "Bentley"
        val password = "Abc@1234"
        // Validation
        if (userName.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Vui lòng nhập Email!") }
            return
        }
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            _uiState.update { it.copy(errorMessage = "Email không đúng định dạng!") }
//            return
//        }
        if (password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Vui lòng nhập Mật khẩu!") }
            return
        }
        if (password.length < 6) {
            _uiState.update { it.copy(errorMessage = "Mật khẩu phải từ 6 ký tự!") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            // Giả lập gọi API (Có thể thay bằng UserRepository)
            delay(1500)

            if (userName == "Bentley" && password == "Abc@1234") {
                _uiState.update { it.copy(isLoading = false, isLoginSuccess = true) }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Tên đăng nhập hoặc mật khẩu không đúng!"
                    )
                }
            }
        }
    }
    fun resetLoginState() {
        _uiState.update { it.copy(isLoginSuccess = false) }
    }
}