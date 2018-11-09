package me.rhon.test;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnRegist = findViewById(R.id.register);

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText uname = findViewById(R.id.username);
                EditText pwd = findViewById(R.id.pwd);
                EditText confirm = findViewById(R.id.confirmPwd);

                final String username = uname.getText().toString();
                String pass = pwd.getText().toString();
                String confPass = confirm.getText().toString();

                if (!pass.equals(confPass)){
                    Toast.makeText(MainActivity.this, "Password tidak sama", Toast.LENGTH_LONG).show();
                }else {
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
                    String key = ref.push().getKey();
                    ref.child(key).setValue(new User(username, pass)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //call notif function
                            notif();

                            //Intent untuk perpindahan activity
                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            intent.putExtra("nama", username);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }

    private void notif(){
        //Notifikasi
        NotificationCompat.Builder builder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(getApplicationContext(), "iot")
                        .setSmallIcon(R.drawable.ic_persom)
                        .setContentTitle("Berhasil daftar")
                        .setAutoCancel(false)
                        .setContentText("Sukses Daftar");

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(1, builder.build());
    }
}
