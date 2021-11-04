package sanjuan.comino.carlos.proyectovinos;

import android.util.Log;

import java.io.BufferedReader;
import java.io.*;

public class FileIO {
    private static final String TAG = MainActivity.class.getName() + "xyzyx";


    public static String[] getFileLines(File file, String fileName){
        File f = new File(file, fileName);
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            String cache = "";
            while ((linea = br.readLine()) != null) {
                cache += linea + "\n";
            }
            br.close();
            return cache.split("%");
        } catch (IOException e) {
            Log.v(TAG, e.toString());
        }
        return null;
    }


    public static boolean writeLine(File file, String filename, String line){
        File f = new File(file, filename);
        FileWriter fw;
        try {
            fw = new FileWriter(f, true);
            fw.write(line);
            fw.flush();
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public static boolean deleteLine(File file, String fileName, String id){

        File f = new File(file, fileName);
        File f2 = new File(file, "temp.tmp");
        String str[];
        String tmp;
        try {
            FileWriter fw = new FileWriter(f2);
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linea;
            while((linea = br.readLine()) != null){
                str = linea.split(";");
                if(!id.equals(str[0])){
                    tmp = linea;
                    fw.write(tmp);
                    fw.write("\n");
                    fw.flush();
                }
            }
            fw.close();
            br.close();

            f.delete();
            f2.renameTo(f);

            return true;
        } catch (Exception e){
            return false;
        }
    }
}
