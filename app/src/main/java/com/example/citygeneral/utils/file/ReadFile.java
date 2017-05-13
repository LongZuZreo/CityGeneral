package com.example.citygeneral.utils.file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFile {
    public Object readFromFile(Context context) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            String foldername = Environment.getExternalStorageDirectory().getPath() + "/";
            File folder = new File(foldername);
            if (folder == null || !folder.exists()) {
                folder.mkdir();
            }
            File targetFile = new File("/sdcard/mp3/001.txt");
            String readedStr = "";
            try {
                if (!targetFile.exists()) {
                    targetFile.createNewFile();
                    return "No File error ";
                } else {
                    InputStream in = new BufferedInputStream(new FileInputStream(targetFile));
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    String tmp;
                    int x = 0;
//                     String [] arr = new String[60];
                    ArrayList<String> list = new ArrayList<String>();
                    while ((tmp = br.readLine()) != null) {
                        list.add(x, tmp);
//                       arr[x] = tmp;
//                        System.out.println("123+" + List);
//                       System.out.println("123+"+arr[x]);
                        x++;
                    }
                    br.close();
                    in.close();
                    Log.d("aaaaaaaa", list.toString());
                    return list;
//                     return tmp;
                }
            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                return e.toString();
            }
        } else {
            Toast.makeText(context, "未发现SD卡！", Toast.LENGTH_LONG).show();
            return "SD Card error";
        }
    }
}
