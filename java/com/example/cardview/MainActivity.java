package com.example.cardview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener {
/*task: sa intru pe un link
* O imagine a preparatului.
Numele rețetei.
O scurtă descriere (timp de preparare, nivel de dificultate etc.
* o steluta si cand apasa pe ea sa apara la favorite acele retete
* */

    private  RecyclerView r;
    private List<Recipe> list;
    private MyCustomAdaptor adaptor;
    private Button btn;
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

       r=findViewById(R.id.recyclerView);
        list=new ArrayList<>();
        String url1="https://jamilacuisine.ro/dulceata-de-afine-dulceata-preferata-a-papanasilor-reteta-video/";
        Recipe r1=new Recipe("Papanasi",R.drawable.papanasi,Uri.parse(url1));
        String url2="https://jamilacuisine.ro/clatite-pufoase-fara-cantar-doar-3-pahare-reteta-video/";
        String url3="https://jamilacuisine.ro/briose-pufoase-cu-ciocolata-facute-de-sarah-mea-reteta-video/";
        Recipe r2=new Recipe("Briose cu ciocolata",R.drawable.briose_ciocolata,Uri.parse(url3));
        Recipe r3=new Recipe("Clatite",R.drawable.clatite,Uri.parse(url2));
        String url5="https://jamilacuisine.ro/tort-de-bezea-cu-ciocolata-reteta-video/";
        Recipe r4=new Recipe("Tort de bezea",R.drawable.tort_bezea,Uri.parse(url5));
        String url4="https://jamilacuisine.ro/tort-diplomat-facut-in-casa-reteta-video/#google_vignette";
        Recipe r5=new Recipe("Tort diplomat",R.drawable.diplomat,Uri.parse(url4));

        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        list.add(r5);

    adaptor=new MyCustomAdaptor(list);
    r.setLayoutManager(new LinearLayoutManager(this));//this - unde e pus recycler view-ul
    r.setAdapter(adaptor);
    adaptor.setClickListener(this);

    }

    @Override
    public void onClick(View v, int i) {
        Intent in=new Intent(Intent.ACTION_VIEW);
        Recipe r=list.get(i);
        if(r.getName().equals("Papanasi")) {
            String url="https://jamilacuisine.ro/dulceata-de-afine-dulceata-preferata-a-papanasilor-reteta-video/";
            in.setData(Uri.parse(url));
            startActivity(in);
        }


    }


}

//CardView este un container care permite afișarea de conținut într-un format de card. (sectiunea de stiri de pe samsung-google)
//  card_view:cardCornerRadius="8dp"
//   card_view:cardElevation="4dp"> //umbre
//folosit in ListView sau in RecyclerView (cea mai buna optiune)

//1.The CardView item layout - xml
//2.RecyclerView - xml
//3.Model class pentru obiectele pe care le prezentam
//4.Adapter class in care tin toate metodele care au treaba cu RecyclerView (CREATE, BINDING, COUNT OF ITEMS)
//5.ViewHolder