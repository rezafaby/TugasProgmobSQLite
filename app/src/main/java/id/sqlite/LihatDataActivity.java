package id.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class LihatDataActivity extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper database;
    Button btn_simpan;
    TextView nama, jenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
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
    }
}