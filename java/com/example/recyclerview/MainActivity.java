package com.example.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements itemClickListener{

    List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        itemList=new ArrayList<>() ;
        Item item1=new Item(R.drawable.fruit,"Fruits","Fresh fruits");
        Item item2=new Item(R.drawable.vegitables,"Vegetables","Fresh vegetables");
        Item item3=new Item(R.drawable.bread,"Bakery","Bread");
        Item item4=new Item(R.drawable.beverage,"Beverage","Juice, Tea, Coffee and Soda");
        Item item5=new Item(R.drawable.milk,"Milk","Milk, Shake and Yogurt");
        Item item6=new Item(R.drawable.popcorn,"Snacks","Pop Corn, Donut and Drinks");

        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);
        itemList.add(item5);
        itemList.add(item6);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MyCustomAdaptor adaptor=new MyCustomAdaptor(itemList);
        recyclerView.setAdapter(adaptor);

        adaptor.setClickListener(this);//adaptorul știe că MainActivity va gestiona evenimentele de click.

        
    }

    @Override
    public void onClick(View v, int i) {
        Toast.makeText(this,"You choose: "+itemList.get(i).getName(),Toast.LENGTH_SHORT).show();
    }
}

/*Recycler view
*widget folosit pentru a afișa o listă mare de elemente într-un mod eficient
* fiind proiectat pentru a optimiza utilizarea memoriei și a gestiona în mod
* eficient afișarea unui număr mare de elemente, fie ele de același tip sau de tipuri diferite .
* Când un element iese din ecran, RecyclerView îl reutilizează pentru a afișa un nou element, în loc să creeze unul nou de la zero.

 Spre deosebire de ListView, care afișează doar o listă verticală de elemente,
 * RecyclerView necesită un LayoutManager pentru a aranja elementele. LayoutManager
 * determină modul în care elementele sunt afișate:

1.LinearLayoutManager - afișează elementele într-o listă verticală sau orizontală.
2.GridLayoutManager - afișează elementele într-o grilă.
3.StaggeredGridLayoutManager - afișează elementele într-o grilă cu înălțimi sau lățimi variabile.
*
* ex: email: cand dam scroll in jos, primul email(item) de sus o sa fie "reciclat" adica pus intr-o coada si
* va fi folosit pentru a afisa item-urile care urmeaza sa apara pe ecran*/