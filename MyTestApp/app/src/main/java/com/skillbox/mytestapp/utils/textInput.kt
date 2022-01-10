package com.skillbox.mytestapp.utils

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber

fun TextInputEditText.textChangedFlow(): Flow<String> {
    return callbackFlow {
        val listener = object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                trySendBlocking(s.toString())
                    .onSuccess {
                        Timber.d("Success")
                    }
                    .onFailure {
                        Timber.d("Failure")
                    }
            }
        }

        this@textChangedFlow.addTextChangedListener(listener)
        awaitClose {
            this@textChangedFlow.removeTextChangedListener(listener)
        }

    }
}