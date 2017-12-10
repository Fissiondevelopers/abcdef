package com.jetslice.referncert;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;


public class AllClassesAdapter extends RecyclerView.Adapter<AllClassesAdapter.RecyclerViewHolders> {

    private List<String> itemList;
    private Context context;

    public AllClassesAdapter(Context context, List<String> itemList) {
        this.itemList = itemList;
        this.context = context;
    }


    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.class_number_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders recyclerViewHolders, int i) {
        final int num = i + 1;
//        recyclerViewHolders.countryName.setText("Class " + num);

//        recyclerViewHolders.countryName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(context, BooksLists.class);
//                i.putExtra("Classno", num);
//                context.startActivity(i);
//            }
//        });
//        switch (num){
//            case 1:recyclerViewHolders.imgb.setImageResource(R.drawable.class1);break;
//            case 2:recyclerViewHolders.imgb.setImageResource(R.drawable.class2);break;
//            case 3:recyclerViewHolders.imgb.setImageResource(R.drawable.class3);break;
//            case 4:recyclerViewHolders.imgb.setImageResource(R.drawable.class4);break;
//            case 5:recyclerViewHolders.imgb.setImageResource(R.drawable.class5);break;
//            case 6:recyclerViewHolders.imgb.setImageResource(R.drawable.class6);break;
//            case 7:recyclerViewHolders.imgb.setImageResource(R.drawable.class7);break;
//            case 8:recyclerViewHolders.imgb.setImageResource(R.drawable.class8);break;
//            case 9:recyclerViewHolders.imgb.setImageResource(R.drawable.class9);break;
//            case 10:recyclerViewHolders.imgb.setImageResource(R.drawable.class10);break;
//            case 11:recyclerViewHolders.imgb.setImageResource(R.drawable.class11);break;
//            case 12:recyclerViewHolders.imgb.setImageResource(R.drawable.class12);break;
//        }

        recyclerViewHolders.imgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, BooksLists.class);
                i.putExtra("Classno",  num);
                context.startActivity(i);
            }
        });
    }



    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder  {

        Button countryName;
        ImageButton imgb;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
//            countryName = itemView.findViewById(R.id.classname);
            imgb = itemView.findViewById(R.id.classpic);
        }
    }
}