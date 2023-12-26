package com.example.testandroid2612.thread;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    private static final String TAG = "MyWorker";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "Work is in progress");

        try {
            for (int i = 0; i < 3; i++) {
                Log.d(TAG, "Doing anything!");

                Data inputData = getInputData();
                String stringValue = inputData.getString("keyA");
                int intValue = inputData.getInt("keyB", 0);

                Log.d(TAG, "String : " + stringValue);
                Log.d(TAG, "Int : " + intValue);

                Thread.sleep(3 * 1000);
            }
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
            return Result.failure();
        }

        Data outputData = new Data.Builder()
                .putString("keyC", "value2")
                .build();

        Log.d(TAG, "Worker finished!");
        return Result.success(outputData);
    }
}
