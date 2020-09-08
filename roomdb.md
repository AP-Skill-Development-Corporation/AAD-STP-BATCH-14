# Android DataBase  

### Introduction 

**Data persistence is one of the basic requirements of most applications. SQLite, an open-source library is a means of persisting data for Android applications. The implementation of SQLite requires lots of boilerplate code, however.This has drawbacks:**
* Syntax errors in queries
* No compile time error detection (Time consuming)
* Parsing is required to convert data to Plain Old Java Objects (POJO) objects

**These issues are quite common in Q&A forums, which is likely why popular No-SQL databases like Realm, GreenDAO and Room came along. Room is a persistent library that abstract away the most of the SQLite code using annotations.**

**In This aims to cover:**

* Storage options for Android
* SQL vs. No-SQL
* Room Library basics
* Using the Room Library to build a app

#### Introduction to Android storage mechanisms
**Core data storage mechanisms**
1. Key-Value pairs : SharedPreferences, An android framework API, which stores key-values pairs in an XML file under protected file system.
  * Data stored via SharedPreference can only be accessed within the app.
  * Can only store boolean, int, long, String and Set of String.
2. Internal and External storage : Applications can store text or CSV files, images, videos in phone memory or inside public directories(kitkat or above) under SD card storage.
  * To access phone or external storage, Applications requires to implement Requesting Permissions Model for marshmallow and above.
  * Repeatedly accessing physical hard drive space slows down the application.
  * Applications can access all directories under external storage on API's below KitKat.
  * Accessible to other applications, No protection.
3. SQLite : SQLite is a light-weight relational database, embedded into the Android OS. The database schema is mapped to tables and integrity constraints.
  * Runtime memory consumption is merely 250 kbytes.
  * Supported data types are INTEGER, REAL (decimals), TEXT, BLOB (mostly used to store images but don't do it) and null.
4. NoSQL : NoSQL simply means Objects or Documents. Instead of storing the data in tabular form, The data is stored in POJO form, which is extremely suitable for semi-structured or un-structured data when there is no fixed schema.

#### SQL vs NoSQL

Features	| SQL	| No-SQL
---|---|---
Data Stored|	In Tabular form (RDBMS)|	POJO objects or Documents
Data Manipulation	|via DML,DDL	|via provided API's
Structure|	RDBMS|	key-value pairs
Schema	|Fixed schema|	dynamic, records can be added on the fly
Scalable	|RDBMS	|key-value pairs
Android Support	|SQLite	|Room(semi-sql), GreenDAO, Realm

### Room Basics
**The Room library acts as an abstract layer for underlying SQLite database. Thus, Room annotations are used:**
  * To Database and Entities where entities are POJO classes representing table structures.
  * To specify operation for retrieval, updation and deletion.
  * To add constraints, such as foreign keys.
  * Support for LiveData.
**There are 3 major components in Room**
1. **[Entity](https://developer.android.com/reference/androidx/room/Entity) :** A class annotated with the @Entity annotation is mapped to a table in database. Every entity is persisted in its own table and every field in class represents the column name.
    * tableName attribute is used to define the name of the table
    * Every entity class must have at-least one Primary Key field, annotated with @PrimaryKey
    * Fields in entity class can be annotated with @ColumnInfo(name = “name_of_column”) annotation to give specific column names
2. **[DAO](https://developer.android.com/reference/androidx/room/Dao.html) :** Data Access Object is either be an interface or an abstract class annotated with @Doa annotation, containing all the methods to define the operations to be performed on data. The methods can be annotated with

    * @Query to retrieve data from database
    * @Insert to insert data into database
    * @Delete to delete data from database
    * @Update to update data in database
 
 **The result of SQLite queries are composed into cursor object, DAO methods abstract the conversion of cursor to Entity objects and vice-versa.**

3. **Database :** Database is a container for tables. An abstract class annotated with @Database annotation is used to create a database with given name along with database version.

   * version = intValueForDBVersion is used to define the database version
   * entities = {EntityClassOne.class, ....} is used to define list of entities for database
   
### Room Architecture
  
### For Adding Dependancys :

**At build.gradle file(app:module)**
```
android{
     compileOptions {
        sourceCompatibility = 1.8
        targetCompatibility = 1.8
    }
      dependencies{
         // Room components
    implementation "androidx.room:room-runtime:$rootProject.roomVersion"
    annotationProcessor "androidx.room:room-compiler:$rootProject.roomVersion"
    androidTestImplementation "androidx.room:room-testing:$rootProject.roomVersion"

// Lifecycle components
    implementation "androidx.lifecycle:lifecycle-extensions:$rootProject.archLifecycleVersion"
    annotationProcessor "androidx.lifecycle:lifecycle-compiler:$rootProject.archLifecycleVersion"
    implementation 'androidx.recyclerview:recyclerview:1.2.0-alpha04'
      }
}

```

**at build.gradle(project level)**
```
ext {
    roomVersion = '2.2.1'
    archLifecycleVersion = '2.2.0'
    coreTestingVersion = '2.1.0'
    materialVersion = '1.0.0'
}

```

<img src="https://raw.githubusercontent.com/Muneiahtellakula/android_development/master/rmdb.PNG">

### Using the Architecture Components

* There are a lot of steps to using the Architecture Components and implementing the recommended architecture. The most important thing is to create a mental model of what is going on, understanding how the pieces fit together and how the data flows. As you work through this codelab, don't just copy and paste the code, but try to start building that inner understanding.

**What are the recommended Architecture Components?**

* Here is a short introduction to the Architecture Components and how they work together. Note that this codelab focuses on a subset of the components, namely LiveData, ViewModel and Room. Each component is explained more as you use it.

**This diagram shows a basic form of this architecture:**

<img src="https://codelabs.developers.google.com/codelabs/android-room-with-a-view/img/a7da8f5ea91bac52.png">

[Entity](https://developer.android.com/reference/androidx/room/Entity): Annotated class that describes a database table when working with Room.

**SQLite database**: On device storage. The Room persistence library creates and maintains this database for you.

[DAO](https://developer.android.com/reference/androidx/room/Dao.html): Data access object. A mapping of SQL queries to functions. When you use a DAO, you call the methods, and Room takes care of the rest.

[Room database](https://developer.android.com/topic/libraries/architecture/room): Simplifies database work and serves as an access point to the underlying SQLite database (hides SQLiteOpenHelper). The Room database uses the DAO to issue queries to the SQLite database.

**Repository**: Used to manage multiple data sources.

[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel): Acts as a communication center between the Repository (data) and the UI. The UI no longer needs to worry about the origin of the data. ViewModel instances survive Activity/Fragment recreation.

[LiveData](https://developer.android.com/topic/libraries/architecture/livedata): A data holder class that can be observed. Always holds/caches the latest version of data, and notifies its observers when data has changed. LiveData is lifecycle aware. UI components just observe relevant data and don't stop or resume observation. LiveData automatically manages all of this since it's aware of the relevant lifecycle status changes while observing.

## Let we move on a samll practical on ROOM DataBase With Live Data:

**For adding dependencies** [Sourse from Google Code Lab Or official ](https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#3)

1. **build.gradle(Project):**
```
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.6.1'
        

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
/*For Room DB*/
ext {
    roomVersion = '2.2.1'
    archLifecycleVersion = '2.2.0'
    coreTestingVersion = '2.1.0'
    materialVersion = '1.0.0'
}
```

2. **build.gradle(Module:app)**

```

apply plugin: 'com.android.application'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.3"
    /* target compile version */
    compileOptions {
        sourceCompatibility = 1.8
        targetCompatibility = 1.8
    }
    defaultConfig {
        applicationId "com.muneiah.roomdatabase"
        minSdkVersion 21
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
/*For Room Database*/
    // Room components
    implementation "androidx.room:room-runtime:$rootProject.roomVersion"
    annotationProcessor "androidx.room:room-compiler:$rootProject.roomVersion"
    androidTestImplementation "androidx.room:room-testing:$rootProject.roomVersion"

// Lifecycle components
    implementation "androidx.lifecycle:lifecycle-extensions:$rootProject.archLifecycleVersion"
    annotationProcessor "androidx.lifecycle:lifecycle-compiler:$rootProject.archLifecycleVersion"
    implementation 'androidx.recyclerview:recyclerview:1.2.0-alpha04'
}


```
3. **activity_main.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:orientation="vertical"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity"
    android:layout_margin="2dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Room DataBase Demo"
        android:textSize="40sp"
        android:layout_gravity="center"
        android:textColor="@color/colorPrimary"
         />

    <EditText
        android:layout_width="match_parent"
        android:layout_margin="10dp"
        android:layout_height="wrap_content"
        android:hint="Enter the Student name"
        android:id="@+id/student_name"/>

    <EditText
        android:layout_width="match_parent"
        android:layout_margin="10dp"
        android:layout_height="wrap_content"
        android:hint="Enter the Student Rollnumber"
        android:id="@+id/student_rollnumber"/>
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="save"
        android:layout_margin="10dp"
        android:onClick="saveData"/>
        //for Live Data I commented below button because retrive buttion won't requvired 
 <!--   <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="retrive"
        android:layout_margin="10dp"
        android:onClick="retriveData"/>-->

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/rec"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp" />

</LinearLayout>

```
4. **row_design.xml**
```

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal" android:layout_width="match_parent"
    android:gravity="center"
    android:layout_height="wrap_content">
    <TextView
        android:drawableLeft="@drawable/ic_edit_black_24dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="30sp"
        android:id="@+id/edit"/>
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Name"
    android:layout_margin="10dp"
    android:textSize="30sp"
    android:textColor="@color/colorPrimary"
    android:id="@+id/nam"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Roll number"
        android:layout_margin="10dp"
        android:textSize="25sp"
        android:textColor="@color/colorAccent"
        android:id="@+id/rollnam"/>
    <TextView
        android:textSize="30sp"
        android:drawableRight="@drawable/ic_delete_black_24dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/delete"/>
</LinearLayout>

```
5. **Students_Entity.java**
```
package com.muneiah.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student_Entity {

    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "rollnumber")
    private String rollNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(@NonNull String rollNumber) {
        this.rollNumber = rollNumber;
    }
}


```

6. **StudentDAO**
```
package com.muneiah.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {
    @Insert
    public void insertData(Student_Entity entity);
    @Update
    public void updateData(Student_Entity entity);
    @Delete
    public void deleteData(Student_Entity entity);

    //for live data
    @Query("SELECT * FROM student_table")
    public LiveData<List<Student_Entity>> retriveData();
    //public List<Student_Entity> retriveData();//Normal data
}

```
7. **StudentDataBase.java**
```
package com.muneiah.roomdatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Student_Entity.class,version = 1,exportSchema = false)
public abstract class StudentDataBase extends RoomDatabase {
    private static StudentDataBase INSTANCE;
    public abstract StudentDAO studentDAO();
    //For Live Data
        public static synchronized StudentDataBase getDataBase(Context context){
            if (INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context,StudentDataBase.class,"muni").allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build();
            }
            return INSTANCE;
        }

}


```
8. **MyAdapter.java**
```
package com.muneiah.roomdatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context ctx;
    List<Student_Entity> list;

    public MyAdapter(Context ctx, List<Student_Entity> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(ctx).inflate(R.layout.row_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.n.setText(list.get(position).getName());
        holder.r.setText(list.get(position).getRollNumber());
        holder.d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // MainActivity.dataBase.studentDAO().deleteData(list.get(position)); //normal db
                //live db
                MainActivity.myViewModel.delete(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView n,r,e,d;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            n=itemView.findViewById(R.id.nam);
            r=itemView.findViewById(R.id.rollnam);
            e=itemView.findViewById(R.id.edit);
            d=itemView.findViewById(R.id.delete);
            e.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nn=n.getText().toString();
                    String rr=r.getText().toString();
                    Intent i=new Intent(ctx,UpdateActivity.class);
                    i.putExtra("name",nn);
                    i.putExtra("roll",rr);
                    ctx.startActivity(i);

                }
            });

        }
    }
}

```
9. **MainAActivity.java**
```
package com.muneiah.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText stu_name,stu_roll;
RecyclerView recyler;
MyAdapter adapter;
static StudentDataBase dataBase;
Student_Entity entity;
List<Student_Entity> entityList;
static MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stu_name=findViewById(R.id.student_name);
        stu_roll=findViewById(R.id.student_rollnumber);
        recyler=findViewById(R.id.rec);
        //Naormal data //dataBase= Room.databaseBuilder(this,StudentDataBase.class,"muni").allowMainThreadQueries().build();
        //for live data
        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
            myViewModel.liveData().observe(this, new Observer<List<Student_Entity>>() {
                @Override
                public void onChanged(List<Student_Entity> student_entities) {
                    adapter=new MyAdapter(MainActivity.this,student_entities);
                    recyler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyler.setAdapter(adapter);
                }
            });

    }

    public void saveData(View view) {
        String name=stu_name.getText().toString();
        String rollnumber=stu_roll.getText().toString();
        entity=new Student_Entity();
        entity.setName(name);
        entity.setRollNumber(rollnumber);
       // dataBase.studentDAO().insertData(entity);// for normal db
        //for live data
        myViewModel.insert(entity);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    /*public void retriveData(View view) {
       // entityList=dataBase.studentDAO().retriveData();
        adapter=new MyAdapter(this,entityList);
        recyler.setLayoutManager(new LinearLayoutManager(this));
        recyler.setAdapter(adapter);

    }*/
}

```
10. **For Update Create new Empty activiy name UpdateActivity**
 A) **activity_update.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:orientation="vertical"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".UpdateActivity">
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Update Details"
        android:textSize="30sp"
        android:gravity="center"/>
    <EditText
        android:layout_width="match_parent"
        android:layout_margin="10dp"
        android:layout_height="wrap_content"
        android:hint="Enter the Student name"
        android:id="@+id/update_name"/>

    <EditText
        android:layout_width="match_parent"
        android:layout_margin="10dp"
        android:layout_height="wrap_content"
        android:hint="Enter the Student Rollnumber"
        android:id="@+id/update_rollnumber"/>
    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Update"
        android:layout_margin="10dp"
        android:onClick="updateData"/>
</LinearLayout>
```
 B) **UpdateActivty.java**
 ```
 
 package com.muneiah.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
EditText updateName,updateRoll;
Student_Entity entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        updateName=findViewById(R.id.update_name);
        updateRoll=findViewById(R.id.update_rollnumber);
        Intent i=getIntent();
        String n=i.getStringExtra("name");
       String r= i.getStringExtra("roll");
       updateName.setText(n);
       updateRoll.setText(r);
       updateRoll.setKeyListener(null);


    }

    public void updateData(View view) {
        String updatedName=updateName.getText().toString();
        String updatedRoll=updateRoll.getText().toString();
        entity=new Student_Entity();
        entity.setRollNumber(updatedRoll);
        entity.setName(updatedName);
       // MainActivity.dataBase.studentDAO().updateData(entity);//normal db
        //live db
        MainActivity.myViewModel.update(entity);
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);


    }
}

 
 ```
11. **StudentRepo.java**
```
package com.muneiah.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepo {
    LiveData<List<Student_Entity>> listLiveData;
    StudentDataBase studentDataBase;

    public StudentRepo(Application application){
        studentDataBase=StudentDataBase.getDataBase(application);
        listLiveData=studentDataBase.studentDAO().retriveData();
    }
    //for live data ineserting
    public class MyAsyncTakForInsert extends AsyncTask<Student_Entity,Void,Void>{

        @Override
        protected Void doInBackground(Student_Entity... student_entities) {
            studentDataBase.studentDAO().insertData(student_entities[0]);
            return null;
        }
    }
    //for live data update
    public class MyAsyncTakForUpdate extends AsyncTask<Student_Entity,Void,Void>{

        @Override
        protected Void doInBackground(Student_Entity... student_entities) {
            studentDataBase.studentDAO().updateData(student_entities[0]);
            return null;
        }
    }
    //for live data delete
   public class MyAsyncTaskDelete extends AsyncTask<Student_Entity,Void,Void>{

        @Override
        protected Void doInBackground(Student_Entity... student_entities) {
            studentDataBase.studentDAO().deleteData(student_entities[0]);
            return null;
        }
    }
    public void insert(Student_Entity entity)
    {
        new MyAsyncTakForInsert().execute(entity);
    }
    public void update(Student_Entity entity)
    {
        new MyAsyncTakForUpdate().execute(entity);
    }
    public void delete(Student_Entity entity)
    {
        new MyAsyncTaskDelete().execute(entity);
    }
}


```
12. **MyViewModel.java**
```
package com.muneiah.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    StudentRepo studentRepo;
    LiveData<List<Student_Entity>> listLiveData_model;
    public MyViewModel(@NonNull Application application) {
        super(application);
        studentRepo=new StudentRepo(application);
        listLiveData_model=studentRepo.listLiveData;
    }
    public void insert(Student_Entity entity){
        studentRepo.insert(entity);
    }
    public void update(Student_Entity entity){
        studentRepo.update(entity);
    }
    public void delete(Student_Entity entity){
        studentRepo.delete(entity);
    }
    public LiveData<List<Student_Entity>> liveData(){

        return  listLiveData_model;
    }
}


```
13. **ic_delete_black_24dp.xml**
 **A) Delete Icon**
 ```
 <vector android:height="24dp" android:tint="#FF624A"
    android:viewportHeight="24.0" android:viewportWidth="24.0"
    android:width="24dp" xmlns:android="http://schemas.android.com/apk/res/android">
    <path android:fillColor="#FF000000" android:pathData="M6,19c0,1.1 0.9,2 2,2h8c1.1,0 2,-0.9 2,-2V7H6v12zM19,4h-3.5l-1,-1h-5l-1,1H5v2h14V4z"/>
</vector>

 ```
 **B) Edit Icon**
 ```
 <vector android:height="24dp" android:tint="#0D17FF"
    android:viewportHeight="24.0" android:viewportWidth="24.0"
    android:width="24dp" xmlns:android="http://schemas.android.com/apk/res/android">
    <path android:fillColor="#FF000000" android:pathData="M3,17.25V21h3.75L17.81,9.94l-3.75,-3.75L3,17.25zM20.71,7.04c0.39,-0.39 0.39,-1.02 0,-1.41l-2.34,-2.34c-0.39,-0.39 -1.02,-0.39 -1.41,0l-1.83,1.83 3.75,3.75 1.83,-1.83z"/>
</vector>

 ```
14. **row_design.xml**
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal" android:layout_width="match_parent"
    android:gravity="center"
    android:layout_height="wrap_content">
    <TextView
        android:drawableLeft="@drawable/ic_edit_black_24dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="30sp"
        android:id="@+id/edit"/>
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Name"
    android:layout_margin="10dp"
    android:textSize="30sp"
    android:textColor="@color/colorPrimary"
    android:id="@+id/nam"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Roll number"
        android:layout_margin="10dp"
        android:textSize="25sp"
        android:textColor="@color/colorAccent"
        android:id="@+id/rollnam"/>
    <TextView
        android:textSize="30sp"
        android:drawableRight="@drawable/ic_delete_black_24dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:id="@+id/delete"/>
</LinearLayout>
```


