package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn_add, btn_del, btn_update, btn_view;
    private EditText edit_id, edit_name, edit_address, edit_phone, edit_job;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_del = (Button) findViewById(R.id.btn_delete);
        btn_view = (Button) findViewById(R.id.btn_view);
        edit_id = (EditText) findViewById(R.id.edit_id);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_address = (EditText) findViewById(R.id.edit_address);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        edit_job = (EditText) findViewById(R.id.edit_job);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit_id.getText().toString();
                String name = edit_name.getText().toString();
                String address = edit_address.getText().toString();
                String phone = edit_phone.getText().toString();
                String job = edit_job.getText().toString();
                boolean bl = database.insertDatabase(id,name, address , phone , job);
                if(bl == false){
                    Toast.makeText(MainActivity.this,"Add Fail", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this,"Add Success", Toast.LENGTH_SHORT).show();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit_id.getText().toString();
                String name = edit_name.getText().toString();
                String address = edit_address.getText().toString();
                String phone = edit_phone.getText().toString();
                String job = edit_job.getText().toString();
                boolean bl = database.updateDatabase(id,name, address , phone , job);
                if(bl = false){
                    Toast.makeText(MainActivity.this,"Update Data Fail", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this,"Update Success", Toast.LENGTH_SHORT).show();
            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit_id.getText().toString();

                boolean bl = database.deleteDatabase(id);
                if(bl == false){
                    Toast.makeText(MainActivity.this,"Delete Data Fail", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this,"Delete Success", Toast.LENGTH_SHORT).show();
            }

        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =  database .getAllData();
                if(res.getCount() == 0){
                    showMessage("Error","Empty");
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id :" +res.getString(0)+"\n");
                    buffer.append("Name :" +res.getString(1)+"\n");
                    buffer.append("Address :" +res.getString(2)+"\n");
                    buffer.append("Phone :" +res.getString(3)+"\n");
                    buffer.append("Job :" +res.getString(4)+"\n");
                }
                showMessage("Data",buffer.toString());
            }
        });

    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder  = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    

}