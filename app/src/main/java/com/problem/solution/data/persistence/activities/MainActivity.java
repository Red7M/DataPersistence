package com.problem.solution.data.persistence.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.problem.solution.data.persistence.R;
import com.problem.solution.data.persistence.storage.ContentProviderData;
import com.problem.solution.data.persistence.storage.ExternalStorage;
import com.problem.solution.data.persistence.storage.InternalStorage;
import com.problem.solution.data.persistence.storage.KeyValueData;
import java.io.File;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);


    onExecuteInternalStorageMethods();
    onExecuteExternalStorageMethods();
    onExecuteSharedPreferences();
    onExecuteContentProviderMethods();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  /**
   * Executes all methods for internal storage.
   *
   * Note: set some hardcoded variables, then debug through
   * breakpoints in order to obtain results in your console.
   */
  private void onExecuteInternalStorageMethods() {
    InternalStorage internalStorage = new InternalStorage(this, "enter file name here");
    // Call the appropriate method to execute an internal storage
    internalStorage.accessAndStoreFile();
    internalStorage.storeUsingStream();
    internalStorage.accessFileUsingStream();
    internalStorage.viewListOfFiles();
    internalStorage.createNestedDirectories();

    File tempCacheFile = internalStorage.createTempFile();
    internalStorage.isCacheFileRemoved(tempCacheFile);
  }

  /**
   * Executes all methods for external storage.
   *
   * Note: set some hardcoded variables, then debug through
   * breakpoints in order to obtain results in your console.
   */
  private void onExecuteExternalStorageMethods() {
    ExternalStorage externalStorage = new ExternalStorage(this);
    // Call the appropriate method to execute an external storage
    externalStorage.isExternalStorageWritable();
    externalStorage.isExternalStorageWritable();
    externalStorage.isExternalStorageReadable();
    externalStorage.selectPhysicalStorageLocation();
    externalStorage.accessPersistentFile("thisIsParent", "thisIsFileName");
    externalStorage.createCacheFiles("thisIsFileName");
    externalStorage.removeCacheFiles(new File("externalCacheFile"));
    externalStorage.getAppSpecificAlbumStorageDir(this, "thisIsAlbum");
  }

  /**
   * Executes all methods for shared preferences.
   *
   * Note: set some hardcoded variables, then debug through
   * breakpoints in order to obtain results in your console.
   */
  private void onExecuteSharedPreferences() {
    KeyValueData keyValueData = new KeyValueData(this);
    keyValueData.writeToSharedPreference();
    keyValueData.readFromSharedPreferences();
  }

  /**
   * Executes all methods for content providers.
   *
   * Note: set some hardcoded variables, then debug through
   * breakpoints in order to obtain results in your console.
   */
  private void onExecuteContentProviderMethods() {
    ContentProviderData contentProviderData = new ContentProviderData(this);
    contentProviderData.getDictionaryUri();
    contentProviderData.buildColumnForEachRow();
    contentProviderData.getSelectionClause();
    contentProviderData.selectionArguments();
    contentProviderData.insertData();
    contentProviderData.updateData();
    contentProviderData.deleteData();
  }
}
