package unalzafer.com.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import unalzafer.com.Models.UserModel;
import unalzafer.com.R;

public class LoginActivity extends AppCompatActivity {
    UserModel userModel;
    Button btLogin,btRegister;
    EditText etUsername,etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btLogin=(Button)findViewById(R.id.btLogin);
        btRegister=(Button)findViewById(R.id.btRegister);

        SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        if(sharedPreferences.getString("userLogin",null)!=null) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userLogin", null);
            Type type = new TypeToken<UserModel>() {
            }.getType();
            userModel = gson.fromJson(json, type);
            Log.d("userLogin",""+userModel.getUsername()+"-"+userModel.getPassword());
        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etUsername.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                if(!username.equals("")&& !password.equals("")){
                    if(username.equals(userModel.getUsername())&& password.equals(userModel.getPassword()))
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    else
                        Toast.makeText(getApplicationContext(),"Mail Adresi veya Şifre Hatalı.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Alanları Boş Geçemezsiniz.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
    }
}
