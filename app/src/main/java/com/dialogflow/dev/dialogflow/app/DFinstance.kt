package com.dialogflow.dev.dialogflow.app

import ai.api.AIListener
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.TextView

class DFinstance(private var txtView: TextView, private var progressBar: ProgressBar) : AIListener {

    override fun onListeningStarted() {
        progressBar.visibility = VISIBLE
    }

    override fun onResult(response: ai.api.model.AIResponse?) {
        val result = response?.result

        var parameterString = ""
        if (result?.parameters != null && !result.parameters.isEmpty()) {
            result.parameters.forEach { entry -> parameterString += "(" + entry.key + ", " + entry.value + ") " }
        }
        txtView.text = result?.fulfillment?.speech ?: "Speech"
    }

    override fun onAudioLevel(level: Float) {

    }

    override fun onError(error: ai.api.model.AIError?) {
        txtView.text = error?.message
    }

    override fun onListeningCanceled() {

    }

    override fun onListeningFinished() {
        progressBar.visibility = GONE
    }

}