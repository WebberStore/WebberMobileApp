package com.example.webberapp.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {
    public CustomExceptionHandler() {
    }

    public void uncaughtException(@NonNull Thread thread, Throwable exception) {
        logger(exception);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

    public void logger(Throwable exception) {
        StringWriter stackTrace = new StringWriter();
        exception.printStackTrace(new PrintWriter(stackTrace));
        Log.d("__LOG", "************ CAUSE OF ERROR ************\n\n");
        Log.d("__LOG", stackTrace.toString());
    }
}