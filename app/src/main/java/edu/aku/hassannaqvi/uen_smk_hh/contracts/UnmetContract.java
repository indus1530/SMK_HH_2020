package edu.aku.hassannaqvi.uen_smk_hh.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class UnmetContract {


    private String _ID = "";
    private String UID = "";
    private String _UUID = "";
    private String deviceId = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String sUN = "";
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


    public UnmetContract hydrate(Cursor cursor) {
        this._ID = cursor.getString(cursor.getColumnIndex(SingleUnmet.COLUMN__ID));
        this.UID = cursor.getString(cursor.getColumnIndex(SingleUnmet.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(SingleUnmet.COLUMN__UUID));
        this.deviceId = cursor.getString(cursor.getColumnIndex(SingleUnmet.COLUMN_DEVICEID));
        this.formDate = cursor.getString(cursor.getColumnIndex(SingleUnmet.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(SingleUnmet.COLUMN_USER));
        this.sUN = cursor.getString(cursor.getColumnIndex(SingleUnmet.COLUMN_SUN));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(SingleUnmet.COLUMN_DEVICETAGID));

        return this;

    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(SingleUnmet.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(SingleUnmet.COLUMN_UID, this.UID == null ? JSONObject.NULL : this.UID);
        json.put(SingleUnmet.COLUMN__UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(SingleUnmet.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(SingleUnmet.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(SingleUnmet.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);

        if (this.sUN != null && !this.sUN.equals("")) {
            json.put(SingleUnmet.COLUMN_SUN, this.sUN.equals("") ? JSONObject.NULL : new JSONObject(this.sUN));
        }
        json.put(SingleUnmet.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);

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

    public String getsUN() {
        return sUN;
    }

    public void setsUN(String sUN) {
        this.sUN = sUN;
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

    public static abstract class SingleUnmet implements BaseColumns {

        public static final String TABLE_NAME = "unmet_table";
        public static final String COLUMN__ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN__UUID = "_uuid";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_SUN = "sun";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";


    }


}
