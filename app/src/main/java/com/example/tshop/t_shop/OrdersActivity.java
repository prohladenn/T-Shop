package com.example.tshop.t_shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    ArrayList<Order> orders;
    OrderAdapter orderAdapter;
    RecyclerView recyclerView;
    ImageView buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        buttonBack = findViewById(R.id.orders_button_back);
        recyclerView = findViewById(R.id.orders_recycler_view);

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
