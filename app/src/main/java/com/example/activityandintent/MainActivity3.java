package com.example.activityandintent;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        final TextView txtFullName = findViewById(R.id.txtFullName);
        final TextView txtNickName = findViewById(R.id.txtNickName);
        final TextView txtNumber = findViewById(R.id.txtNumber);
//        Person p = (Person) getIntent().getSerializableExtra("p");
        Bundle bundle = getIntent().getBundleExtra("myBundle");
        if (bundle == null) ;
        Person p = (Person) bundle.getSerializable("p");
        if(p == null) p = new Person("","","");
        txtNumber.setText(p.getNumber());
        txtNickName.setText(p.getNickName());
        txtFullName.setText(p.getFullName());


    }
}