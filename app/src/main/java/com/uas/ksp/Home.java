package com.uas.ksp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class Home extends AppCompatActivity {
    public FragmentManager fm;
    public FragmentTransaction ft;
    Bundle historyBundle = new Bundle();
    Bundle simpanBundle = new Bundle();
    Bundle pinjamBundle = new Bundle();
    Bundle angsurBundle = new Bundle();
    TextView txtnama,BalanceUser,timeNow;
    String id2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id"); //menerima value id dari intent login
        String name = intent.getStringExtra("name"); //menerima value name dari intent login
        txtnama = (TextView)findViewById(R.id.txtNamaUser);
        txtnama.setText(name);
        Balance balance = new Balance(this);
        String result  ="ads";
        id2 = id;
        String balanceuser = "0";

        //cek waktu
        timeNow = (TextView)findViewById(R.id.txtTime);
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if(timeOfDay < 12){
            timeNow.setText("Good Morning");
        }else if(timeOfDay < 16){
            timeNow.setText("Good Afternoon");
        }else if(timeOfDay < 21){
            timeNow.setText("Good Evening");
        }else if(timeOfDay < 24){
            timeNow.setText("Good Night");
        }


        //cek balance user
        try {
            result = balance.execute("checkBalance",id).get();
            String[] valuesBalance = result.split(",");
            balanceuser = valuesBalance[2];
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BalanceUser = (TextView)findViewById(R.id.txtBalanceUser);
        BalanceUser.setText(balanceuser);

        // kirim id, balance dan nama user ke masing-masing fragment
        historyBundle.putString("id",id2);
        historyBundle.putString("balanceNow",balanceuser);

        simpanBundle.putString("id",id2);
        simpanBundle.putString("balanceNow",balanceuser);
        simpanBundle.putString("name",name);

        angsurBundle.putString("id",id2);
        angsurBundle.putString("balanceNow",balanceuser);
        angsurBundle.putString("name",name);

        pinjamBundle.putString("id",id2);
        pinjamBundle.putString("balanceNow",balanceuser);
        pinjamBundle.putString("name",name);


        //ketika di home otomatis jalankan history karena dafault fragment
        History historyFragment = new History();
        historyFragment.setArguments(historyBundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        ft.replace(R.id.frame1, historyFragment);
        ft.commit();


    }

    //button fragment
    public void changeFragment(View v){
        Fragment fragment;

        if(v == findViewById(R.id.btnPinjam)){

            Pinjam pinjamFragment = new Pinjam();
            pinjamFragment.setArguments(simpanBundle);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();
            ft.replace(R.id.frame1, pinjamFragment);
            ft.commit();

        }
        if(v == findViewById(R.id.btnSimpan)){

            Simpan simpanFragment = new Simpan();
            simpanFragment.setArguments(simpanBundle);
            //fragment = new Simpan();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();
            ft.replace(R.id.frame1, simpanFragment);
            ft.commit();

        }
        if(v == findViewById(R.id.btnAngsur)){
            Angsur angsurFragment = new Angsur();
            angsurFragment.setArguments(simpanBundle);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft =fm.beginTransaction();
            ft.replace(R.id.frame1, angsurFragment);
            ft.commit();

        }
    }
    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
