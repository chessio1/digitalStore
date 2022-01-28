package com.skillbox.mytestapp


import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import ru.agladkov.uitls.navigation.NavCommand
import ru.agladkov.uitls.navigation.NavCommands
import ru.agladkov.uitls.navigation.NavigationProvider


class MainActivity : AppCompatActivity(R.layout.activity_main), NavigationProvider {
    private val navController: NavController
        get() = findNavController(R.id.fragmentContainerView2)

    override fun launch(navCommand: NavCommand) {
        val target = navCommand.target as NavCommands.DeepLink
        openDeepLink(url = target.url)
    }

    private fun openDeepLink(url: Uri) {
        val navOptions =
            NavOptions.Builder()
                .build()
        navController.navigate(url, navOptions)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

}
