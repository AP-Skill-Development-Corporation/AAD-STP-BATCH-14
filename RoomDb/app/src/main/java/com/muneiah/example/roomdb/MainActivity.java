package com.muneiah.example.roomdb;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProvider;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import androidx.room.Room;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText name,rollnumber;
    RecyclerView rec;
   static StudentDataBase dataBase;
    StudentEntity entity;
    StudentAdapter adapter;
    List<StudentEntity> entityList;
    static StudentViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rec=findViewById(R.id.recycler);
        name=findViewById(R.id.editTextPersonName);
        rollnumber=findViewById(R.id.editTextPersonRollnum);
        /*dataBase= Room.databaseBuilder(this,StudentDataBase.class,"ap")
                .allowMainThreadQueries()
                .build();*/
        viewModel=new ViewModelProvider(this).get(StudentViewModel.class);
        viewModel.liveData().observe(this, new Observer<List<StudentEntity>>() {
            @Override
            public void onChanged(List<StudentEntity> studentEntities) {
                adapter=new StudentAdapter(MainActivity.this,studentEntities);
                rec.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rec.setAdapter(adapter);
            }
        });
    }

    public void saveData(View view) {
        String myname=name.getText().toString();
        String myroll=rollnumber.getText().toString();
        entity=new StudentEntity();
        entity.setName(myname);
        entity.setRollnum(myroll);
        viewModel.insert(entity);
       /* dataBase.studentDAO().insert(entity);*/
        Toast.makeText(this, "Success "+myname, Toast.LENGTH_SHORT).show();

    }

   /* public void reriveData(View view) {
        entityList=dataBase.studentDAO().retrive();
        adapter=new StudentAdapter(this,entityList);
        Toast.makeText(this, "Total "+entityList.size(), Toast.LENGTH_SHORT).show();
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setAdapter(adapter);
    }*/
}