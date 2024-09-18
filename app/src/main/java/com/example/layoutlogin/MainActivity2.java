package com.example.layoutlogin;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvResult;
    private Button btnLogout, btnViewAccounts;

    private SharedPreferences sharedPreferences;
    private Set<String> accountSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EdgeToEdge.enable(this);

        // Ánh xạ TextView để hiển thị kết quả
        tvResult = findViewById(R.id.tvResult);
        btnLogout = findViewById(R.id.btnLogout);
        btnViewAccounts = findViewById(R.id.btnViewAccounts);


        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("AccountPrefs", MODE_PRIVATE);

        // Lấy danh sách tài khoản từ SharedPreferences
        accountSet = sharedPreferences.getStringSet("accounts", new HashSet<>());

        // Nhận Intent từ Activity1
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        accountSet.add(username + ":" + password);
        sharedPreferences.edit().putStringSet("accounts", accountSet).apply();

        // Xử lý logic kiểm tra đăng nhập
        tvResult.setText("Đăng nhập thành công! Chào " + username + "\nDescription: " + password);

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });

        btnViewAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewAccountsIntent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(viewAccountsIntent);
            }
        });
    }
}
