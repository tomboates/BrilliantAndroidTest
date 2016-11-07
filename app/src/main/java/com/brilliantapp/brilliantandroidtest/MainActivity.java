package com.brilliantapp.brilliantandroidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.content.Intent;
import com.brilliant.sdk.Brilliant;
import com.brilliant.sdk.Constants;
import com.brilliant.sdk.BrilliantSurveyResult;

public class MainActivity extends AppCompatActivity {

    public void launchSurvey(View view) {

        Brilliant.getInstance().showNPS(this, "Android Test!");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The SDK must be initalized in a background thread
        new Thread(new Runnable() {
            public void run() {
                try {
                    if (!Brilliant.isInitialized()) {
                        Brilliant.initialize(getApplicationContext(), "8763989873555555555976492");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.BRILLIANT_REQUEST_CODE) {
            if (resultCode == this.RESULT_OK) {
                //handle the brilliant response
                Bundle extras = data.getExtras();
                BrilliantSurveyResult surveyResult = extras.getParcelable(Constants.SURVEY_EXTRAS_KEY);

                //Do whatever with the survey
            }
        }
    }

}