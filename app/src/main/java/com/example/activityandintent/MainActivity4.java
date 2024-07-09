package com.example.activityandintent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity4 extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        TextView txt = findViewById(R.id.txtCalculator);
        Button btn = findViewById(R.id.btnBack);
        btn.setOnClickListener(v -> {
            int a = getIntent().getIntExtra("a", 0);
            int b = getIntent().getIntExtra("b", 0);
            int result = a + b;
            txt.setText(Integer.toString(result));
            getIntent().putExtra("result", result);
            setResult(Activity.RESULT_OK, getIntent());
            finish();
        });


    }
}