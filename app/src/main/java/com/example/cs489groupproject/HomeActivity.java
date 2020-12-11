package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    protected SpeechRecognizer speechRecognizer;
    public static APIModel model;
    public static RunData rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        connectToApi();
    }

    public void connectToApi() {
        if (getIntent().getData() != null) {
            model = new APIModel(this, getIntent().getData().toString());
        } else {
            Log.w("MA", "no data");
        }
    }

    public void listen() {
        if(this.speechRecognizer != null)
            speechRecognizer.destroy();
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        SpeechListener speechListener = new SpeechListener();
        Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizer.startListening(speechIntent);
    }

    public void viewData(View v) {
        Intent intent = new Intent( this, DataActivity.class );
        startActivity( intent );
        overridePendingTransition( R.anim.fade_in_and_scale, 0 );
    }

    public class SpeechListener implements RecognitionListener {

        @Override
        public void onReadyForSpeech(Bundle bundle) {
            Log.w( "MA", "MA: inside onReadyForSpeech" );
        }

        @Override
        public void onBeginningOfSpeech() {
            Log.w( "MA", "MA: inside onBeginningOfSpeech" );
        }

        @Override
        public void onRmsChanged(float v) {
            Log.w( "MA", "MA: inside onRmsChanged" );
        }

        @Override
        public void onBufferReceived(byte[] bytes) {
            Log.w( "MA", "MA: inside onBufferReceived" );
        }

        @Override
        public void onEndOfSpeech() {
            Log.w( "MA", "MA: inside onEndOfSpeech" );
        }

        @Override
        public void onError(int i) {
            Log.w( "MA", "MA: inside onError, error is " + i  );
            listen( );
        }

        @Override
        public void onResults(Bundle bundle) {
            ArrayList<String> words = bundle.getStringArrayList( SpeechRecognizer.RESULTS_RECOGNITION );
            float [] scores = bundle.getFloatArray( SpeechRecognizer.CONFIDENCE_SCORES );

            // idk what we are doing with the words
        }

        @Override
        public void onPartialResults(Bundle bundle) {
            Log.w( "MA", "MA: inside onPartialResults" );
        }

        @Override
        public void onEvent(int i, Bundle bundle) {
            Log.w( "MA", "MA: inside onEvent" );
        }
    }

    public void activitiesConnect(View v) {
        model.getActivities();
        Toast.makeText(this, "Connected activities",
                Toast.LENGTH_LONG).show();
    }

    protected void onStart( ) {
        super.onStart( );
        Log.w( "MA", "Inside MainActivity::onStart" );
    }

    protected void onRestart( ) {
        super.onRestart( );
        Log.w( "MA", "Inside MainActivity::onRestart" );
    }

    protected void onResume( ) {
        super.onResume( );
        Log.w( "MA", "Inside MainActivity::onResume" );
    }

    protected void onPause( ) {
        super.onPause( );
        Log.w( "MA", "Inside MainActivity::onPause" );
    }

    protected void onStop( ) {
        super.onStop( );
        Log.w( "MA", "Inside MainActivity::onStop" );
    }

    protected void onDestroy( ) {
        super.onDestroy( );
        Log.w( "MA", "Inside MainActivity::onDestroy" );
    }

}