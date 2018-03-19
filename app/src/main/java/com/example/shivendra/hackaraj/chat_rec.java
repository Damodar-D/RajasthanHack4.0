package com.example.shivendra.hackaraj;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Subham on 17-03-2018.
 */

public class chat_rec extends RecyclerView.ViewHolder
{
    public TextView leftText;
    public TextView rightText;
    public chat_rec(View itemView) {
        super(itemView);
        leftText = (TextView)itemView.findViewById(R.id.leftText);
        rightText = (TextView)itemView.findViewById(R.id.rightText);
    }
}
