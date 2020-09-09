package com.muneiah.example.roomdb;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepo {
    StudentDataBase studentDb;
    LiveData<List<StudentEntity>> listLiveData;
    public StudentRepo(Application app){
      studentDb=StudentDataBase.getDatabase(app);
      listLiveData=studentDb.studentDAO().liveData();
    }
    /*some of the background task*/
    //Insert
    public class MyAsysncTasForInsert extends AsyncTask<StudentEntity,Void,Void>{

        @Override
        protected Void doInBackground(StudentEntity... studentEntities) {
            studentDb.studentDAO().insert(studentEntities[0]);
            return null;
        }
    }
    //Insert
    public class MyAsysncTasForUpdate extends AsyncTask<StudentEntity,Void,Void>{

        @Override
        protected Void doInBackground(StudentEntity... studentEntities) {
            studentDb.studentDAO().update(studentEntities[0]);
            return null;
        }
    }
    //Insert
    public class MyAsysncTasForDelete extends AsyncTask<StudentEntity,Void,Void>{

        @Override
        protected Void doInBackground(StudentEntity... studentEntities) {
            studentDb.studentDAO().delete(studentEntities[0]);
            return null;
        }
    }
    public void insert(StudentEntity entity){
        new MyAsysncTasForInsert().execute(entity);
    }
    public void update(StudentEntity entity){
        new MyAsysncTasForUpdate().execute(entity);
    }
    public void delete(StudentEntity entity){
        new MyAsysncTasForDelete().execute(entity);
    }
    public LiveData<List<StudentEntity>> liveData(){
        return listLiveData;
    }

}
