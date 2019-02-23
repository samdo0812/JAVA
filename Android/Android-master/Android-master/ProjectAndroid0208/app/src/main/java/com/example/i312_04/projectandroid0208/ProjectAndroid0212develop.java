package com.example.i312_04.projectandroid0208;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProjectAndroid0212develop extends AppCompatActivity {

    TextView backmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_android0212develop);

        backmain = (TextView)findViewById(R.id.backmain);

        backmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent it = new Intent(ProjectAndroid0212develop.this, ProjectAndroid0212.class);
                startActivity(it);*/
                finish();
            }
        });
    }
}
