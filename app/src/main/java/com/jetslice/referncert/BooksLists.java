package com.jetslice.referncert;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BooksLists extends AppCompatActivity {
    ArrayList<String> Books_name;
    ImageView class_image1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_lists);
        int clsno = getIntent().getIntExtra("Classno", 1);
        try {
            Toast.makeText(getBaseContext(),""+clsno,Toast.LENGTH_SHORT).show();
            Books_name = getBooksNames(clsno);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < Books_name.size(); i++) {
            Log.e("ff", "Ere   " + Books_name.get(i));
        }
        RecyclerView rView = (RecyclerView) findViewById(R.id.rv_book_list);
//        rView.setHasFixedSize(true);
        rView.setLayoutManager(new GridLayoutManager(this,2));
        BookListAdapter rcAdapter = new BookListAdapter(Books_name,this,clsno);
        rView.setAdapter(rcAdapter);

        class_image1 = (ImageView) findViewById(R.id.class_image);
        switch (clsno){
            case 1:
                class_image1.setImageResource(R.drawable.class1);
                break;
            case 2:
                class_image1.setImageResource(R.drawable.class2);
                break;
            case 3:
                class_image1.setImageResource(R.drawable.class3);
                break;
            case 4:
                class_image1.setImageResource(R.drawable.class4);
                break;
            case 5:
                class_image1.setImageResource(R.drawable.class5);
                break;
            case(6):
                class_image1.setImageResource(R.drawable.class6);
                break;
            case(7):
                class_image1.setImageResource(R.drawable.class7);
                break;
            case(8):
                class_image1.setImageResource(R.drawable.class8);
                break;
            case(9):
                class_image1.setImageResource(R.drawable.class9);
                break;
            case(10):
                class_image1.setImageResource(R.drawable.class10);
                break;
            case(11):
                class_image1.setImageResource(R.drawable.class11);
                break;
            case(12):
                class_image1.setImageResource(R.drawable.class12);
                break;
        }

    }

    private ArrayList<String> getBooksNames(int clsno) throws IOException {
        InputStream is;
        boolean flag;
        is = getResources().openRawResource(R.raw.expmp);
        ArrayList<String> classBooks = new ArrayList<>();
        flag = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            String arr[] = line.split(",");
            if (arr[0].equalsIgnoreCase(String.valueOf(clsno))) {
                classBooks.add(arr[1]);
                flag = true;
            }
        }
        removeDup(classBooks);
        return classBooks;
    }
    private ArrayList<String> removeDup(ArrayList<String> al) {
        Set<String> hs = new HashSet<>();
        hs.addAll(al);
        al.clear();
        al.addAll(hs);
        return al;
    }
}