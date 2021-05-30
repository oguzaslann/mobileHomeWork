package com.example.a0407_example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    ArrayList<Question> questionsArrayList = new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;

    public QuestionAdapter(ArrayList<Question> questionsArrayList, Context context) {
        this.questionsArrayList = questionsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.row_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.description.setText(questionsArrayList.get(position).getDescription());
        holder.answer1.setText("A) " + questionsArrayList.get(position).getAnswer1());
        holder.answer2.setText("B) " + questionsArrayList.get(position).getAnswer2());
        holder.answer3.setText("C) " + questionsArrayList.get(position).getAnswer3());
        holder.answer4.setText("D) " + questionsArrayList.get(position).getAnswer4());
        holder.correctAnswer.setText("Cevap: " +questionsArrayList.get(position).getCorrectAnswer());
        //holder.imageView.setImageURI(Uri.parse(questionsArrayList.get(position).getPhoto()));

        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent updateIntent = new Intent(v.getContext(), QuestionUpdateActivity.class);
                    updateIntent.putExtra("questionId", String.valueOf(questionsArrayList.get(position).getId()));
                    v.getContext().startActivity((updateIntent));
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("Silme onayı");
                alert.setMessage("Soruyu silmek istediğinize emin misiniz?");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        Toast.makeText(v.getContext(), "Silme başarılı", Toast.LENGTH_SHORT).show();
                        SQLLiteHelperQuestions sqlLiteHelperQuestions = new SQLLiteHelperQuestions(context);
                        sqlLiteHelperQuestions.DeleteQuestion(questionsArrayList.get(position).getId());
                        Intent menuIntent = new Intent(v.getContext(), MenuActivity.class);
                        v.getContext().startActivity((menuIntent));

                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });

        holder.linearLayout.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return questionsArrayList.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView description, answer1, answer2, answer3, answer4, correctAnswer, difficultyLevel;
        ImageView imageView;
        Button updateButton, deleteButton;
        LinearLayout linearLayout;
        Uri imageUri;

        public  ViewHolder(View itemView){
            super(itemView);

            description = itemView.findViewById(R.id.description);
            answer1 = itemView.findViewById(R.id.answer1);
            answer2 = itemView.findViewById(R.id.answer2);
            answer3 = itemView.findViewById(R.id.answer3);
            answer4 = itemView.findViewById(R.id.answer4);
            correctAnswer = itemView.findViewById(R.id.correctAnswer);
            imageView = itemView.findViewById(R.id.imageView);
            difficultyLevel = itemView.findViewById(R.id.difficultyLevel);
            updateButton = itemView.findViewById(R.id.updateButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
