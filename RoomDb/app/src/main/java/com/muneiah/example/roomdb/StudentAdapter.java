package com.muneiah.example.roomdb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHoder> {
    Context ctx;
    List<StudentEntity> list;
    /*right click >generate >constuctor*
    select all
    click ok/
     */

    public StudentAdapter(Context ctx, List<StudentEntity> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public StudentViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHoder(LayoutInflater.from(ctx).inflate(R.layout.row_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHoder holder, int position) {
    holder.n.setText(list.get(position).getName());
    holder.r.setText(list.get(position).getRollnum());
    holder.d.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity.dataBase.studentDAO().delete(list.get(position));
            Toast.makeText(ctx, "deleted "+holder.n.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StudentViewHoder extends RecyclerView.ViewHolder {

        TextView n, r, e, d;

        public StudentViewHoder(@NonNull View itemView) {
            super(itemView);
            n = itemView.findViewById(R.id.nam);
            r = itemView.findViewById(R.id.rollnam);
            e = itemView.findViewById(R.id.edit);
            d = itemView.findViewById(R.id.delete);
            e.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nn=n.getText().toString();
                    String rr=r.getText().toString();
                    Intent intent=new Intent(ctx,UpdateActivity.class);
                    intent.putExtra("name_key",nn);
                    intent.putExtra("roll_key",rr);
                    ctx.startActivity(intent);
                }
            });

        }
    }
}
