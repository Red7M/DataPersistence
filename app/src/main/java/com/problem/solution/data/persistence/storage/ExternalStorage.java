package com.problem.solution.data.persistence.storage;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import java.io.File;

public class ExternalStorage {

  private static final String TAG = "ExternalStorage";

  private Context context;

  public ExternalStorage(Context context) {
    this.context = context;
  }

  /**
   * Checks if a volume containing external storage is available
   * for read and write.
   */
  public boolean isExternalStorageWritable() {
    return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
  }

  /**
   * Checks if a volume containing external storage is available to at least read.
   */
  public boolean isExternalStorageReadable() {
    return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ||
        Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY);
  }

  public void selectPhysicalStorageLocation() {
    File[] externalStorageVolumes =
        ContextCompat.getExternalFilesDirs(context, null);
    File primaryExternalStorage = externalStorageVolumes[0];
  }

  public File accessPersistentFile(String parentPathName, String fileName) {
    return new File(parentPathName, fileName);
  }

  public File createCacheFiles(String fileName) {
    return new File(context.getExternalCacheDir(), fileName);
  }

  public boolean removeCacheFiles(File externalCacheFile) {
    return externalCacheFile.delete();
  }

  @Nullable
  public File getAppSpecificAlbumStorageDir(Context context, String albumName) {
    File file = new File(context.getExternalFilesDir(
        Environment.DIRECTORY_PICTURES), albumName);
    if (!file.mkdirs()) {
      Log.e(TAG, "Directory not created");
    }
    return file;
  }
}
