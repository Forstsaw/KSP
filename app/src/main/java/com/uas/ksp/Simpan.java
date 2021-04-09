package com.uas.ksp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class Simpan extends Fragment {
    private EditText simpanValue;
    private FragmentActivity myContext;
    private int value;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_simpan, container, false);
        simpanValue = (EditText) v.findViewById(R.id.edtSimpanUang);

        //menerima value dari halaman home
        final String userid = getArguments().getString("id");
        final String balanceNow = getArguments().getString("balanceNow");
        final String name = getArguments().getString("name");

        Button btnNext = (Button) v.findViewById(R.id.btnNextSimpan);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String svalue = simpanValue.getText().toString();
                value = Integer.parseInt(simpanValue.getText().toString());

                //menampilkan alert box alert
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirmation ");
                builder.setMessage("UANG ANDA AKAN DI TRANSFER DARI SALDO AKTIF ANDA DAN TERSIMPAN DALAM KOPERASI SEBESAR: "+ svalue);

                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        simpan_proses simpan = new simpan_proses(getActivity());
                        try {
                            String total = String.valueOf(value + Integer.parseInt(balanceNow));
                            String check = simpan.execute(userid,svalue,total).get(); //menjalankan asyntask untuk simpan

                            //jika berhasil menyimpan saldo
                            if (check.equals("success")){
                                Toast.makeText(getContext(),"Berhasil Top Up Saldo Sebesar Rp."+svalue,Toast.LENGTH_LONG).show();
                                Intent homeIntent = new Intent(getContext(),Home.class);
                                homeIntent.putExtra("id", userid); //mengirim value ke halaman home
                                homeIntent.putExtra("name", name);
                                startActivity(homeIntent);

                            }else{
                                Toast.makeText(getContext(),"Gagal Top Up Saldo ",Toast.LENGTH_LONG).show();
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                });

                builder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"UANG YANG ANDA SIMPAN TIDAK TERVERIFIKASI",Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });
        return v;
    }
    @Override
    public void onAttach(@NonNull Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


}
