package com.muneiah.example.roomdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class StudentEntity {
    @ColumnInfo(name = "stu_name")
    String name;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "rollNum")
    String rollnum;
    /*Right click >generate>getters and setters
    * select all
    * enter*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getRollnum() {
        return rollnum;
    }

    public void setRollnum(@NonNull String rollnum) {
        this.rollnum = rollnum;
    }
}
