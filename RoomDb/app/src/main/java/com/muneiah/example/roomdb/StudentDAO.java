package com.muneiah.example.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert
    public void insert(StudentEntity entity);
    @Update
    public void update(StudentEntity entity);
    @Delete
    public void delete(StudentEntity entity);
    @Query("SELECT * FROM student_table")
    public List<StudentEntity> retrive();//normal Room Db
}
