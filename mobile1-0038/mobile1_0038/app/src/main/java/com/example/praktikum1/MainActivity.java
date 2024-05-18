package com.example.praktikum1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;

        if (requestCode == REQ_CODE_ACTIVITY2) {
            if (resultCode == MainActivity.RESULT_OK) {
                float nilaiAkhir;
                float nTgs, Mid, nFinal;
                nTgs = Float.parseFloat(data.getStringExtra(KEY_NILAI_TUGAS));
                float nMid = Float.parseFloat(data.getStringExtra(KEY_NILAI_MID));
                nFinal = Float.parseFloat(data.getStringExtra(KEY_NILAI_FINAL));
                nilaiAkhir = (nTgs + nMid + nFinal) / 3;
                txtNilaiAkhir.setText(":  " + nilaiAkhir);

                char indeks = ' ';
                if (nilaiAkhir >= 90 && nilaiAkhir <= 100)
                    indeks = 'A';
                else if (nilaiAkhir > 80 && nilaiAkhir < 90)
                    indeks = 'B';
                else if (nilaiAkhir >= 70 && nilaiAkhir < 80)
                    indeks = 'C';
                else if (nilaiAkhir > 45 && nilaiAkhir < 70)
                    indeks = 'D';
                else if (nilaiAkhir < 45)
                    indeks = 'E';

                txtIndeks.setText(":  " + indeks);
            } else if (resultCode == RESULT_CANCEL) {
                txtStb.setText("");
                txtNama.setText("");
                txtNilaiAkhir.setText(":");
                txtIndeks.setText(":");
                Toast.makeText(this, "Input Nilai dibatalkan...", Toast.LENGTH_SHORT).show();
                txtStb.requestFocus();
            }
        }
    }



    // Instance Variables
    private EditText txtStb, txtNama;
    private TextView txtNilaiAkhir, txtIndeks;

    // Constants
    static final String KEY_STB = "STB";
    static final String KEY_NAMA = "NAMA";
    static final String KEY_NILAI_TUGAS = "NILAI_TUGAS";
    static final String KEY_NILAI_MID = "NILAI_MID";
    static final String KEY_NILAI_FINAL = "NILAI_FINAL";
    static final int RESULT_OK = 1;
    static final int RESULT_CANCEL = 0;
    private final int REQ_CODE_ACTIVITY2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Mengaktifkan EdgeToEdge untuk menyesuaikan padding dengan sistem UI
        EdgeToEdge.enable(this);

        // Mengatur layout activity_main.xml sebagai tata letak tampilan utama
        setContentView(R.layout.activity_main);

        // Mengatur padding untuk layout utama agar sesuai dengan sistem UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi objek EditText, TextView, dan Button
        txtStb = findViewById(R.id.txt_edit_stb);
        txtNama = findViewById(R.id.txt_edit_nama);
        txtNilaiAkhir = findViewById(R.id.txt_nilai_akhir);
        txtIndeks = findViewById(R.id.txt_index);

        // Set teks awal untuk txtNilaiAkhir dan txtIndeks
        txtNilaiAkhir.setText(":");
        txtIndeks.setText(":");
    }

    // Method untuk membuka Activity2 dan mengirim data
    public void bukaActivity2(View view) {
        // Membuat Intent untuk membuka Activity2
        Intent intent = new Intent(this, Activity2.class);

        // Menambahkan data Stambuk dan Nama ke dalam Intent Extras
        intent.putExtra(KEY_STB, txtStb.getText().toString());
        intent.putExtra(KEY_NAMA, txtNama.getText().toString());

        // Mengatur teks awal untuk txtNilaiAkhir dan txtIndeks
        txtNilaiAkhir.setText(":");
        txtIndeks.setText(":");

        // Memulai Activity2 dan menunggu respon yang akan dikembalikan
        startActivityForResult(intent, REQ_CODE_ACTIVITY2);
    }

}
