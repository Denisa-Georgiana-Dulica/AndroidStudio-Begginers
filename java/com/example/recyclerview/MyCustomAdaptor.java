package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyCustomAdaptor extends RecyclerView.Adapter<MyCustomAdaptor.ViewHolder>
{
    private List<Item> itemList;
    public itemClickListener clickListener;//interfata

    public void setClickListener(itemClickListener myListener)
    {
        this.clickListener=myListener;
    }
    //setClickListener O FOLOSESC IN MAIN PENTRU adaptor.setClickListener(this);
    //leg adaptorul de main
    /*MainActivity implementează interfața itemClickListener, ceea ce înseamnă că ea furnizează o implementare pentru metoda onClick(View v, int i).
În MainActivity, adaptor.setClickListener(this); spune adaptorului: "Uite, MainActivity implementează itemClickListener. Când se întâmplă un
click pe un element din RecyclerView, apelează metoda onClick definită în MainActivity."
Practic, this aici reprezintă instanța curentă a MainActivity, care implementează interfața itemClickListener.*/

    public MyCustomAdaptor(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolder v=new ViewHolder(item);
        return v;
    }
    /*Scop: Această metodă este apelată de RecyclerView atunci când are nevoie să creeze un nou ViewHolder (pentru a umple ecranul).
Rol: Aici trebuie să se "inflațeze" (creeze) layout-ul XML pentru fiecare element din listă. Layout-ul este
transformat într-un obiect View care poate fi folosit pentru a afișa datele.
Ce trebuie să returneze: Un nou obiect ViewHolder care conține elementul de vizualizare (inflatat din XML).*/

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item=itemList.get(position);
        holder.img.setImageResource(item.image);
        holder.name.setText(item.name);
        holder.description.setText(item.description);
    }
    /*Scop: Această metodă este apelată de RecyclerView pentru a lega datele la ViewHolder.
Rol: Această metodă este responsabilă pentru a pune datele din itemList în elementul de vizualizare
(ViewHolder). position reprezintă poziția elementului în listă.
Ce trebuie să facă: Folosește holder pentru a accesa și actualiza elementele de vizualizare de exemplu
atunci cand vrem sa refolosim un view, elementele din vechiul holder
o sa le actualizam cu cele noi (de exemplu, setarea textului unui TextView).*/

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    /*Scop: Această metodă returnează numărul total de elemente din setul de date.
        Rol: RecyclerView folosește această valoare pentru a ști câte elemente trebuie afișate.
    Ce trebuie să returneze: Dimensiunea listei de date (itemList.size()).*/


    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView img;
        TextView name;
        TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imageView);
            name=itemView.findViewById(R.id.textView);
            description=itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(this);//inseamnă că atunci când itemView (adică întregul element din listă) este apăsat, View.OnClickListener va fi apelat.
                                            //this se referă la instanța curentă a clasei în care ne aflăm, adică ViewHolder

            //Fiecare element din RecyclerView este reprezentat
            // de un ViewHolder. În momentul în care un element
            // este creat, i se setează un OnClickListener în
            // constructorul ViewHolder care este necesar pentru a răspunde la click-uri..
        }

        @Override
        public void onClick(View view) {//se declanseaza dupa ce utilizatorul da click
            if(clickListener!=null)
            {
                clickListener.onClick(view,getAdapterPosition());//spun MainActivity ca s a facut click
            }
        }

        /*Când onClick din ViewHolder este apelată:
clickListener: Acesta este obiectul setat anterior în MainActivity prin setClickListener. În cazul nostru, clickListener este de fapt MainActivity.
getAdapterPosition(): Aceasta returnează poziția elementului pe care s-a făcut click în listă.
Apelarea clickListener.onClick:

Metoda clickListener.onClick(view, getAdapterPosition());:
Aceasta apelează metoda onClick a obiectului clickListener.
În cazul nostru, clickListener este MainActivity. Așadar, metoda onClick definită în MainActivity va fi apelată.
Se transmit:
view: View-ul pe care s-a făcut click (în cazul nostru, itemView).
getAdapterPosition(): Poziția elementului în listă.*/

        /*Rolul fiecărui element
itemClickListener (interfața):
Definește un contract pentru evenimentele de click.
Permite MainActivity să preia evenimentele de click.

setClickListener (în adaptor):
Leagă MainActivity de adaptor pentru a primi notificări de click.

onClick (în ViewHolder):
Gestionează evenimentul de click pentru fiecare element individual din listă.
Apelează metoda onClick a clickListener.

onClick (în MainActivity):
Primește notificarea de click și gestionează logica necesară (în acest caz, afișează un Toast).*/
    }
}
