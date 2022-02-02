package ru.agladkov.uitls.navigation

import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.navigate(url:String) {
    val navCommand: NavCommand = NavCommand(
        target = NavCommands.DeepLink(
            Uri.parse(
                url
            ),
            false,
            true
        )
    )
    (requireActivity() as? NavigationProvider)?.launch(navCommand)
}