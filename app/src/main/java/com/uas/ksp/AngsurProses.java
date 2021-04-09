package com.uas.ksp;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class AngsurProses extends AsyncTask<String, Void, String> {

    Context context;


    AngsurProses(Context ctx){
        this.context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String simpan_url = "https://korporasipinjamsimpan.000webhostapp.com/angsur.php";
        try {
            String user_id = params[0];
            String no_tagihan = params[1];
            String telah_dibayar = params[2];
            String sejumlah = params[3];
            String penerima = params[4];


            URL url = new URL(simpan_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(user_id,"UTF-8") + "&" +
                    URLEncoder.encode("no_tagihan","UTF-8")+"="+URLEncoder.encode(no_tagihan,"UTF-8") + "&" +
                    URLEncoder.encode("telah_dibayar","UTF-8")+"="+URLEncoder.encode(telah_dibayar,"UTF-8") + "&" +
                    URLEncoder.encode("sejumlah","UTF-8")+"="+URLEncoder.encode(sejumlah,"UTF-8") + "&" +
                    URLEncoder.encode("penerima","UTF-8")+"="+URLEncoder.encode(penerima,"UTF-8");

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line;
            while((line = bufferedReader.readLine()) != null){
                result+=line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
