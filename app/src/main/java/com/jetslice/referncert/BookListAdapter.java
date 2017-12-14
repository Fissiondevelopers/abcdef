package com.jetslice.referncert;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by shubham on 6/9/17.
 */

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.RecyclerViewHoldersx> {
    private ArrayList<String> itemList;
    Context context;
    int clsno;

    public BookListAdapter(ArrayList<String> itemList, Context context,int clsno) {
        this.itemList = itemList;
        this.context = context;
        this.clsno=clsno;
    }

    @Override
    public BookListAdapter.RecyclerViewHoldersx onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_itemx, parent,false);
        Animation slideUpAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_up_animation);
        layoutView.startAnimation(slideUpAnimation);
        RecyclerViewHoldersx rcv = new RecyclerViewHoldersx(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(final BookListAdapter.RecyclerViewHoldersx holder, int position) {
        int chaps = 0;
        try {
            chaps=getBookChapters(itemList.get(position),clsno).size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Typeface type = Typeface.createFromAsset(context.getAssets(),"fonts/Lobster_1.3.otf");
        holder.bookname.setText("" + itemList.get(position));
        holder.bookname.setTypeface(type);
        holder.chapnos.setText("Included Chapters: " + chaps);

        holder.cvbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chaptername = holder.bookname.getText().toString();
                Intent i = new Intent(context, ChapterList.class);
                i.putExtra("Bookname", chaptername);
                i.putExtra("Classno", clsno);
                context.startActivity(i);
                Toast.makeText(context, "" + chaptername, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    public class RecyclerViewHoldersx extends RecyclerView.ViewHolder {
        TextView bookname,chapnos;

//        Button bookopen;
//        ImageButton bookimage;
//        LinearLayout ll;
        CardView cvbook;

        public RecyclerViewHoldersx(View itemView) {
            super(itemView);
            bookname = itemView.findViewById(R.id.tvbookx);
            chapnos = itemView.findViewById(R.id.tvchapnos);

//            bookopen = itemView.findViewById(R.id.open_book);
//            bookimage = itemView.findViewById(R.id.book_pic);
//            ll=itemView.findViewById(R.id.bookll);
            cvbook=itemView.findViewById(R.id.book_cardx);
        }
    }
    private ArrayList<String> getBookChapters(String Book, int clsno) throws IOException {
        ArrayList<String> BookChapters=new ArrayList<>();
        InputStream is;
        boolean flag ;
        is = context.getResources().openRawResource(R.raw.expmp);
        flag = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null){
            String arr[] = line.split(",");
            if ((arr[1].equals(Book)) && (Integer.parseInt(arr[0])==clsno)){
                BookChapters.add(arr[2]);
                flag=true;
            }
        }
        return BookChapters;
    }
}