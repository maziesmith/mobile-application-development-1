package com.mad.exercise6.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.mad.exercise6.model.Train;

import java.util.ArrayList;
import java.util.List;

public class TrainDatabaseHelper extends SQLiteOpenHelper {

    private static TrainDatabaseHelper mInstance = null;
    private static final String DB_NAME = "trains.db";
    private static final String DB_TABLE = "train_table";

    //Columns
    private static final String ID = "ID";
    private static final String PLATFORM = "PLATFORM";
    private static final String ARRIVAL_TIME = "ARRIVAL_TIME";
    private static final String ONTIMEORLATE = "ARRIVAL_TIME";
    private static final String STATUS = "STATUS";
    private static final String DESTINATION = "DESTINATION";
    private static final String DESTINATION_TIME = "DESTINATION_TIME";


    private static final int DB_VERSION = 1;

    private Context context;


    public static TrainDatabaseHelper getInstance(Context context){
        if(mInstance == null){
            mInstance = new TrainDatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    //Private Constructor
    public TrainDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
//        SQLiteDatabase db = this.getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ DB_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, ARRIVAL_TIME TEXT, PLATFORM TEXT, STATUS TEXT, DESTINATION TEXT, DESTINATION_TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String platform, String arriveTime, String status, String destination, String destinationTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(ARRIVAL_TIME, arriveTime);
        cValues.put(PLATFORM, platform);
        cValues.put(STATUS, status);
        cValues.put(DESTINATION, destination);
        cValues.put(DESTINATION_TIME, destinationTime);
        long result = db.insert(DB_TABLE, null, cValues);
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE from " + DB_TABLE);
        db.close();
    }


    public List<Train> getData() {
        List<Train> trainList = new ArrayList<>();
        String query = "SELECT * FROM " + DB_TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //if table has any rows
        if(cursor.moveToFirst()){
            do{
                Train train = new Train();
                train.setPlatform(cursor.getString(1));
                train.setArrivalTime(cursor.getString(2));

                train.setStatus(cursor.getString(3));
                train.setDestination(cursor.getString(4));
                train.setDestinationTime(cursor.getString(5));
                trainList.add(train);
            } while (cursor.moveToNext());
        }

        db.close();

        return trainList;
    }
}
