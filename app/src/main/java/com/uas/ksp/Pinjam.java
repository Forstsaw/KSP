package com.uas.ksp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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

public class Pinjam extends Fragment {
    EditText pinjamValue;
    private FragmentActivity myContext;
    int value;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_pinjam, container, false);

        pinjamValue = (EditText) v.findViewById(R.id.edtPinjamUang);
        //menerima value dari halaman home yang dikirim melalui fragment
        final String userid = getArguments().getString("id");
        final String balanceNow = getArguments().getString("balanceNow");
        final String name = getArguments().getString("name");

        Button btnProses = (Button) v.findViewById(R.id.btnProsesPinjam);

        // proses melakukan peminjaman dan melakukan penyinmpan ke database jumlah yang dipinjam
        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String svalue = pinjamValue.getText().toString();
                value = Integer.parseInt(pinjamValue.getText().toString());

                //menampilkan alert dialog, untuk melakukan konfirmasi
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirmation ");
                builder.setMessage("APAKAH ANDA INGIN MEMINJAM UANG DENGAN SEBESAR: Rp." + svalue);

                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Fragment fragment;
                        ProsesPinjaman pinjam = new ProsesPinjaman(getActivity());
                        String check = null;
                        try {
                            check = pinjam.execute(userid, svalue).get(); //melakukan proses asyntask
                            if (check.equals("success")) {
                                Toast.makeText(getContext(), "Berhasil Pinjam Uang Sebesar Rp." + svalue, Toast.LENGTH_LONG).show();
                                Intent homeIntent = new Intent(getContext(),Home.class);
                                homeIntent.putExtra("id", userid); //mengiirm value ke halaman home
                                homeIntent.putExtra("name", name);
                                startActivity(homeIntent);
                            } else {
                                Toast.makeText(getContext(), "Gagal Pinjam Uang ", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getContext(), "Tidak jadi pinjam uang", Toast.LENGTH_LONG).show();
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


