package com.example.adapter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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


        ListView listView=findViewById(R.id.listView);//AdapterView
        String[] countries={"USA","Germany","France"};//Data source
        //ArrayAdapter (simplu)
       // ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,countries);//simple_list_item_1 reprezinta un item in care este introdusa o tara

        //BaseAdapter
        MyCustomAdapter adapter=new MyCustomAdapter(this,countries);
        listView.setAdapter(adapter);
    }
}
//Un adapter este o interfata utilizată pentru a gestiona și
//a afișa date într-un mod eficient, în elemente de interfață grafică
//precum liste (ListView), grile (GridView). Daca vreau sa unesc datele
//preluate dintr-un ArrayList, HashMap, SQLite cu un element UI cum ar fi
//ListView, GridView o sa folosesc adapters.
//Adapter actioneaza ca un pod intre data source si adapterView care este
//de exemplu un ListView


/*AdapterView: Este o clasă părinte pentru toate vizualizările (views)
care utilizează adaptoare pentru a lega datele de vizualizările copil.
Exemple comune de AdapterView includ ListView, GridView, Spinner*/

//ArrayAdapter: Utilizat pentru a afișa o listă de date într-un ListView
// sau Spinner. Este cel mai des folosit atunci când avem o sursă de date
// simplă, precum un array sau o listă de stringuri.


/*BaseAdapter: Este o clasă de bază pentru crearea propriilor adaptoare
personalizate. Când ai nevoie de mai mult control asupra modului în care
sunt afișate datele în ListView sau GridView, poți extinde BaseAdapter și
să implementezi metode personalizate.

De exemplu noi ne dorim ca o tara sa apara intr-un chenar mai mare,
cu text rosu. Vom crea un nou layout app->res->layout->new-> Layout resource file (my_item) .
In acel layout vom crea un TextView in felul in care vrem sa arate item-ul nostru.*/