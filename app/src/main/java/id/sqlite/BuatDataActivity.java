package id.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import id.sqlite.DataHelper;
import id.sqlite.R;

public class BuatDataActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper database;
    Button btn_simpan;
    EditText nama, jenis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_data);
        database = new DataHelper(this);
        nama = findViewById(R.id.nama);
        jenis = findViewById(R.id.jenis);
        btn_simpan = findViewById(R.id.btnsimpan);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("insert into animal(nama, jenis) values('" +
                        nama.getText().toString() + "','" +
                        jenis.getText().toString() + "')");

                Toast.makeText(BuatDataActivity.this,"Data Tersimpan", Toast.LENGTH_SHORT).show();
                id.sqlite.MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}