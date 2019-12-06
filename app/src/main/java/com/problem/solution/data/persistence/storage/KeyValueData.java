package com.problem.solution.data.persistence.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class KeyValueData {

  private Context context;
  private final static String CONSTANT_AWESOME_KEY = "IAM_AWESOME_SHARED_KEY";

  public KeyValueData(Context context) {
    this.context = context;
  }

  private SharedPreferences getSharedPreferences() {
    return context.getSharedPreferences("mySharedPreferenceFile", Context.MODE_PRIVATE);
  }

  public void writeToSharedPreference() {
    SharedPreferences sharedPreferences = getSharedPreferences();
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(CONSTANT_AWESOME_KEY, "I'm awesome");
    editor.apply();
  }

  public void readFromSharedPreferences() {
    SharedPreferences sharedPreferences = getSharedPreferences();
    String sharedName = "default_value";
    sharedPreferences.getString(CONSTANT_AWESOME_KEY, sharedName);
  }
}
