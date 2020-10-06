package edu.aku.hassannaqvi.uen_smk_hh.contracts;

import android.database.Cursor;
import android.provider.BaseColumns;

import org.json.JSONException;
import org.json.JSONObject;

public class AdolscentContract {


    private String _ID = "";
    private String UID = "";
    private String _UUID = "";
    private String deviceId = "";
    private String formDate = ""; // Date
    private String user = ""; // Interviewer
    private String sAH1 = "";
    private String sAH2 = "";
    private String sAH3 = "";
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


    public AdolscentContract hydrate(Cursor cursor) {

        this._ID = cursor.getString(cursor.getColumnIndex(SingleAdolscent.COLUMN__ID));
        this.UID = cursor.getString(cursor.getColumnIndex(SingleAdolscent.COLUMN_UID));
        this._UUID = cursor.getString(cursor.getColumnIndex(SingleAdolscent.COLUMN__UUID));
        this.deviceId = cursor.getString(cursor.getColumnIndex(SingleAdolscent.COLUMN_DEVICEID));
        this.formDate = cursor.getString(cursor.getColumnIndex(SingleAdolscent.COLUMN_FORMDATE));
        this.user = cursor.getString(cursor.getColumnIndex(SingleAdolscent.COLUMN_USER));
        this.sAH1 = cursor.getString(cursor.getColumnIndex(SingleAdolscent.COLUMN_SAH1));
        this.sAH2 = cursor.getString(cursor.getColumnIndex(SingleAdolscent.COLUMN_SAH2));
        this.sAH3 = cursor.getString(cursor.getColumnIndex(SingleAdolscent.COLUMN_SAH3));
        this.devicetagID = cursor.getString(cursor.getColumnIndex(SingleAdolscent.COLUMN_DEVICETAGID));

        return this;
    }

    public JSONObject toJSONObject() throws JSONException {

        JSONObject json = new JSONObject();
        json.put(SingleAdolscent.COLUMN__ID, this._ID == null ? JSONObject.NULL : this._ID);
        json.put(SingleAdolscent.COLUMN_UID, this.UID == null ? JSONObject.NULL : this.UID);
        json.put(SingleAdolscent.COLUMN__UUID, this._UUID == null ? JSONObject.NULL : this._UUID);
        json.put(SingleAdolscent.COLUMN_DEVICEID, this.deviceId == null ? JSONObject.NULL : this.deviceId);
        json.put(SingleAdolscent.COLUMN_FORMDATE, this.formDate == null ? JSONObject.NULL : this.formDate);
        json.put(SingleAdolscent.COLUMN_USER, this.user == null ? JSONObject.NULL : this.user);

        if (this.sAH1 != null && !this.sAH1.equals("")) {
            json.put(SingleAdolscent.COLUMN_SAH1, this.sAH1.equals("") ? JSONObject.NULL : new JSONObject(this.sAH1));
        }
        if (this.sAH2 != null && !this.sAH2.equals("")) {
            json.put(SingleAdolscent.COLUMN_SAH2, this.sAH2.equals("") ? JSONObject.NULL : new JSONObject(this.sAH2));
        }
        if (this.sAH3 != null && !this.sAH3.equals("")) {
            json.put(SingleAdolscent.COLUMN_SAH3, this.sAH3.equals("") ? JSONObject.NULL : new JSONObject(this.sAH3));
        }
        json.put(SingleAdolscent.COLUMN_DEVICETAGID, this.devicetagID == null ? JSONObject.NULL : this.devicetagID);

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

    public String getsAH1() {
        return sAH1;
    }

    public void setsAH1(String sAH1) {
        this.sAH1 = sAH1;
    }

    public String getsAH2() {
        return sAH2;
    }

    public void setsAH2(String sAH2) {
        this.sAH2 = sAH2;
    }

    public String getsAH3() {
        return sAH3;
    }

    public void setsAH3(String sAH3) {
        this.sAH3 = sAH3;
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

    public static abstract class SingleAdolscent implements BaseColumns {

        public static final String TABLE_NAME = "adolescent";
        public static final String COLUMN__ID = "_id";
        public static final String COLUMN_UID = "_uid";
        public static final String COLUMN__UUID = "_uuid";
        public static final String COLUMN_DEVICEID = "deviceid";
        public static final String COLUMN_FORMDATE = "formdate";
        public static final String COLUMN_USER = "username";
        public static final String COLUMN_SAH1 = "sah1";
        public static final String COLUMN_SAH2 = "sah2";
        public static final String COLUMN_SAH3 = "sah3";
        public static final String COLUMN_DEVICETAGID = "tagid";
        public static final String COLUMN_SYNCED = "synced";
        public static final String COLUMN_SYNCED_DATE = "synced_date";


    }


}
