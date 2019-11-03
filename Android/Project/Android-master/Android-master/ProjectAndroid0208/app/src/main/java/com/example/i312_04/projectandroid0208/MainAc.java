package com.example.i312_04.projectandroid0208;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainAc extends AppCompatActivity {

    private  static  String TAG = "MainAc";
    Button load,save,delete;
    EditText inputText, aaa1;

    Button backMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load = findViewById(R.id.load);
        save = findViewById(R.id.save);
        delete = findViewById(R.id.delete);
        inputText = findViewById(R.id.inputText);
        aaa1 =  findViewById(R.id.aaa1);

        load.setOnClickListener(listener);
        save.setOnClickListener(listener);
        delete.setOnClickListener(listener);

        backMemo = findViewById(R.id.backMemo);

        backMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String filename = aaa1.getText().toString()+".txt";
            switch (view.getId()){
                case R.id.load:
                    Log.i("TAG","load 진행");

                    FileInputStream fis = null;
                    try{
                        fis = openFileInput(filename);
                        byte[] data = new byte[fis.available()];
                        while (fis.read(data)!= -1){
                        }
                        inputText.setText(new String(data));
                        Toast.makeText(MainAc.this,"load 완료",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        try{if(fis != null)fis.close();}catch (Exception e){e.printStackTrace();}
                    }
                    break;
                case R.id.save:
                    Log.i("TAG","save 진행");
                    FileOutputStream fos = null;
                    //String filename2 = String.format("%s");
                    try {
                        fos= openFileOutput(filename, Context.MODE_PRIVATE);
                        String out = inputText.getText().toString();
                        fos.write(out.getBytes());
                        Toast.makeText(MainAc.this,"save 완료",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally{
                        try {if(fos != null)fos.close();}catch (Exception e){e.printStackTrace();}
                    }
                    break;
                case R.id.delete:
                    Log.i("TAG","delete 진행");
                    boolean b = deleteFile(filename);
                    if(b){
                        Toast.makeText(MainAc.this,"delete 완료", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainAc.this, "delete 실패",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

}
