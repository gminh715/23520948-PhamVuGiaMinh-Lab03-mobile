package com.example.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvItems = findViewById(R.id.lvItems);

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("Harry", "San Diego"));
        users.add(new User("Marla", "San Francisco"));
        users.add(new User("Sarah", "San Marco"));

        UsersAdapter adapter = new UsersAdapter(this, users);
        lvItems.setAdapter(adapter);
    }
}

