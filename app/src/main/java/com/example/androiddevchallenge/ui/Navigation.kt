package com.example.androiddevchallenge.ui

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


enum class ScreenName { HOME, DETAIL }

sealed class Screen(val id: ScreenName) {
    object Home : Screen(ScreenName.HOME)
    data class Detail(val dogId: String) : Screen(ScreenName.DETAIL)
}

private const val DOG_ID = "dog_id"
private const val SIS_SCREEN = "sis_screen"
private const val SIS_NAME = "screen_name"

private fun Screen.toBundle(): Bundle {
    return bundleOf(SIS_NAME to id.name).also {
        if (this is Screen.Detail) {
            it.putString(DOG_ID, dogId)
        }
    }
}

/**
 * Read a bundle stored by [Screen.toBundle] and return desired screen.
 *
 * @return the parsed [Screen]
 * @throws IllegalArgumentException if the bundle could not be parsed
 */
private fun Bundle.toScreen(): Screen {
    val screenName = ScreenName.valueOf(DOG_ID)
    return when (screenName) {
        ScreenName.HOME -> Screen.Home
        ScreenName.DETAIL -> {
            val postId = getString(DOG_ID) ?: ""
            Screen.Detail(postId)
        }
    }
}

fun <T> SavedStateHandle.getMutableStateOf(
    key: String,
    default: T,
    save: (T) -> Bundle,
    restore: (Bundle) -> T
): MutableState<T> {
    val bundle: Bundle? = get(key)
    val initial = if (bundle == null) {
        default
    } else {
        restore(bundle)
    }
    val state = mutableStateOf(initial)
    setSavedStateProvider(key) {
        save(state.value)
    }
    return state
}

class NavigationViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    var currentScreen: Screen by savedStateHandle.getMutableStateOf<Screen>(
        key = SIS_SCREEN,
        default = Screen.Home,
        save = { it.toBundle() },
        restore = { it.toScreen() }
    )
        private set // limit the writes to only inside this class.

    @MainThread
    fun onBack(): Boolean {
        val wasHandled = currentScreen != Screen.Home
        currentScreen = Screen.Home
        return wasHandled
    }

    @MainThread
    fun navigateTo(screen: Screen) {
        currentScreen = screen
    }
}
