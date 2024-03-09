package com.example.login;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
private EditText etUsername, etPassword;
private Button btnRegister, btnLogin;
private DatabaseHelper databaseHelper;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
etUsername = findViewById(R.id.etUsername);
etPassword = findViewById(R.id.etPassword);
btnRegister = findViewById(R.id.btnRegister);
btnLogin = findViewById(R.id.btnLogin);
databaseHelper = new DatabaseHelper(this);
btnRegister.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
registerUser();
}
});
btnLogin.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
loginUser();
}
});
}
private void registerUser() {
String username = etUsername.getText().toString().trim();
String password = etPassword.getText().toString().trim();
if (username.isEmpty() || password.isEmpty()) {
Toast.makeText(this, "Please enter both username and password",
Toast.LENGTH_SHORT).show();
return;
}
User user = new User(username, password);
long result = databaseHelper.addUser(user);
if (result != -1) {
Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
} else {
Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
}
}
private void loginUser() {
String username = etUsername.getText().toString().trim();
String password = etPassword.getText().toString().trim();
if (databaseHelper.checkUser(username, password)) {
Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
} else {
Toast.makeText(this, "Login failed. Check your username and password",
Toast.LENGTH_SHORT).show();
}
}
}
