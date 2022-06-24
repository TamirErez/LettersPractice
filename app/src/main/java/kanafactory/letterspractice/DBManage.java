package kanafactory.letterspractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBManage extends SQLiteOpenHelper {
    public final static int VERSION = 1;
    public final static String DBNAME = "Letters.db";
    public final static String CreateTable = "CREATE TABLE letters (name TEXT PRIMARY KEY)";
    private static DBManage instance;

    public static DBManage getInstance(Context context){
        if(instance == null)
            instance = new DBManage(context);
        return instance;
    }

    private DBManage(Context context){
        super(context,DBNAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateTable);
    }

    public List<String> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {"name"};
        Cursor cursor = db.query(
                "letters",
                projection,
                null,
                null,
                null,
                null,
                null,
                null);
        List<String> result = new ArrayList<>();
        while(cursor.moveToNext()){
            result.add(cursor.getString(0));
        }
        return result;
    }


    private boolean exists(String letter){
        List<String> allLetters = getAll();
        for(String s : allLetters)
            if(letter.equals(s))
                return true;
        return false;
    }

    public boolean remove(String letter){
        if(!exists(letter))
            return false;
        try {
            SQLiteDatabase db = getWritableDatabase();
            return db.delete("letters", "name = '" + letter + "'", null) > 0;
        }
        catch (Exception ignored){
            return false;
        }
    }

    public boolean add(String letter){
        if(exists(letter))
            return false;
        try {
            ContentValues values = new ContentValues();
            values.put("name", letter);
            SQLiteDatabase db = getWritableDatabase();
            db.insert("letters", null, values);
            return true;
        }
        catch (Exception ignored){
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
