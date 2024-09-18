package com.example.layoutlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity3 extends AppCompatActivity {

    private ListView lvUserAccounts;
    private Button btnBack;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Ánh xạ ListView và Button
        lvUserAccounts = findViewById(R.id.lvUserAccounts);
        btnBack = findViewById(R.id.btnBack);

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("AccountPrefs", MODE_PRIVATE);

        // Lấy danh sách tài khoản từ SharedPreferences
        Set<String> accountSet = sharedPreferences.getStringSet("accounts", null);

        // Chuyển Set thành List<String>
        List<String> accountList = new ArrayList<>();
        if (accountSet != null) {
            accountList.addAll(accountSet);
        }

        // Hiển thị danh sách tài khoản trong ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, accountList);
        lvUserAccounts.setAdapter(adapter);

//         Xử lý sự kiện khi nhấn nút Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(backIntent);
            }
        });
    }
}

