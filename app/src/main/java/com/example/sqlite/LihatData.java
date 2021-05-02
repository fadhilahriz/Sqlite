package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.database.DBControl;

public class LihatData extends AppCompatActivity {
    protected Cursor cursor;
    TextView tid,tnama,tnotel;
    Button back;
    DBControl controller = new DBControl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        tid = (TextView) findViewById(R.id.tvId);
        tnama = (TextView) findViewById(R.id.tvNama);
        tnotel = (TextView) findViewById(R.id.tvNomorTelepon);

        SQLiteDatabase db = controller.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM teman WHERE id = '" +
                getIntent().getStringExtra("id") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            cursor.moveToPosition(0);
            tid.setText(cursor.getString(0).toString());
            tnama.setText(cursor.getString(1).toString());
            tnotel.setText(cursor.getString(2).toString());
        }

        back = (Button) findViewById(R.id.btkembali);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
    });
    }
}