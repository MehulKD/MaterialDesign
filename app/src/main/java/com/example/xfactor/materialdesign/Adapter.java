package com.example.xfactor.materialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sarthak on 02-07-2015.
 */                                               //classname.MyViewHolder
public class Adapter extends RecyclerView.Adapter<Adapter.Itemholder> {
    List<SingleRow> data = Collections.emptyList();
//    ArrayList<header> heads = null;
    LayoutInflater inflater;
    String username;
    String email;
    private static final int HEADER = 0;
    private static final int ITEM = 1;
    //List<SingleRow> refers to list of objects of SingleRow

    public Adapter(Context context, List<SingleRow> data , String username, String email) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.username = username;
        Log.d("User" , "" + this.username);
        this.email = email;
        Log.d("email" , "" + this.email);

    }

//    public Adapter(String username, String email) {
//
//
//    }

//    public Adapter(ArrayList<header> heads) {
//        this.heads = heads;
//    }


    @Override
    public Itemholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Whenever new row is to be displayed this fn is called
        if (i == HEADER) {
            Log.d("Tag", "i");
            View view = inflater.inflate(R.layout.circular, viewGroup, false);
            Itemholder vHeader = new Itemholder(view, i);
            return vHeader;
        } else {
            View view = inflater.inflate(R.layout.single_row, viewGroup, false);
            Itemholder vItem = new Itemholder(view, i);
            return vItem;
        }
    }

    @Override
    public void onBindViewHolder(Itemholder viewHolder, int position) {
        //used to set data to be displayed int the recycler view and update it as per the position (i)
        Log.d("Tag", "Header2");

        if (viewHolder.Holderid == 0) {
            Log.d("Tag", "Header");
//            header he = heads.get(position);
//            Log.d("Tag", "Header45");
//            viewHolder.email.setText(he.email);
//            viewHolder.username.setText(he.username2);
            viewHolder.email.setText(this.email);
            Log.d("email",""+ this.email);
            viewHolder.username.setText(this.username);

        } else if (viewHolder.Holderid == 1) {
            Log.d("Tag", "else");
            SingleRow current = data.get(position - 1);
            viewHolder.textView.setText(current.text);
            viewHolder.icon.setImageResource(current.Iconid);
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else {
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        //+1 for header
        return data.size() + 1;

    }

    class Itemholder extends RecyclerView.ViewHolder
            //This class needs to be a sub class of the Adapter class
    {
        TextView textView;
        ImageView icon;
        TextView email;
        TextView username;
        int Holderid;


        public Itemholder(View itemView, int Viewtype) {
            super(itemView);
            if (Viewtype == ITEM) {
                textView = (TextView) itemView.findViewById(R.id.listtext);
                icon = (ImageView) itemView.findViewById(R.id.image);
                Holderid = 1;
            } else {
                username = (TextView) itemView.findViewById(R.id.text);
                email = (TextView) itemView.findViewById(R.id.text2);
                Holderid = 0;

            }


        }
    }


}
