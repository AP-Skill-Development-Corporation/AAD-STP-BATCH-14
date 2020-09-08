package com.muneiah.example.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = StudentEntity.class,version = 1,exportSchema = false)
public abstract class StudentDataBase extends RoomDatabase {
    public abstract StudentDAO studentDAO();

}
