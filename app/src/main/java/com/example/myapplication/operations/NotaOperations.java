package com.example.myapplication.operations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.SQLHelper;
import com.example.myapplication.models.NotaModel;

import java.util.ArrayList;

public class NotaOperations {

    private static final String DBNAME = "appnotasmyapp.db";
    private static final String NOMBRE_TABLA = "nota";
    private static final int VERSION = 2;
    public final Context context;
    public static SQLiteDatabase database;
    private SQLHelper helper;
    private NotaModel model;



    public NotaOperations(Context context) {
        this.context = context;
        helper = new SQLHelper(context, DBNAME, null, VERSION);
    }

    public void openRead(){
        database = helper.getReadableDatabase();
    }

    public void openWrite(){
        database = helper.getWritableDatabase();
    }

    public void close(){
        database.close();
    }

    public int insertModel(NotaModel model){
        try {
            ContentValues values = new ContentValues();
            values.put("titulo", model.getTitulo());
            values.put("contenido", model.getContenido());

            openWrite();
            return (int) database.insert(NOMBRE_TABLA, null, values);

        } catch(Exception e){
            String TAG = "MyActivity";
            Log.i(TAG,"e" + e);
            return -1;

        }

   }

    public int delete(int id){
        try {
            String idString = String.valueOf(id);
            String sqlWhere = "id = ?";
            String[] whereArgs = new String[] {idString};

            openWrite();
            return database.delete(NOMBRE_TABLA, sqlWhere, whereArgs);

        }catch (Exception e){
            return -1;
        }
    }







    public ArrayList<String> selectAllString() {
        ArrayList<String> list = new ArrayList<>();

        try {
            openRead();
            Cursor cursor = database.query(NOMBRE_TABLA,null,null,null,null,null,null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do{
                    int id;
                    String titulo,contenido;

                    id = cursor.getInt(cursor.getColumnIndex("id"));
                    titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                    contenido = cursor.getString(cursor.getColumnIndex("contenido"));

                    model = new NotaModel(id,titulo,contenido);
                    list.add(model.toString());

                }while (cursor.moveToNext());
            }
            return list;
        }catch (Exception e){
            return list;
        }
    }
}


