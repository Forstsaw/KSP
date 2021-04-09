package com.uas.ksp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;


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

public class Angsur extends Fragment {
    EditText noTag, telahdibayar,sejumlah,penerima;
    private FragmentActivity myContext;
    Bundle historyBundle = new Bundle();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_angsur, container, false);

        //menerima value dari halaman home
        final String userid = getArguments().getString("id");
        final String name = getArguments().getString("name");

        noTag = (EditText) v.findViewById(R.id.edtNoTagihan);
        telahdibayar = (EditText) v.findViewById(R.id.edtTelah);
        sejumlah = (EditText) v.findViewById(R.id.edtSejumlah);
        penerima = (EditText) v.findViewById(R.id.edtAngsuran);

        Button btnNext2 = (Button) v.findViewById(R.id.btnProsesAngsur);
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String noTag2 = noTag.getText().toString();
                final String telahdibayar2 = telahdibayar.getText().toString();
                final String svalue = sejumlah.getText().toString();
                final String penerima2 = penerima.getText().toString();

                //menampilkan alert box alert
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Confirmation ");
                builder.setMessage("APAKAH ANDA INGIN ANGSUR SEBESAR: Rp."+ svalue + " KEPADA " +penerima2);

                builder.setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AngsurProses angsur = new AngsurProses(getActivity());
                        try {
                           // String total = String.valueOf(noTag2,telahdibayar2,svalue,penerima2);
                            String check = angsur.execute(userid,noTag2,telahdibayar2,svalue,penerima2).get(); //melakukan proses asyntask

                            if (check.equals("success")){
                                Toast.makeText(getContext(),"Berhasil Melakukan Angsuran Sebesar Rp."+svalue,Toast.LENGTH_LONG).show();
                                Intent homeIntent = new Intent(getContext(),Home.class);
                                homeIntent.putExtra("id", userid);
                                homeIntent.putExtra("name", name); //mengirim value ke halaman home
                                startActivity(homeIntent);
                            }else if(check.equals("tidakcukup")){
                                Toast.makeText(getContext(),"Saldo Simpanan Anda Tidak Cukup Harap Isi Saldo Anda",Toast.LENGTH_LONG).show();
                            }else if(check.equals("failed")) {
                                Toast.makeText(getContext(), "Kesalahan sistem", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(getContext(),"Gagal Melakukan Angsuran",Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getContext(),"Gagal Melakukan Angsuran",Toast.LENGTH_LONG).show();
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
