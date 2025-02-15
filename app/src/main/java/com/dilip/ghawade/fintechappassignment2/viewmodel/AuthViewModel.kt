package com.dilip.ghawade.fintechappassignment2.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dilip.ghawade.fintechappassignment2.model.User
import com.dilip.ghawade.fintechappassignment2.preference.UserPreferences
import com.dilip.ghawade.fintechappassignment2.room.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userDao: UserDao,
    @ApplicationContext private val context: Context, // Inject the application context
    private val userPreferences: UserPreferences,
) :
    ViewModel() {


    //    private val userPreferences = UserPreferences(application.applicationContext)
    private val _loginState = MutableStateFlow("")
    val loginState: StateFlow<String> = _loginState

    var isLoggedIn = MutableStateFlow(false)  // Track login state

    init {
        checkLoginStatus()
    }

    // Reactive state variables
    var username by mutableStateOf("")

    var password by mutableStateOf("")
    var email by mutableStateOf("")
    var mobileNo by mutableStateOf("")

    var errorMessage by mutableStateOf("")
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    // Function to update username
    fun onUsernameChange(newUsername: String) {
        username = newUsername
    }

    // Function to update password
    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onMobileNoChange(newMobileNo: String) {
        mobileNo = newMobileNo
    }

    fun registerUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _loginState.value = "" // Reset state before registration
                val result = userDao.insertUser(user)
                Log.d("AuthViewModel", "Inserted user ID: $result")

                withContext(Dispatchers.Main) {
                    _loginState.value = if (result > 0) {
                        Log.d("AuthViewModel", "User Registered Successfully")
                        "User Registered Successfully"
                    } else {
                        Log.d("AuthViewModel", "Error Registering User")
                        "Error Registering User"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _loginState.value = "Registration Failed: ${e.message}"
                    Log.e("AuthViewModel", "Registration failed", e)
                }
            }
        }
    }


    fun loginUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginState.value = "" // Reset before login
            userDao.userLogin(username, password).collectLatest { user ->
                withContext(Dispatchers.Main) {
                    _loginState.value = if (user != null) {
                        userPreferences.saveLoginStatus(true)
                        Log.d("AuthViewModel", "Login Successful")
                        "Login Successful"
                    } else {
                        Log.d("AuthViewModel", "Invalid Credentials")
                        "Invalid Credentials"
                    }
                }
            }
        }
    }

    private fun checkLoginStatus() {
        viewModelScope.launch {
            userPreferences.isLoggedIn.collect { loggedIn ->
                isLoggedIn.value = loggedIn
            }
        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            userPreferences.logout() // Clear login state
            isLoggedIn.value = false
        }
    }

}
