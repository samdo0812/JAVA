package com.example.i312_04.projectandroid0208;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import noman.googleplaces.PlaceType;


//여기가 메인 페이지 (제일 처음 뜨는 화면)

public class ProjectAndroid0212 extends AppCompatActivity {
    private BackPressCloseHandler backPressCloseHandler;

    TextView menu, text2, developer, finish;

    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_android0212);

        backPressCloseHandler = new BackPressCloseHandler(this);

        menu = (TextView)findViewById(R.id.menu);
        text2 = (TextView)findViewById(R.id.text2);
        developer = (TextView)findViewById(R.id.developer);
        finish = (TextView)findViewById(R.id.finish);


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212.this, ProjectAndroid0212Next.class);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation); //에니메이션 눌렀을 때 누른 효과주기 위해서
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212.this, MemoActivity.class);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);

            }
        });

        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212.this, ProjectAndroid0212develop.class);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);

            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //종료 실행 코드 입력
                finish();
            }
        });
    }
}
