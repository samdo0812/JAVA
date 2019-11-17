package practice.kim.makeinstagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.btn_call);
        button.setOnClickListener(this);







    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");  //d -> debug , syso 역할
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }


    //intent practice
    @Override
    public void onClick(View v) {

       // Intent intent = new Intent();
       // intent.setAction(Intent.ACTION_DIAL);
        // startActivity(intent);


        //명시적 인텐트
        Intent intent = new Intent(MainActivity.this, CallActivity.class);
        intent.putExtra("intent-message","hello");

        startActivity(intent);

        //Toast.makeText(this,"Click", Toast.LENGTH_SHORT).show();
    }
}
