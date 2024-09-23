package com.example.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    Button btnF;
    Button btnS;

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

        btnF=findViewById(R.id.button1);
        btnS=findViewById(R.id.button2);

        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new FirstFragment());
            }
        });

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new SecondFragment());
            }
        });
    }

    public void openFragment(Fragment f)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frameLayout,f);
        ft.commit();
    }
}
//Fragments sunt componente UI reutilizabile
//comportament sau o porțiune a interfeței utilizatorului dintr-o activitate, și poate fi gândit ca un mini-"Activity" în cadrul unei activități mai mari (Activity)
//Un fragment este asociat cu o activitate și nu poate exista independent de aceasta
//pe un telefon cu ecran mic, ai putea folosi o singură activitate cu un singur fragment afișat la un moment dat
//pe o tabletă ai putea afișa două sau mai multe fragmente simultan.
//FragmentManager este folosit pentru a gestiona fragmentele dintr-o activitate, inclusiv adăugarea, înlocuirea sau eliminarea acestora.
//FragmentTransaction este folosit pentru a efectua tranzacții cu fragmente, cum ar fi adăugarea sau înlocuirea lor în interiorul unui container definit în layout-ul activității.
//EXEMPLE:

/*Aplicația YouTube:
Fragment 1: Lista cu videoclipuri recomandate.
Fragment 2: Player-ul video, care apare când selectezi un videoclip.*/

/*Aplicațiile de email (Gmail, Outlook):
Fragment 1: Inbox-ul cu lista de emailuri.
Fragment 2: Detaliile emailului selectat, care se deschide în partea dreaptă sau într-un alt ecran (în funcție de mărimea dispozitivului).*/

//LIFE CICLE
//onAttach() - Fragmentul este atașat la activitatea care îl conține. (primeste o referinta la activitatea care il tine ca sa poata sa interactioneze cu ea)
//onCreate() - Fragmentul este creat. Aici inițializezi componente care nu sunt legate de interfața utilizatorului (UI).
//onCreateView() - Este creată și returnată interfața grafică (UI) a fragmentului. Aici fragmentezi și setezi layout-ul pentru fragment.
//onViewCreated() -  Aceasta este apelată după ce onCreateView() a finalizat, indicând faptul că UI-ul a fost creat.
//onActivityCreated() - Această metodă este apelată după ce activitatea gazdă a fost creată și toate layout-urile din activitate sunt pregătite.
//onStart() - Fragmentul devine vizibil pentru utilizator.
//onResume() - Fragmentul devine activ și poate începe să primească input de la utilizator (în primul plan).
//onPause() - Fragmentul este întrerupt și își pierde interactivitatea (de exemplu, dacă utilizatorul deschide altă activitate).
//onStop() - Fragmentul nu mai este vizibil.
//onDestroyView() -  UI-ul asociat fragmentului este distrus, dar fragmentul în sine încă există.
//onDestroy() - Fragmentul este complet distrus.
//onDetach() -  Fragmentul este detașat complet de activitatea sa gazdă.