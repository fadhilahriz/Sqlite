package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.database.DBControl;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class EditData extends AppCompatActivity {
    private TextInputEditText tNama, tTelpon;
    private Button btnSave;
    String id,nm, tlp;
    DBControl controller = new DBControl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        tNama = (TextInputEditText) findViewById(R.id.ednama);
        tTelpon = (TextInputEditText) findViewById(R.id.edtelpon);
        btnSave = (Button) findViewById(R.id.edbtn);

        id = getIntent().getStringExtra("id");
        nm = getIntent().getStringExtra("nm");
        tlp = getIntent().getStringExtra("tlp");

        tNama.setText(nm);
        tTelpon.setText(tlp);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tNama.getText().toString().equals("") || (tTelpon.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "Data belum lengkap!!", Toast.LENGTH_LONG);
                } else {
                    nm = tNama.getText().toString();
                    tlp = tTelpon.getText().toString();

                    HashMap<String,String> qvalues = new HashMap<>();
                    qvalues.put("id",id);
                    qvalues.put("nama",nm);
                    qvalues.put("telpon",tlp);

                    controller.editData(qvalues);
                    callHome();
                }
            }
        });
    }

    public void callHome() {
        Intent intent = new Intent(EditData.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}