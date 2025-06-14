package by.roman.test_app.business_logic.data.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import by.roman.test_app.business_logic.data.dto.ModelDTO;
import by.roman.test_app.business_logic.data.models.Model;

public class ModelDao {
    protected static final String TABLE_MODEL = "model";
    protected static final String COLUMN_VUNP = "vunp";
    protected static final String COLUMN_MAIL = "mail";
    protected static final String COLUMN_VNAIMP = "vnaimp";
    protected static final String COLUMN_VNAIMK = "vnaimk";
    protected static final String COLUMN_VPADRES = "vpadres";
    protected static final String COLUMN_DREG = "dreg";
    protected static final String COLUMN_NMNS = "nmns";
    protected static final String COLUMN_VMNS = "vmns";
    protected static final String COLUMN_CKODSOST = "ckodsost";
    protected static final String COLUMN_VKODS = "vkods";
    protected static final String COLUMN_DLIKV = "dlikv";
    protected static final String COLUMN_VLIKV = "vlikv";

    protected static final String CREATE_TABLE_MODEL = "CREATE TABLE " + TABLE_MODEL + " (" +
            COLUMN_VUNP + " TEXT PRIMARY KEY, " +
            COLUMN_MAIL + " TEXT, " +
            COLUMN_VNAIMP + " TEXT, " +
            COLUMN_VNAIMK + " TEXT, " +
            COLUMN_VPADRES + " TEXT, " +
            COLUMN_DREG + " TEXT, " +
            COLUMN_NMNS + " TEXT, " +
            COLUMN_VMNS + " TEXT, " +
            COLUMN_CKODSOST + " TEXT, " +
            COLUMN_VKODS + " TEXT, " +
            COLUMN_DLIKV + " TEXT, " +
            COLUMN_VLIKV + " TEXT);";
    private final SQLiteDatabase db;
    public ModelDao(SQLiteDatabase db){
        this.db = db;
    }
    public void addModel(@NonNull ModelDTO dto){
        ContentValues values = new ContentValues();
        values.put(COLUMN_VUNP, dto.vunp);
        values.put(COLUMN_MAIL, dto.mail);
        values.put(COLUMN_VNAIMP, dto.vnaimp);
        values.put(COLUMN_VNAIMK, dto.vnaimk);
        values.put(COLUMN_VPADRES, dto.vpadres);
        values.put(COLUMN_DREG, dto.dreg);
        values.put(COLUMN_NMNS, dto.nmns);
        values.put(COLUMN_VMNS, dto.vmns);
        values.put(COLUMN_CKODSOST, dto.ckodsost);
        values.put(COLUMN_VKODS, dto.vkods);
        values.put(COLUMN_DLIKV, dto.dlikv);
        values.put(COLUMN_VLIKV, dto.vlikv);
        db.insert(TABLE_MODEL, null, values);
    }
    public boolean hasRecords() {
        String query = "SELECT 1 FROM " + TABLE_MODEL + " LIMIT 1";

        try (Cursor cursor = db.rawQuery(query, null)) {
            return cursor != null && cursor.moveToFirst();
        } catch (Exception e) {
            Log.e("DB_ERROR", "Failed to check records", e);
            return false;
        }
    }
    public void removeModel(String vunp){
        String selection = COLUMN_VUNP + " = ?";
        String[] selectionArgs = {vunp};
        db.delete(TABLE_MODEL, selection, selectionArgs);
    }
    public Model getModel(String vunp){
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MODEL + " WHERE " +
                COLUMN_VUNP + " = ?", new  String[]{String.valueOf(vunp)});
        if (cursor.moveToNext()) {
            try (cursor) {
                int vunpIndex = cursor.getColumnIndex(COLUMN_VUNP);
                int mailIndex = cursor.getColumnIndex(COLUMN_MAIL);
                int vnaimpIndex = cursor.getColumnIndex(COLUMN_VNAIMP);
                int vnaimkIndex = cursor.getColumnIndex(COLUMN_VNAIMK);
                int vpadresIndex = cursor.getColumnIndex(COLUMN_VPADRES);
                int dregIndex = cursor.getColumnIndex(COLUMN_DREG);
                int nmnsIndex = cursor.getColumnIndex(COLUMN_NMNS);
                int vmnsIndex = cursor.getColumnIndex(COLUMN_VMNS);
                int ckodsostIndex = cursor.getColumnIndex(COLUMN_CKODSOST);
                int vkodsIndex = cursor.getColumnIndex(COLUMN_VKODS);
                int dlikvIndex = cursor.getColumnIndex(COLUMN_DLIKV);
                int vlikvIndex = cursor.getColumnIndex(COLUMN_VLIKV);
                if(vunpIndex != -1 && mailIndex != -1 && vnaimpIndex != -1 && vnaimkIndex != -1 && vpadresIndex != -1 && dregIndex != -1 &&
                        nmnsIndex != -1 && vmnsIndex != -1 && ckodsostIndex != -1 && vkodsIndex != -1 && dlikvIndex != -1 && vlikvIndex != -1){
                    return new Model(
                            cursor.getString(vunpIndex),
                            cursor.getString(mailIndex),
                            cursor.getString(vnaimpIndex),
                            cursor.getString(vnaimkIndex),
                            cursor.getString(vpadresIndex),
                            cursor.getString(dregIndex),
                            cursor.getString(nmnsIndex),
                            cursor.getString(vmnsIndex),
                            cursor.getString(ckodsostIndex),
                            cursor.getString(vkodsIndex),
                            cursor.getString(dlikvIndex),
                            cursor.getString(vlikvIndex)
                    );
                }
            }
        }
        return null;
    }
    public List<String> getUMPList(){
        List<String> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_VUNP + " FROM " + TABLE_MODEL, null);
        if(cursor.moveToNext()){
            try (cursor){
                int index = cursor.getColumnIndex(COLUMN_VUNP);
                if(index != -1){
                    list.add(cursor.getString(index));
                }
            }
        }
        return list;
    }
}
