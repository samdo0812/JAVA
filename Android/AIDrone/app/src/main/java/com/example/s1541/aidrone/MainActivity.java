package com.example.s1541.aidrone;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private final int PERMISSIONS_REQUEST_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SystemClock.sleep(2000);
        setTheme(R.style.AppTheme);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //화면 고정

        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECORD_AUDIO)) {
                //권한을 거절하면 재 요청을 하는 함수
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST_RESULT);
            }
        }

        // Initializing the TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tabmic).setText("AI Drone"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.book).setText("Explanation"));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.cont).setText("Controller"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Initializing ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        // Creating TabPagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (PERMISSIONS_REQUEST_RESULT == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "권한 요청이 됐습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "권한 요청을 해주세요.", Toast.LENGTH_SHORT).show();
                finish();
            }
            return;
        }

    }

    public void listenButtonOnClick(final View view) {  //Image Button이 눌린다면
        TabFragment1.aiService.startListening();     //인식 시작
        ImageButton listenButton = (ImageButton) findViewById(R.id.listenButton);
        listenButton.setImageResource(R.drawable.mic2);
    }

    public void func01(View view) {     //비상착륙 버튼
        new Thread() {
            public void run() {
                try {
                    InetAddress serverAddr = InetAddress.getByName("192.168.10.1"); //정보를 전송할 IP 주소

                    DatagramSocket socket = new DatagramSocket();   //소켓 선언

                    byte[] buf = ("command").getBytes();    //보낼 정보가 들어간 메모리 작성
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);  //패킷에 정보가 들어간 메모리와 IP주소, port

                    socket.send(packet);    //소켓에 패킷(데이터의 블럭 단위)의 내용을 전달

                    buf = ("land").getBytes();
                    packet = new DatagramPacket(buf, buf.length, serverAddr, 8889);

                    socket.send(packet);

                } catch(Exception e) {
                    e.printStackTrace();    //Exception의 내용 출력
                }
            }
        }.start();
    }

    public void controller(View view) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
        finish();
    }

}