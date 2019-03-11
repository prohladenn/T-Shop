package com.example.tshop.t_shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    ArrayList<Order> orders;
    OrderAdapter orderAdapter;
    RecyclerView recyclerView;
    ImageView buttonBack;

    TextView problemOne;
    TextView problemTwo;
    TextView problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        buttonBack = findViewById(R.id.orders_button_back);
        recyclerView = findViewById(R.id.orders_recycler_view);

        problemOne = findViewById(R.id.orders_text_one);
        problemOne.setOnClickListener(v -> {
            Intent intent = new Intent(OrdersActivity.this, HelpActivity.class);
            intent.putExtra("problem", 1);
            startActivity(intent);
        });

        problemTwo = findViewById(R.id.orders_text_two);
        problemTwo.setOnClickListener(v -> {
            Intent intent = new Intent(OrdersActivity.this, HelpActivity.class);
            intent.putExtra("problem", 2);
            startActivity(intent);
        });

        problem = findViewById(R.id.orders_text_three);
        problem.setOnClickListener(v -> {
            Intent intent = new Intent(OrdersActivity.this, HelpActivity.class);
            startActivity(intent);
        });

        generateOrderList();

        orderAdapter = new OrderAdapter(orders, this::onOrderListener);
        recyclerView.setAdapter(orderAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        backListener();
    }

    private void onOrderListener(int i) {
        /*
        todo по клику интент и новая активити в виде ProductActivity с флагом
         */
        Toast.makeText(this, "Пока что не покажу", Toast.LENGTH_SHORT).show();
    }

    void backListener() {
        buttonBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /*
    todo на тебе через бд
     */
    private void generateOrderList() {
        orders = new ArrayList<>();
        orders.add(new Order(300L,
                Timestamp.valueOf("2019-02-14 11:11:11"),
                Timestamp.valueOf("2019-02-14 11:12:11"),
                "1234", null,
                "status", "type", "phone",
                "name", null
        ));
        orders.add(new Order(800L,
                Timestamp.valueOf("2019-03-14 11:11:11"),
                Timestamp.valueOf("2019-03-14 11:12:11"),
                "4321", null,
                "status", "type", "phone",
                "name", null
        ));

    }
}
