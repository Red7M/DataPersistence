package com.problem.solution.data.persistence.storage;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.UserDictionary.Words;
import androidx.annotation.Nullable;

/**
 * Content provider class to have inner communications between application.
 *
 * Note: Read permission for dictionary is added to the AndroidManifest.xml
 */
public class ContentProviderData {

  private Context context;

  public ContentProviderData(Context context) {
    this.context = context;
  }

  public Uri getDictionaryUri() {
    return ContentUris.withAppendedId(Words.CONTENT_URI, 4);
  }

  public String[] buildColumnForEachRow() {
    return
        new String[]{
            Words._ID,
            Words.WORD,
            Words.LOCALE
        };
  }

  @Nullable
  public String getSelectionClause() {
    return null;
  }

  public String[] selectionArguments() {
    return new String[]{""};
  }

  public void insertData() {
    Uri newUri;
    ContentValues contentValues = new ContentValues();

    contentValues.put(Words.APP_ID, "hello_id");
    contentValues.put(Words.LOCALE, "en_US");
    contentValues.put(Words.WORD, "add");
    contentValues.put(Words.FREQUENCY, "100");

    newUri = context.getContentResolver().insert(
        Words.CONTENT_URI,
        contentValues
    );
  }

  public void updateData() {
    ContentValues updateValues = new ContentValues();
    String selectionClause = Words.LOCALE +  " Good!";
    String[] selectionArgs = {"en_%"};
    int rowsUpdated = 0;

    updateValues.putNull(Words.LOCALE);
    rowsUpdated = context.getContentResolver()
        .update(
        Words.CONTENT_URI,
        updateValues,
        selectionClause,
        selectionArgs
    );
  }

  public void deleteData() {
    String selectionClause = Words.APP_ID + "Good!";
    String[] selectionArgs = {"user"};
    int rowsDeleted = 0;

    // Deletes the words that match the selection criteria
    rowsDeleted = context.getContentResolver()
        .delete(
        Words.CONTENT_URI,
        selectionClause,
        selectionArgs
    );
  }


}
