package com.uas.ksp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoryAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final String[] balance;
    private final String[] status;
    private final int[] imgid;
    private final int[] color;
    public HistoryAdapter(Activity context, String[] maintitle, String[] subtitle, String[] status, String[] balance, int[] imgid, int[] color) {
        super(context, R.layout.list_item, maintitle);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.balance = balance;
        this.status = status;
        this.imgid=imgid;
        this.color=color;
    }
    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_item, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        TextView balanceText = (TextView) rowView.findViewById(R.id.balance);
        TextView statusText = (TextView) rowView.findViewById(R.id.status);
        //set setiap value berdasarkan posisi sekarang
        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        subtitleText.setText(subtitle[position]);
        balanceText.setText(balance[position]);
        statusText.setText(status[position]);
        balanceText.setTextColor(color[position]);
        return rowView;

    };
}