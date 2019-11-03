package com.example.i312_04.projectandroid0208;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import noman.googleplaces.PlaceType;

public class ProjectAndroid0212Next extends AppCompatActivity {

    TextView restaurant, convenience, atm, bakery, subway, postoffice, hospital, library;
    TextView bank, cafe, busstation, lodging, gasstation, movie, spa, laundry;
    TextView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_android0212_next);

        restaurant = (TextView)findViewById(R.id.restaurant);
        convenience = (TextView)findViewById(R.id.convenience);
        atm = (TextView)findViewById(R.id.atm);
        bakery = (TextView)findViewById(R.id.bakery);
        subway = (TextView)findViewById(R.id.subway);
        postoffice = (TextView)findViewById(R.id.postoffice);
        hospital = (TextView)findViewById(R.id.hospital);
        library = (TextView)findViewById(R.id.library);
        bank = (TextView)findViewById(R.id.bank);
        cafe = (TextView)findViewById(R.id.cafe);
        busstation = (TextView)findViewById(R.id.busstation);
        lodging = (TextView)findViewById(R.id.lodging);
        gasstation = (TextView)findViewById(R.id.gasstation);
        movie = (TextView)findViewById(R.id.movie);
        spa = (TextView)findViewById(R.id.spa);
        laundry = (TextView)findViewById(R.id.laundry);
        back = (TextView)findViewById(R.id.back);


        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("txt1_KEY", PlaceType.RESTAURANT);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        convenience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.CONVENIENCE_STORE);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        atm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.ATM);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        bakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.BAKERY);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        subway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.SUBWAY_STATION);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        postoffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.POST_OFFICE);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.HOSPITAL);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.LIBRARY);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.BANK);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        cafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.CAFE);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        busstation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.BUS_STATION);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        lodging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.LODGING);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        gasstation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.GAS_STATION);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.MOVIE_THEATER);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        spa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.SPA);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ProjectAndroid0212Next.this, TestMap.class);
                it.putExtra("text1_KEY", PlaceType.LAUNDRY);
                startActivity(it);
                overridePendingTransition(R.anim.animation2, R.anim.animation);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent it = new Intent(ProjectAndroid0212Next.this, ProjectAndroid0212.class);
                startActivity(it);*/
                overridePendingTransition(R.anim.animation4, R.anim.animation3);
                finish();
            }
        });

    }
}
