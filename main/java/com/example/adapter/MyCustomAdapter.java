package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyCustomAdapter extends BaseAdapter {

    private Context context;
    /*Context este o clasă fundamentală în Android care reprezintă
    informații despre starea actuală a aplicației sau activității.
    Este un obiect care oferă acces la resurse și funcționalități de
    bază ale aplicației, cum ar fi accesul la baze de date, la
    resursele de fișiere.*/
    private String[] items;

    public MyCustomAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() //Returnează numărul de elemente din sursa de date.
    {
        return items.length;
    }

    @Override
    public Object getItem(int i) //Returnează obiectul din sursa de date de la poziția specificată.
    {
        return items[i];
    }

    @Override
    public long getItemId(int i) //Returnează ID-ul unic pentru obiectul de la poziția specificată. (i=position)
    {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent)
    {
        ViewHolder holder;//instanta ViewHolder
        // Dacă nu există o vizualizare reutilizabilă, inflați o nouă vizualizare
        //"inflating" se referă la procesul de transformare a unui fișier XML de layout într-o instanță de View
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.my_item,parent,false);
            /*LayoutInflater este o clasă Android utilizată pentru a crea o vizualizare (View) dintr-un fișier XML de layout.*/
            /*LayoutInflater.from(context) creează o instanță de LayoutInflater folosind contextul (de obicei, activitatea sau fragmentul în care se află lista). context este transmis constructorului adapterului, deci se obține LayoutInflater specific contextului aplicației.*/
            /*R.layout.my_item: ID-ul resursei XML de layout care trebuie inflațată.*/
            /*parent: Un obiect ViewGroup care reprezintă părintele viitoarei vizualizări inflațate. În metoda getView() a unui Adapter, parent este de obicei ListView sau RecyclerView care conține toate elementele listei.*/
            /*false: Un boolean care specifică dacă vizualizarea inflațată ar trebui să fie atașată direct la parent (ViewGroup-ul). */
            //Rezultatul este o nouă instanță a layout-ului R.layout.my_item, care este asignată lui convertView.

            // Creați și salvați un ViewHolder pentru vizualizare
            holder=new ViewHolder();
            holder.textview = convertView.findViewById(R.id.item);
            convertView.setTag(holder); // Salvați ViewHolder-ul în convertView
        }
        else {
            holder=(ViewHolder) convertView.getTag();
            /*În cazul în care convertView nu este null,
            adică vizualizarea există deja și poate fi reutilizată,
            se folosește convertView.getTag() pentru a recupera un
            obiect care a fost salvat anterior în convertView.
            Acest obiect este de obicei un ViewHolder care conține
            referințele la componentele UI (de exemplu, TextView, ImageView etc.)
            ale vizualizării respective.*/
        }

        //setam datele la view
        holder.textview.setText(items[i]);
        return convertView;
        //În metoda getView(), convertView este obiectul View care
        // reprezintă un singur element de listă. Fiecare apel al
        // metodei getView() este responsabil pentru crearea sau
        // reciclarea unei vizualizări pentru un element din listă
        // la o anumită poziție (position).
    }
    //Cea mai importantă metodă, folosita pentru a crea și returna
    // vizualizarea (view) corespunzătoare
    // datelor de la poziția specificată. Această metodă gestionează
    // reutilizarea vizualizărilor pentru performanță (mai ales în cazul
    // RecyclerView).Scopul său principal este de a furniza o vizualizare
    // pentru fiecare element din setul de date.

    //View convertView: Este vizualizarea reutilizabilă veche.
    // Când o vizualizare existentă este disponibilă, aceasta
    // este transmisă ca convertView pentru a fi reutilizată.
    // În acest fel, Android nu trebuie să creeze o nouă vizualizare
    // de fiecare dată, economisind astfel resurse și îmbunătățind
    // performanța.Cand dam scroll, view-urile care dispar de pe ecran
    //se duc in convertView si de aici sunr refolosite pentru view-urile
    //care apar nou pe ecran

    static class ViewHolder
    {
        TextView textview;// Referință la TextView din elementul de listă
        //daca aveam 2 textviews trebuia sa am 2 declaratii
    }
    /*Un ViewHolder este o clasă statică utilizată în interiorul unui adapter
    personalizat (cum ar fi unul care extinde BaseAdapter sau RecyclerView.Adapter)
    pentru a reține referințele la vizualizările unui element de listă. Prin
    utilizarea unui ViewHolder, evităm necesitatea de a apela findViewById()
    de fiecare dată când un element este afișat (in situatia de fata avem 3 tari,
    deci este apelata de 3 ori), ceea ce poate fi costisitor din
    punct de vedere al performanței.

    Într-un ListView sau RecyclerView, elementele vizualizate trebuie să fie create
    și afișate pe ecran pe măsură ce utilizatorul derulează. Fără un ViewHolder, de
    fiecare dată când un element apare pe ecran, metoda findViewById() ar fi apelată
    pentru a găsi și lega fiecare componentă UI, ceea ce este foarte ineficient și
    consumă multe resurse. ViewHolder rezolvă această problemă prin stocarea referințelor
    la componentele UI ale unui element și reutilizarea lor.*/
}
