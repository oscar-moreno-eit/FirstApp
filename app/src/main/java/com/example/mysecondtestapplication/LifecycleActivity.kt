package com.example.mysecondtestapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "LifecycleActivity"

class LifecycleActivity: AppCompatActivity() {

    /**
     * Used for view instantiation after the setContentView
     * Used to define the layout for this Activity. (setContentView)
     * Used to init any external dependencies.
     * It will be called only  ONCE per lifetime.
     * This will be called when the constructor is completed
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lifecycle_activity)

        findViewById<Button>(R.id.btn_open_main).setOnClickListener{
            // Navigate to MainActivity
            val navigateIntent = Intent()
            navigateIntent.setClass(this,MainActivity::class.java)
            startActivity(navigateIntent)
        }

        Log.d(TAG, "onCreate: 1")
    }

    // todo override onStart, OnResume, onPaused, onStop,  onDestroy
    // todo add the logd for each callbacks
    // todo change this LifecycleActivity as the main Activity

    /**
     * When the  onCreate is fully completed.
     * The user will be see the UI, but it cannot be interacted.
     * Update the default UI with some cache/online  data.
     */

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: 2")
    }

    /**
     * It defines the "running" state.
     * The user can see and interact  with the UI
     */

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: 3")
    }

    /**
     * The UI is visible but not interactive.
     * Will happen when:
     * A dialog is shown.
     * User navigates away.
     * Navigate to another Activity.
     * Multitask.
     *
     * Persist UI Data.
     */
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: 4")
    }

    /**
     * UI is not longer  visible.
     * The activity is still in memory.
     * The OS is getting ready to  kill this Process.
     * The user navigates to the  Launcher.
     * Use cases:
     * Saving data.
     * Disposing/Disconnecting components.
     */
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: 5")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: 6 Restarting app")
    }

    /**
     * This event is also called using finish() function.
     * It doesn't have reference when clear all apps button unless is onStop mode.
     * It executes after all instructions on onStop Event
     *
     * Is not guaranteed due resources scenarios
     * Is called when the Application is finish
     */

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: 7: This event is also called using finish() function. It doesn't have reference when clear all apps button unless is onStop mode. It executes after all instructions on onStop Event")

    }



}