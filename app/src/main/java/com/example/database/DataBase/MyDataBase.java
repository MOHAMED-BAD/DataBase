package com.example.database.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.database.Car;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {
    public static final int DB_version=1;
    public static final String DB_name="cars_db";
    public static final String TB_NAME="car";
    public static final String TB_ID="id";
    public static final String TB_MODEL="model";
    public static final String  TB_COLOR="color";
    public static final String TB_DISTANCE="distance";

    public MyDataBase(Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create new Table
        //يتم استداعها مرة وادة فقط
        db.execSQL("CREATE TABLE "+TB_NAME+"( "+TB_ID+" INTEGER Primary KEY AUTOINCREMENT , " +
                " "+" "+TB_MODEL+" TEXT , "+TB_COLOR+" TEXT , "+TB_DISTANCE+" REAL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          // تم استدعها عند تغير version
          db.execSQL("DROP TABLE IF EXISTS "+TB_NAME);
          onCreate(db);

    }
    public boolean insertCar(Car car){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(TB_MODEL ,car.getModel());
        values.put(TB_COLOR,car.getColor());
        values.put(TB_DISTANCE,car.getDistance());
        Long result=db.insert(TB_NAME, null,values);
       return result != -1;
    }
    public boolean updateCar(Car car){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(TB_MODEL ,car.getModel());
        values.put(TB_COLOR,car.getColor());
        values.put(TB_DISTANCE,car.getDistance());
        String [] conditions={String.valueOf(car.getId())};
        int result=db.update(TB_NAME,values,"id=?",conditions);
       return result>0;
     }
     public boolean deleteCar(Car car){
        SQLiteDatabase db= getWritableDatabase();
        String [] conditions={String.valueOf(car.getId())};
        int result=db.delete(TB_NAME,"id=?",conditions);
       return result>0;
     }
     //عدد الصفوف في الداتابيز
     public Long getCountCar(){
        SQLiteDatabase db=getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db,TB_NAME);
     }
     public ArrayList<Car> getAllCar() {
        ArrayList<Car> cars = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        // cursor start with -1 , Save values in row and col
        Cursor c = db.rawQuery("SELECT * FROM " + TB_NAME, null);
        if (c.moveToFirst()) {
            do{
                int id=c.getInt(c.getColumnIndex(TB_ID));
                String model=c.getString(c.getColumnIndex(TB_MODEL));
                String color=c.getString(c.getColumnIndex(TB_COLOR));
                double distance =c.getDouble(c.getColumnIndex(TB_DISTANCE));
                Car car =new Car(id,model,color,distance);
                cars.add(car);
            }
            while (c.moveToNext());
            c.close();
        }
        return cars;
    }
    //Search using Model
    public ArrayList<Car> getCar(String modelSearch) {
        ArrayList<Car> cars = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        // cursor start with -1 , Save values in row and col
        Cursor c = db.rawQuery("SELECT * FROM " + TB_NAME+ " WHERE "+TB_MODEL , new String[]{modelSearch});
        if (c.moveToFirst()) {
            do{
                int id=c.getInt(c.getColumnIndex(TB_ID));
                String model=c.getString(c.getColumnIndex(TB_MODEL));
                String color=c.getString(c.getColumnIndex(TB_COLOR));
                double distance =c.getDouble(c.getColumnIndex(TB_DISTANCE));
                Car car =new Car(id,model,color,distance);
                cars.add(car);
            }
            while (c.moveToNext());
            c.close();
        }
        return cars;
    }
}
