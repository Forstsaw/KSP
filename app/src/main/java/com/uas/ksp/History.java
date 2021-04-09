package com.uas.ksp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.concurrent.ExecutionException;

public class History extends Fragment {
    ListView list;
    String[] judul = {

    };

    String[] balance = {

    };
    String[] status = {

    };
    String[] date = {

    };
    int[] icon = {

    };

    int[] color = {

    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_history, container, false);
        setHasOptionsMenu(true);
        final String userid = getArguments().getString("id");

        String[] historyValue;
        //proses ambil history berdasarkan user dan dimasukin ke masing2 list
        historyProcess history = new historyProcess(getActivity());
        try {
            String check = history.execute(userid).get();

            historyValue = check.split(",");

            for (int i = 0; i < historyValue.length;i++){
                if(historyValue[i].equals("simpan")){

                    judul = addX(judul.length, judul, historyValue[i]);
                    status = addX(status.length, status, "SAVE");
                    balance = addX(balance.length, balance, "+ Rp."+historyValue[i+2]);
                    icon = addIcon(icon.length, icon, R.drawable.simpan);
                    date = addX(date.length, date, historyValue[i+4]);
                    color = addIcon(color.length, color,  ContextCompat.getColor(getContext(), R.color.greenBalance));


                }else if(historyValue[i].equals("pinjam")){
                    judul = addX(judul.length, judul, historyValue[i]);
                    balance = addX(balance.length, balance, "+ Rp."+historyValue[i+2]);
                    icon = addIcon(icon.length, icon, R.drawable.pinjam);
                    status = addX(status.length, status, "INCOMING");
                    date = addX(date.length, date, historyValue[i+4]);
                    color = addIcon(color.length, color, ContextCompat.getColor(getContext(), R.color.greenBalance));

                }else if(historyValue[i].equals("angsur")){
                    judul = addX(judul.length, judul, historyValue[i]);
                    balance = addX(balance.length, balance, "- Rp."+historyValue[i+3]);
                    icon = addIcon(icon.length, icon, R.drawable.angsur);
                    status = addX(status.length, status, "OUTGOING");
                    date = addX(date.length, date, historyValue[i+5]);
                    color = addIcon(color.length, color, Color.RED);

                }
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //proses untuk meanmpilkan hasil list ke dalam fragment history
        HistoryAdapter adapter=new HistoryAdapter((Activity) getContext(), judul, date, status, balance,icon,color);
        list=(ListView)v.findViewById(R.id.list);
        list.setAdapter(adapter);

        return v;
    }

    // untuk menambah value yang berupa string ke dalam list
    public static String[] addX(int n, String[] arr, String x)
    {
        int i;

        // create a new array of size n+1
        String newarr[] = new String[n + 1];


        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }
    // untuk menambah value yang berupa int ke dalam list, ini bisa di masuk dengan gambar,warna dan angka
    public static int[] addIcon(int n, int[] arr, int x)
    {
        int i;

        // create a new array of size n+1
        int newarr[] = new int[n + 1];


        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }



}
