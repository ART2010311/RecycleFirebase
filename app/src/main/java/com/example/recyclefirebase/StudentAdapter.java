package com.example.recyclefirebase;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private final Context context;
    ArrayList<Student> studentArrayList;

    public StudentAdapter(Context context, ArrayList<Student> studentArrayList) {
        this.context = context;
        this.studentArrayList = studentArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentArrayList.get(position);
        holder.n.setText(student.name);
        holder.e.setText(student.getEmail());
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView n, e;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            n = itemView.findViewById(R.id.name);
            e = itemView.findViewById(R.id.email);
        }
    }
}