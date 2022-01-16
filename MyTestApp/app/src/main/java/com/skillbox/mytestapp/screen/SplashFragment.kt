package com.skillbox.mytestapp.screen

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.skillbox.mytestapp.R
import ru.agladkov.uitls.navigation.NavCommand
import ru.agladkov.uitls.navigation.NavCommands
import ru.agladkov.uitls.navigation.navigate

class SplashFragment : Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = Uri.parse("jetnavapp://mainScreen"),
                    isModal = true,
                    isSingleTop = true
                )
            )
        )
    }
}