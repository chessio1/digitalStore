package com.skillbox.mytestapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.core.utils.exceptions.NoDataException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.agladkov.uitls.navigation.NavCommand
import ru.agladkov.uitls.navigation.NavCommands
import ru.agladkov.uitls.navigation.NavigationProvider
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main),NavigationProvider{
    private val navController: NavController
        get() = findNavController(R.id.fragmentContainerView2)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


    }

    override fun onResume() {
        super.onResume()
        Timber.d("extra123")
        val extras = intent.extras?.getString("screen")
        Timber.d("extra123 $extras")
    }

    override fun launch(navCommand: NavCommand) {
        when (val target = navCommand.target) {
            is NavCommands.DeepLink -> openDeepLink(
                url = target.url
            )
            is NavCommands.Browser -> openBrowser(url = target.url)
        }
    }

    private fun openDeepLink(url: Uri) {
        val navOptions =
            NavOptions.Builder()
                .build()

        navController.navigate(url, navOptions)
    }

    private fun openBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        browserIntent.setPackage("com.android.chrome")
        try {
            this.startActivity(browserIntent)
        } catch (ex: ActivityNotFoundException) {
            browserIntent.setPackage(null)
            this.startActivity(browserIntent)
        }
    }

}
