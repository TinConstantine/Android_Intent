package com.example.activityandintent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PERMISSION = 1;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
       Button btnOpenPage1 = findViewById(R.id.btnOpenPage1);
       Button btnOpenPage2 = findViewById(R.id.btnOpenPage2);
       btnOpenPage1.setOnClickListener(v -> {
           startActivity(new Intent(MainActivity.this, MainActivity2.class ).putExtra(
                "str", "Hello world!"
           ));
       });

       Person p = new Person("Mr Freeze", "0123456789","You know who");



       btnOpenPage2.setOnClickListener(v -> {
           // normal
//           startActivity(new Intent(MainActivity.this, MainActivity3.class).putExtra("p", p));
           // with Bundle
           Bundle bundle = new Bundle();
           bundle.putSerializable("p", p);
           startActivity(new Intent(MainActivity.this, MainActivity3.class).putExtra("myBundle", bundle));
       });



        EditText txtA = findViewById(R.id.txtA);
        EditText txtB = findViewById(R.id.txtB);
        Button btnCalculator = findViewById(R.id.btnCalculator);
        txtResult = findViewById(R.id.txtResult);



        @SuppressLint("SetTextI18n") ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        txtResult.setText(Integer.toString(result.getData().getIntExtra("result", 0)));

                    }
                }
        );
        btnCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity4.class);
            intent.putExtra("a", Integer.parseInt(txtA.getText().toString()));
            intent.putExtra("b", Integer.parseInt(txtB.getText().toString()));
            // old
//            startActivityForResult(intent, 33);

            //new
            activityResultLauncher.launch(intent);
        });


        EditText txtPhone = findViewById(R.id.txtPhoneNumber);
        EditText txtContent = findViewById(R.id.txtContent);
        Button btnCall = findViewById(R.id.btnCall);
        Button btnCallNow =  findViewById(R.id.btnCallNow);
        Button btnSendMessage = findViewById(R.id.btnSendMessage);

        btnCall.setOnClickListener(v -> {
            Uri uri = Uri.parse("tel:"+txtPhone.getText().toString());

            startActivity(new Intent(Intent.ACTION_DIAL).setData(uri));
        });

        btnCallNow.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
            }
            else {
                Uri uri = Uri.parse("tel:"+txtPhone.getText().toString());

                Intent intent =new Intent(Intent.ACTION_CALL).setData(uri);
                startActivity(intent);
            }
        });



    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 33 && resultCode == 99){
//            assert data != null;
//            txtResult.setText(Integer.toString(data.getIntExtra("result", 0))); ;
//        }
//    }



}

class Person implements Serializable{
    private String fullName;
    private String number;
    private String nickName;

    public Person(String fullName, String number, String nickName) {
        this.fullName = fullName;
        this.number = number;
        this.nickName = nickName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}