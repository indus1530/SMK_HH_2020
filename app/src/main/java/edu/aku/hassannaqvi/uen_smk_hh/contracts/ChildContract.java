package edu.aku.hassannaqvi.uen_smk_hh.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class ChildContract {


    private String _ID = "";
    private String UID = "";
    private String _UUID = "";
    private String deviceId = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String sH1 = "";
    private String sJ = "";
    private String devicetagID = "";
    private String synced = "";
    private String synced_date = "";

     /*
    saved in JSON
    =============
    * hhno
    * cluster
    * i1_fm_uid
    * i1_fm_serial
    * i1_res_fm_uid
    * i1_res_fm_serial
    * i2_fm_uid
    * i2_fm_serial
    * i2_res_fm_uid
    * i2_res_fm_serial
    * j_fm_uid
    * j_fm_serial
    * j_res_fm_uid
    * j_res_fm_serial
    * */


    public ChildContract hydrate(Cursor cursor) {

        this._ID         = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN__ID));
        this.UID         = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_UID));
        this._UUID       = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN__UUID));
        this.deviceId    = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_DEVICEID));
        this.formDate    = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_FORMDATE));
        this.user        = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_USER));
        this.sH1         = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_SH1));
        this.sJ          = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_SJ));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(ChildTable.COLUMN_DEVICETAGID));

        return this;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(ChildTable.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(ChildTable.COLUMN_UID, this.UID == null ? JSONObject.NULL : this.UID);
        json.put(ChildTable.COLUMN__UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(ChildTable.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(ChildTable.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(ChildTable.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);

        if (this.sH1 != null && !this.sH1.equals("")) {
            json.put(ChildTable.COLUMN_SH1, this.sH1.equals("") ? JSONObject.NULL : new JSONObject(this.sH1));
        }

        if (this.sJ != null && !this.sJ.equals("")) {
            json.put(ChildTable.COLUMN_SJ, this.sJ.equals("") ? JSONObject.NULL : new JSONObject(this.sJ));
        }

        json.put(ChildTable.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);

        return json;
    }

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String get_UUID() {
        return _UUID;
    }

    public void set_UUID(String _UUID) {
        this._UUID = _UUID;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getsH1() {
        return sH1;
    }

    public void setsH1(String sH1) {
        this.sH1 = sH1;
    }

    public String getsJ() {
        return sJ;
    }

    public void setsJ(String sJ) {
        this.sJ = sJ;
    }

    public String getDevicetagID() {
        return devicetagID;
    }

    public void setDevicetagID(String devicetagID) {
        this.devicetagID = devicetagID;
    }

    public String getSynced() {
        return synced;
    }

    public void setSynced(String synced) {
        this.synced = synced;
    }

    public String getSynced_date() {
        return synced_date;
    }

    public void setSynced_date(String synced_date) {
        this.synced_date = synced_date;
    }

    public static abstract class ChildTable implements BaseColumns {

        public static final String TABLE_NAME = "child_table";
        public static final String COLUMN__ID = "_id"; // autoincreament
        public static final String COLUMN_UID = "_uid"; // unique identifier
        public static final String COLUMN__UUID = "_uuid"; // unique form identifier
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_SH1 = "sh1";
        public static final String COLUMN_SJ = "sj";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";
    }
}
