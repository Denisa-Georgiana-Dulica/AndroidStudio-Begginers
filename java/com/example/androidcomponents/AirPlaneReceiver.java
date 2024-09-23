package com.example.androidcomponents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirPlaneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {//when the broadcast receiver receives a broadcast(msg)
        if(intent.getAction()!=null && intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        //Verifică dacă broadcast-ul interceptat este specific pentru schimbarea stării modului avion. Dacă ambele condiții sunt adevărate, codul din interiorul if-ului se va executa.
        {
            Boolean isAirPlaneModeOn=intent.getBooleanExtra("state",false);//caută în msg primit un extra (atribut) cu cheia "state", care conține starea modului avion.Dacă "state" nu este prezent în intent, valoarea implicită va fi false.
            String msg=isAirPlaneModeOn ? "AirplaneMode ON" :"AirplaneMode OFF";
            Toast.makeText(context,""+msg,Toast.LENGTH_SHORT).show();
        }
// Intentul conține informațiile despre broadcast-ul primit. În acest caz, conține datele despre schimbarea stării modului avion.
    }
}
