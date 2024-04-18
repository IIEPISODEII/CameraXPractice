package com.sb.imfine.presentation.data

sealed interface Screens {
    sealed class LoginScreens(val route: String): Screens {
        object LoginScreen: LoginScreens(route = "Login")
        object RegisterScreen: LoginScreens(route = "Register")
    }

    sealed class TaskScreens(val route: String): Screens {
        object TodoListScreen: TaskScreens(route = "TodoList")
        object AddTaskScreen: TaskScreens(route = "AddTaskScreen")
        object EditTaskScreen: TaskScreens(route = "EditTaskScreen")
    }
}