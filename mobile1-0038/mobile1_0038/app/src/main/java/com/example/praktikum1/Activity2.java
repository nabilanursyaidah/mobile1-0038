package com.example.praktikum1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {

    // Instance Variables
    private TextView txtStb, txtNama;
    private EditText txtNilaiTugas, txtNilaiFinal, txtNilaiMid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi objek TextView dan EditText
        txtStb = findViewById(R.id.txt_stb);
        txtNama = findViewById(R.id.txt_nama);
        txtNilaiTugas = findViewById(R.id.txt_input_nilai_tugas);
        txtNilaiMid = findViewById(R.id.txt_input_nilai_mid);
        txtNilaiFinal = findViewById(R.id.txt_input_nilai_final);

        // Mengambil data yang dipassing dari MainActivity melalui Intent Extras
        Intent intent = getIntent();
        String stb, nama;

        stb = intent.getStringExtra(MainActivity.KEY_STB);
        nama = intent.getStringExtra(MainActivity.KEY_NAMA);

        // Set teks untuk TextView txtStb dan txtNama
        txtStb.setText(stb);
        txtNama.setText(nama);
    }

    // Method untuk menyelesaikan input dan kembali ke MainActivity
    public void inputSelesai(View view) {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.KEY_NILAI_TUGAS, txtNilaiTugas.getText().toString());
        intent.putExtra(MainActivity.KEY_NILAI_MID, txtNilaiMid.getText().toString());
        intent.putExtra(MainActivity.KEY_NILAI_FINAL, txtNilaiFinal.getText().toString());
        setResult(MainActivity.RESULT_OK, intent);
        finish();
    }

    // Method untuk membatalkan input dan kembali ke MainActivity
    public void inputBatal(View view) {
        Intent intent = new Intent();
        setResult(MainActivity.RESULT_CANCEL, intent);
        finish();
    }
}
