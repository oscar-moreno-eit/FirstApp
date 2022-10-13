package com.example.mysecondtestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TodoCreateActivity extends AppCompatActivity {

    public static final String NEW_TODO = "NEW_TODO_TodoCreateActivity";
    public static final String NEW_CATE = "NEW_CAT_TodoCreateActivity";

    private static final String TAG = "TodoCreateActivity";

    private EditText etNewTodo;
    private Spinner spNewTodoCategory;
    private Button btnNewTodoSave;
    private ArrayAdapter adapterCategory;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_create);
        initViews();

    }

    private void initViews() {
        etNewTodo = findViewById(R.id.et_todo_input);
        spNewTodoCategory = findViewById(R.id.sp_todo_category);
        btnNewTodoSave = findViewById(R.id.btn_save_todo);

        btnNewTodoSave.setOnClickListener(view -> {
                    String newTodoInput = etNewTodo.getText().toString();
                    if (newTodoInput.isEmpty()){
                        showError(getString(R.string.error_message));
                    }else{
                        addNewTodo(newTodoInput,spNewTodoCategory.getSelectedItem().toString());
                    }
                }
        );

//        spNewTodoCategory.setOn -- Events

        adapterCategory = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.category_list)
        );

        spNewTodoCategory.setAdapter(adapterCategory);

    }

    private void addNewTodo(String newTodoInput, String newTodoCategory) {
        //todo steps for startActivityForResult
        Intent sendNewTodoWithCategory = new Intent();
        sendNewTodoWithCategory.putExtra(NEW_TODO,newTodoInput);
        sendNewTodoWithCategory.putExtra(NEW_CATE,newTodoCategory);
        setResult(RESULT_OK, sendNewTodoWithCategory);
        finish();
    }

    private void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart: ");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}