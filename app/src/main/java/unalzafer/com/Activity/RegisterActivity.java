package unalzafer.com.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;

import unalzafer.com.Models.UserModel;
import unalzafer.com.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername,etPassword,etPasswordAgain,etBirthDay;
    private Button btRegister;
    private int mYear, mMonth, mDay;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etBirthDay=(EditText)findViewById(R.id.etBirthDay);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etPasswordAgain=(EditText) findViewById(R.id.etPasswordAgain);
        btRegister=(Button)findViewById(R.id.btRegister);

        etBirthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                etBirthDay.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        final UserModel userModel=new UserModel();
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etUsername.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                String birthDay=etBirthDay.getText().toString().trim();
                userModel.setUsername(username);
                userModel.setBirthday(birthDay);
                userModel.setPassword(password);
                if(!username.equals("")&& !password.equals("")&& !birthDay.equals("")){
                    if(password.equals(etPasswordAgain.getText().toString().trim())) {
                        SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
                        Gson gson = new Gson();
                        String json = gson.toJson(userModel);
                        sharedPreferences.edit().putString("userLogin", json).apply();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }else
                        Toast.makeText(getApplicationContext(),"Şifreler Eşleşmedi.",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),"Alanları Boş Geçemezsiniz.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
