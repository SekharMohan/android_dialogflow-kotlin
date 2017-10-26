package com.dialogflow.dev.dialogflow

import ai.api.AIConfiguration.SupportedLanguages
import ai.api.AIListener
import ai.api.android.AIConfiguration
import ai.api.android.AIService
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dialogflow.dev.dialogflow.app.DFinstance
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class MainActivity : AppCompatActivity() {

    private lateinit var aiService:AIService
    private lateinit var listenButton:Button
    private lateinit var txtView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeWidgets()
        aiService = createAiService()
    }

    private fun createAiConfig(): AIConfiguration {
        return AIConfiguration("[api-key]",
                SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System)
    }
    private fun createAiService():AIService{
        val aiService = AIService.getService(this, createAiConfig())
        aiService.setListener(DFinstance(txtView))
        return aiService
    }

    fun listenButtonOnClick(view: View) {
        aiService.startListening()
    }

    private fun initializeWidgets(){
        listenButton = findViewById<Button>(R.id.listen_button) as Button
        txtView = findViewById<TextView>(R.id.text_view) as TextView
    }
}
