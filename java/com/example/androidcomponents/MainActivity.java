package com.example.androidcomponents;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.color.utilities.ColorUtils;

public class MainActivity extends AppCompatActivity {

    Button play;
    Button stop;
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

        play=findViewById(R.id.play);
        stop=findViewById(R.id.stop);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(), CustomService.class);
                startService(in);//start a unnounded service

            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(getApplicationContext(), CustomService.class);
                stopService(in);//start a unnounded service

            }
        });
        /*Pentru ca aplicatia sa functioneze nu uita sa declari serviciul in manifest.xml
        *         <activity
          ...
        </activity>
        <service android:name=".CustomService"/>
    </application>*/

        //BROADCAST RECEIVER (specificat dinamic) (static este in manifect)
        IntentFilter inf=new IntentFilter("android.intent.action.AIRPLANE_MODE");
        AirPlaneReceiver br=new AirPlaneReceiver();
        registerReceiver(br,inf);

    }
}

//activity - single screen with a user interface

//Services - sunt componente Android care rulează în fundal pentru
// a efectua operațiuni pe termen lung, fără a interacționa direct
// cu utilizatorul. Ele sunt folosite atunci când aplicația trebuie
// să execute anumite sarcini pe o perioadă extinsă de timp, cum ar
// fi descărcarea fișierelor, redarea muzicii, efectuarea sincronizărilor
// sau monitorizarea locației, chiar și atunci când aplicația nu este
// vizibilă. . Aplicația nu trebuie să fie activă pentru ca serviciul să continue să ruleze.
    //TIPURI DE SERVICES:
/*. Foreground Service (Serviciu de prim-plan)
Acesta este un serviciu care este vizibil utilizatorului deoarece afișează o notificare persistentă în bara de notificări.
 (redarea muzicii, monitorizarea GPS-ului)
 Exemplu: O aplicație de muzică care rulează în fundal și continuă să redea muzică chiar și atunci când utilizatorul o minimizează.*/

/*Background Service (Serviciu de fundal)
Acesta este un serviciu care rulează în fundal, fără a fi vizibil utilizatorului și fără o interfață de utilizator.
Exemplu: O aplicație de e-mail care sincronizează periodic mesajele noi fără ca utilizatorul să fie informat imediat.*/

/*Bound Service (Serviciu legat)
Acest tip de serviciu permite unei componente (activitate, fragment sau alt serviciu) să se lege la serviciu și să interacționeze cu el direct.
Utilizare: Atunci când ai nevoie de o interacțiune continuă și controlată între activitate și serviciu, cum ar fi gestionarea sesiunilor de chat.*/
/*Când o activitate se leagă la acest serviciu, ea poate interacționa direct cu el pentru a cere informații
sau pentru a declanșa anumite acțiuni, cum ar fi obținerea de date actualizate sau realizarea unor operațiuni
complexe. Când activitatea se închide sau decide să nu mai aibă nevoie de serviciu, legătura se închide, iar
serviciul poate fi oprit.
Exemplu real: Aplicație de redare muzică
Scenariu:Ai o aplicație care redă muzică. Vrei ca activitatea principală a aplicației să poată controla
redarea muzicii (pornire, pauză, următoarea piesă) și să primească informații actualizate despre starea
player-ului (cum ar fi timpul curent din melodie).*/

/*Exista si  Unbounded Service Acesta este un serviciu care rulează în fundal pentru a efectua o sarcină
specifică, fără a permite altor componente să se lege la el. Odată pornit, serviciul își continuă execuția
fără interacțiune directă.Rămâne activ până când fie se termină singur.
EX: : O aplicație care descarcă un fișier în fundal.
*/

/*un bounded service
* startService() se apelează pentru a porni un serviciu. Nu este parte din serviciu, ci este apelată din activitate sau altă componentă.
* onCreate() este apelată o singură dată când serviciul este creat. Este locul unde poți inițializa resursele necesare pentru serviciu
* onStartCommand() metoda principală care este apelată atunci când un client (de obicei, o activitate) pornește serviciul folosind startService() (oți specifica ce acțiuni să efectueze serviciul)
* SERVICE RUNNING
* SERVICE STOPPED
* onDestroy() Aceasta este apelată când serviciul este oprit, fie de către sistem, fie prin apelarea metodei stopSelf() sau stopService()*/

/*bounded service
 * bindService() este apelată pentru a lega o activitate sau o componentă la un serviciu.
 * onCreate() este apelată o singură dată când serviciul este creat. Este locul unde poți inițializa resursele necesare pentru serviciu
 * onBind() Această metodă este apelată când un client (de exemplu, o activitate) încearcă să se lege la serviciu.
 * CLIENT INTERACT WITH THE SERVICE
 * ALL CLIEMTS UNBIND
 * onUnbind()  este apelată atunci când toate legăturile active cu serviciul au fost întrerupte.
 * onRebind() Această metodă este apelată când un client (o activitate sau altă componentă) se leagă din nou la un serviciu care a fost deja dezlegat. Este folosită pentru a gestiona situațiile în care legătura este reestabilită.
 * onDestroy() Aceasta este apelată când serviciul este oprit, fie de către sistem, fie prin apelarea metodei stopSelf() sau stopService()*/

//Broadcast Receiver - în Android este un component special care permite unei
// aplicații să recepționeze și să răspundă la evenimentele sistemului sau aplicațiilor,
// numite broadcasts. Aceste evenimente sunt trimise de sistemul Android sau alte aplicații
// sub forma de mesaje globale care pot fi interceptate de orice componentă care a
// înregistrat interes pentru ele.
//
//Scopul unui Broadcast Receiver:
//Un Broadcast Receiver monitorizează mesajele transmise de sistem (sau de alte aplicații)
// și execută cod atunci când detectează un broadcast care coincide cu intențiile specificate (înregistrate).
// Exemple de evenimente care pot fi capturate sunt:
//
//Pornirea sau oprirea telefonului.
//Starea bateriei (cum ar fi atunci când bateria este descărcată).
//Conexiunea la rețea (Wi-Fi conectat/deconectat).
//Recepționarea unui SMS.
//
//ex:O aplicație poate recepționa broadcast-uri despre starea
// bateriei pentru a ajusta comportamentul în funcție de nivelul bateriei.
/*Statice: Sunt declarate în fișierul AndroidManifest.xml și sunt active chiar dacă aplicația nu rulează. Acestea sunt utilizate de obicei pentru evenimente de sistem, cum ar fi pornirea dispozitivului sau schimbarea conectivității.
Dinamice: Sunt înregistrate în codul aplicației, la runtime. Acestea sunt utile pentru evenimente care se întâmplă în timpul în care aplicația este activă, cum ar fi monitorizarea notificărilor.*/

//Conent Provider - în Android este o componentă care gestionează accesul la un
// set structurat de date. Acesta oferă o interfață standardizată care permite
// aplicațiilor să stocheze și să partajeze date cu alte aplicații în mod
// securizat și controlat. Ex: Partajarea datelor între aplicații: De exemplu,
// dacă aplicația ta dorește să acceseze contactele stocate pe dispozitiv, va
// folosi un Content Provider pentru a citi datele din lista de contacte.