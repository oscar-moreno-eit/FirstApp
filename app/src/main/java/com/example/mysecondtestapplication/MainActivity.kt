package com.example.mysecondtestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.GetContent
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * Use to define an entry point in your  Application.
 * It will describe the IU/UX for a given Screen
 * It can ONLY describe a single xml file.
 */



class MainActivity : AppCompatActivity(), View.OnClickListener {



    private lateinit var btnCreateTodo: FloatingActionButton
    //    private lateinit var etInputTodo: EditText --> moved to..
    private lateinit var lvDisplayTodo: ListView

    companion object{
        const val OPEN_REQ_CODE = 23412
        private const val TAG = "MainActivity"
    }

    private val getNewTodo = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

        // when TodoCreateActivity calls setResult....
        // we receive the callback here....
        //it.data
        if (result.resultCode == RESULT_OK){
            result.data?.getStringExtra(TodoCreateActivity.NEW_TODO)?.let {
                addNewTodo(it)
            }
        }
    }

    private val adapter: ArrayAdapter<String> by lazy {
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
    }



    /**
     * @param savedInstanceState Used to "save" the data state after a Configuration Change.
     * @see setContentView defines the XML file that will be used for this Activity.
     * Its the first callback invoked in the Activity lifecycle
     * Use onCreate to "setup" or initialize views or external dependencies.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: ${intent}")


        btnCreateTodo = findViewById(R.id.fab_create_todo)
        //etInputTodo = findViewById(R.id.et_todo_input)
        lvDisplayTodo = findViewById(R.id.lv_list_todo)

        //Anonymous Interface Impl
        btnCreateTodo.setOnClickListener(
            object: View.OnClickListener{
                override fun onClick(v: View?) {

                }
            }
        )

        //Interface implementation
        btnCreateTodo.setOnClickListener(this)

        //Lambda
        btnCreateTodo.setOnClickListener {

        }

        //Interface implementation
        btnCreateTodo.setOnClickListener(this)

        //Method reference
        btnCreateTodo.setOnClickListener  (this::validateInput)

        lvDisplayTodo.setOnItemLongClickListener { parent, view, position, id ->
            adapter.remove(adapter.getItem(position))
            true
        }

    }

    private fun validateInput(view: View) {
        val openCreateActivity = Intent()
        openCreateActivity.setClass(this,TodoCreateActivity::class.java)
        //startActivityForResult(openCreateActivity,OPEN_REQ_CODE)
        getNewTodo.launch(openCreateActivity)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d(TAG, "onActivityResult: ")

        if (requestCode == OPEN_REQ_CODE && resultCode == RESULT_OK && data != null){
            data.let {
                val newTodo = it.getStringExtra(TodoCreateActivity.NEW_TODO)
                val newCat = it.getStringExtra(TodoCreateActivity.NEW_CATE)
//                addNewTodo(newTodo ?: "")
                addNewTodo(newTodo ?: "")

            }  //Safe call(?.), elvis operator(?:), assertion operator(!!)
        }
    }

    private fun addNewTodo(todoInput: String) {
        //todo create an adapter. Explain adapter
        //todo add data into the Adapter

        adapter.add(todoInput)
        lvDisplayTodo.adapter = adapter
//        etInputTodo.text.clear()
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View?) {

    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy:  ")

    }

}