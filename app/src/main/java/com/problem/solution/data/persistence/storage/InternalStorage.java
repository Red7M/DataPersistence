package com.problem.solution.data.persistence.storage;

import android.content.Context;
import androidx.annotation.Nullable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorage {

  private Context context;
  private String fileName;

  public InternalStorage(Context context, String fileName) {
    this.context = context;
    this.fileName = fileName;
  }

  public void accessAndStoreFile() {
    File file = new File(context.getFilesDir(), fileName);
  }

  public void storeUsingStream() {
    String filename = "thisIsMyFile";
    String fileContents = "Hello World";
    try {
      FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
      fileOutputStream.write(fileContents.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void accessFileUsingStream() {
    FileInputStream fis = null;
    try {
      fis = context.openFileInput(fileName);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    InputStreamReader inputStreamReader =
        new InputStreamReader(fis);
    StringBuilder stringBuilder = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(inputStreamReader);
      String line = reader.readLine();
      while (line != null) {
        stringBuilder.append(line).append('\n');
        line = reader.readLine();
      }
    } catch (IOException e) {
      // Error occurred when opening raw file for reading.
    } finally {
      String contents = stringBuilder.toString();
    }
  }

  public String[] viewListOfFiles() {
    return context.fileList();
  }

  public void createNestedDirectories() {
    File directory = context.getFilesDir();
    File file = new File(directory, fileName);
  }

  @Nullable
  public File createTempFile() {
    File tempFile = null;
    try {
      tempFile = File.createTempFile(fileName, null, context.getCacheDir());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return tempFile;
  }

  public boolean isCacheFileRemoved(File cacheFile) {
    return cacheFile.delete();
  }


}
