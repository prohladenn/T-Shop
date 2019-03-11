package com.example.tshop.t_shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.sql.Timestamp;
import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity {

    ArrayList<Order> orders = new ArrayList<>();
    OrderAdapter orderAdapter;
    RecyclerView recyclerView;
    ImageView buttonBack;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
    private FirebaseUser currntUser;
    TextView problemOne;
    TextView problemTwo;
    TextView problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        mAuth = FirebaseAuth.getInstance();
        currntUser = mAuth.getCurrentUser();
        mFirestore = FirebaseFirestore.getInstance();
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
        todo inventoryRef с тебя, а флвг готов
         */
        Toast.makeText(this, "Пока что не покажу", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OrdersActivity.this, ProductsActivity.class);
        intent.putExtra("refPath", orders.get(i).getBasketRef().getPath());
        intent.putExtra("order", true);
        startActivity(intent);
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
        mFirestore.collection("orders").addSnapshotListener((queryDocumentSnapshots, e) -> {
            if (e != null) {
                return;
            }
            for (QueryDocumentSnapshot docItem : queryDocumentSnapshots) {
                if(docItem.getDocumentReference("customer").equals(mFirestore.collection("users").document(currntUser.getUid()))){
                    Task<DocumentSnapshot> task = docItem.getDocumentReference("basket").get();
                    while (!task.isComplete()) {
                        Log.d("LOGI", "WaitOwen");
                    }
                    DocumentSnapshot doc = task.getResult();
                    orders.add(new Order(doc.getLong("totalPrice"),
                            docItem.getTimestamp("dateCreation"),
                            docItem.getLong("number"),
                            docItem.getDocumentReference("basket"),
                            docItem.getString("status"),
                            docItem.getDocumentReference("customer"),
                            docItem.getDocumentReference("shop")

                    ));
                }


            }
            orderAdapter = new OrderAdapter(orders, this::onOrderListener);
            recyclerView.setAdapter(orderAdapter);
        });

    }
}