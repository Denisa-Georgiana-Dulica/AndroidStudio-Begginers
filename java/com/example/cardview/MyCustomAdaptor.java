package com.example.cardview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyCustomAdaptor extends RecyclerView.Adapter<MyCustomAdaptor.ViewHolder> //pod intre lista de retete si recyclerView-ul sub forma de cardView
{
    //prima data creeaza viewHolderul si dupa aceea extindem RecyclerView.Adapter<MyCustomAdaptor.ViewHolder
    private List<Recipe> lista;
    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener myClickListener)
    {
        this.clickListener=myClickListener;
    }

    public MyCustomAdaptor(List<Recipe> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       //creez un view pentru fiecare item (obiect din lista) pe tot ecranul
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false);//parent.getContext() parent este recyclerView-ul care este sub forma de cartview
        ViewHolder v=new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe r=lista.get(position);
        holder.img.setImageResource(r.getImage());
        holder.title.setText(r.getName());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent in=new Intent(Intent.ACTION_SEND);
              in.setType("text/plain");
              in.putExtra(Intent.EXTRA_SUBJECT,"VEZI RETETA !"); // Subiectul partajării(titlul email)
              in.putExtra(Intent.EXTRA_TEXT,"Link: "+r.getUrl()); // Textul partajat (ce pui in email)
                in.putExtra(Intent.EXTRA_TITLE,""+r.getName()); //apare cand alegi aplicatia deasupra linkului
                Context c=view.getContext();
                c.startActivity(Intent.createChooser(in,"Share recipe via"));
            }
        });
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView img;
        TextView title;
        Button btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imagine);
            title=itemView.findViewById(R.id.denumire);
            btn=itemView.findViewById(R.id.button);

            itemView.setOnClickListener(this);//se apeleaza onClick de mai jos
        }

        @Override
        public void onClick(View view) {
            if(clickListener!=null)
            {
                clickListener.onClick(view,getAdapterPosition());
            }
        }
    }




}
