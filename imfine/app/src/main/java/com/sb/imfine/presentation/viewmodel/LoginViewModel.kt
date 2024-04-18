package com.sb.imfine.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sb.imfine.data.repository.UserRepositoryImpl
import com.sb.imfine.domain.data.UserDomain
import com.sb.imfine.domain.repository.UserRepository
import com.sb.imfine.presentation.data.Event
import com.sb.imfine.presentation.data.Screens
import com.sb.imfine.presentation.data.UserViewData
import com.sb.imfine.presentation.data.toViewData
import com.sb.imfine.util.isBirthdayValidate
import com.sb.imfine.util.isNameValidate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    private val userRepository: UserRepository = UserRepositoryImpl()

    private val _event = MutableStateFlow<Event?>(null)
    val event: StateFlow<Event?>
        get() = _event.asStateFlow()

    private val _user: MutableStateFlow<UserViewData> = MutableStateFlow(UserViewData("", 0L, byteArrayOf()))
    val user: StateFlow<UserViewData>
        get() = _user.asStateFlow()

    private fun getUser() {
        viewModelScope.launch {
            userRepository.getUser().collect {
                _user.emit(it.toViewData())
            }
        }
    }

    fun saveUser(name: String, birthDay: Long, profileImageByteArray: ByteArray) {
        viewModelScope.launch {
            if (!isNameValidate(name)) {
                _event.emit(Event.ToastEvent("이름을 다시 한번 확인해주세요."))
                return@launch
            }
            if (!isBirthdayValidate(birthDay)) {
                _event.emit(Event.ToastEvent("생년월일을 다시 한번 확인해주세요."))
                return@launch
            }
            val newUser = UserDomain(
                name = name,
                birthDay = birthDay,
                profileImage = profileImageByteArray
            )
            if (withContext(Dispatchers.IO) { userRepository.saveUser(newUser) }) {
                _event.emit(Event.NavigateEvent(Screens.LoginScreens.LoginScreen.route))
            }
        }
    }

    init {
        getUser()
    }
}