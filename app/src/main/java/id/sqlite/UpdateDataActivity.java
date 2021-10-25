package id.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDataActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper database;
    Button btn_simpan;
    EditText nama, jenis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        database = new DataHelper(this);
        nama = findViewById(R.id.nama);
        jenis = findViewById(R.id.jenis);
        btn_simpan = findViewById(R.id.btnsimpan);

        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM animal WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(0).toString());
            jenis.setText(cursor.getString(1).toString());

        }
        btn_simpan.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("update animal set nama= '" +
                        nama.getText().toString() + "', jenis= '" +
                        jenis.getText().toString() + "' where nama ='" +
                        getIntent().getStringExtra("nama") + "'");
                Toast.makeText(UpdateDataActivity.this, "Data Berhasil Dipdate", Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        }));
    }
}