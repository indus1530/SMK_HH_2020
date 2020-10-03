package edu.aku.hassannaqvi.uen_smk_hh.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Clear;
import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import edu.aku.hassannaqvi.uen_smk_hh.R;
import edu.aku.hassannaqvi.uen_smk_hh.contracts.ChildContract;
import edu.aku.hassannaqvi.uen_smk_hh.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_smk_hh.core.MainApp;
import edu.aku.hassannaqvi.uen_smk_hh.databinding.ActivitySectionH1Binding;
import edu.aku.hassannaqvi.uen_smk_hh.utils.Util;

public class SectionH1Activity extends AppCompatActivity {

    ActivitySectionH1Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_h1);
        bi.setCallback(this);

        setupSkips();

    }

    private void setupSkips() {

        //h102
        bi.h102.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId != bi.h102b.getId()) {

                Clear.clearAllFields(bi.fldGrpCVh103);
                Clear.clearAllFields(bi.fldGrpCVh104);
                bi.fldGrpCVh103.setVisibility(View.GONE);
                bi.fldGrpCVh104.setVisibility(View.GONE);
            } else {

                bi.fldGrpCVh103.setVisibility(View.VISIBLE);
                bi.fldGrpCVh104.setVisibility(View.VISIBLE);
            }
        });


        //h103
        bi.h103.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h103a.getId()) {
                bi.fldGrpCVh104.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVh104);
                bi.fldGrpCVh104.setVisibility(View.GONE);
            }
        });


        //h105
        bi.h105.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h105a.getId()) {
                bi.fldGrpCVh106.setVisibility(View.VISIBLE);
                bi.fldGrpCVh107.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVh106);
                Clear.clearAllFields(bi.fldGrpCVh107);
                bi.fldGrpCVh106.setVisibility(View.GONE);
                bi.fldGrpCVh107.setVisibility(View.GONE);
            }
        });


        //h10698
        bi.h10698.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                bi.h106.setText(null);
                bi.h106.setEnabled(false);
            } else {
                bi.h106.setEnabled(true);
            }
        });

        //h110
        bi.h110.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h110a.getId()) {
                bi.fldGrpCVh111.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVh111);
                bi.fldGrpCVh111.setVisibility(View.GONE);
            }
        });


        //h113
        bi.h113.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h113a.getId()) {
                bi.fldGrpCVh114.setVisibility(View.VISIBLE);
                bi.fldGrpCVh11500.setVisibility(View.VISIBLE);
                bi.fldGrpCVh11501.setVisibility(View.VISIBLE);

            } else {

                Clear.clearAllFields(bi.fldGrpCVh114);
                Clear.clearAllFields(bi.fldGrpCVh11500);
                Clear.clearAllFields(bi.fldGrpCVh11501);

                bi.fldGrpCVh114.setVisibility(View.GONE);
                bi.fldGrpCVh11500.setVisibility(View.GONE);
                bi.fldGrpCVh11501.setVisibility(View.GONE);
            }
        });


        //h116
        bi.h116.setOnCheckedChangeListener((group, checkedId) -> {

            Clear.clearAllFields(bi.fldGrpCVh117);
            Clear.clearAllFields(bi.fldGrpCVh118);
            Clear.clearAllFields(bi.fldGrpCVh119);
            bi.fldGrpCVh117.setVisibility(View.GONE);
            bi.fldGrpCVh118.setVisibility(View.GONE);
            bi.fldGrpCVh119.setVisibility(View.GONE);

            if (checkedId == bi.h116a.getId()) {
                bi.fldGrpCVh117.setVisibility(View.VISIBLE);
                bi.fldGrpCVh118.setVisibility(View.VISIBLE);
                bi.fldGrpCVh119.setVisibility(View.VISIBLE);
            } else if (checkedId == bi.h116b.getId()) {
                bi.fldGrpCVh118.setVisibility(View.VISIBLE);
                bi.fldGrpCVh119.setVisibility(View.VISIBLE);
            }
        });


        //h118
        bi.h118.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h118b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVh119);
                bi.fldGrpCVh119.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVh119.setVisibility(View.VISIBLE);
            }
        });

        //h112
        bi.h112aa.setOnCheckedChangeListener((radioGroup, i) -> {

            if (i == bi.h112b.getId()) {
                Clear.clearAllFields(bi.fldGrpSecH01);
                Clear.clearAllFields(bi.fldGrpSecH02);
            }
        });


    }

    public void BtnContinue() {
        if (formValidation()) {
            try {
                SaveDraft();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (UpdateDB()) {
                finish();
                startActivity(new Intent(this, SectionH102Activity.class));
            } else {
                Toast.makeText(this, "Failed to Update Database!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void BtnEnd() {

        Util.openEndActivity(this);
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long updcount = db.addChild(MainApp.child);
        if (updcount > 0) {
            MainApp.child.set_ID(String.valueOf(updcount));
            MainApp.child.setUID(MainApp.child.getDeviceId() + MainApp.child.get_ID());
            db.updatesChildColumn(ChildContract.ChildTable.COLUMN_UID, MainApp.child.getUID());
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {


        MainApp.child = new ChildContract();
        MainApp.child.set_UUID(MainApp.fc.get_UID());
        MainApp.child.setDeviceId(MainApp.appInfo.getDeviceID());
        MainApp.child.setDevicetagID(MainApp.fc.getDevicetagID());
        MainApp.child.setFormDate(MainApp.fc.getFormDate());
        MainApp.child.setUser(MainApp.fc.getUser());

        JSONObject json = new JSONObject();
        json.put("sysdate", new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));

        /*json.put("h101", bi.h101a.isChecked() ? "1" :
                bi.h101b.isChecked() ? "2" :
                        bi.h10198.isChecked() ? "98" : "0");*/
        json.put("h101aw", bi.h101aw.getText().toString().trim().isEmpty() ? "-1" : bi.h101aw.getText().toString());
        //json.put("h101bm", bi.h101bm.getText().toString());

        json.put("h102", bi.h102a.isChecked() ? "1"
                : bi.h102b.isChecked() ? "2"
                : bi.h102c.isChecked() ? "98"
                : "-1");

        json.put("h103", bi.h103a.isChecked() ? "1"
                : bi.h103b.isChecked() ? "2"
                : bi.h103c.isChecked() ? "98"
                : "-1");

        json.put("h104a", bi.h104a.isChecked() ? "1" : "-1");
        json.put("h104b", bi.h104b.isChecked() ? "2" : "-1");
        json.put("h104c", bi.h104c.isChecked() ? "3" : "-1");
        json.put("h10496", bi.h10496.isChecked() ? "96" : "-1");
        json.put("h10496x", bi.h10496x.getText().toString().trim().isEmpty() ? "-1" : bi.h10496x.getText().toString());

        json.put("h105", bi.h105a.isChecked() ? "1" :
                bi.h105b.isChecked() ? "2" :
                        bi.h105c.isChecked() ? "98" : "-1");

        json.put("h106", bi.h106.getText().toString().trim().isEmpty() ? "-1" : bi.h106.getText().toString());

        json.put("h107", bi.h107a.isChecked() ? "1" :
                bi.h107b.isChecked() ? "2" :
                        bi.h107c.isChecked() ? "3" :
                                bi.h107d.isChecked() ? "4" :
                                        bi.h10798.isChecked() ? "98" : "-1");

        json.put("h108", bi.h108a.isChecked() ? "1" :
                bi.h108b.isChecked() ? "2" :
                        bi.h108c.isChecked() ? "3" :
                                bi.h108d.isChecked() ? "4" :
                                        bi.h10898.isChecked() ? "98" : "-1");

        json.put("h109", bi.h109a.isChecked() ? "1" :
                bi.h109b.isChecked() ? "2" :
                        bi.h109c.isChecked() ? "3" :
                                bi.h109d.isChecked() ? "4" :
                                        bi.h109e.isChecked() ? "5" :
                                                bi.h109f.isChecked() ? "6" :
                                                        bi.h109g.isChecked() ? "9" :
                                                                bi.h109h.isChecked() ? "7" :
                                                                        bi.h109i.isChecked() ? "8" :
                                                                                bi.h109j.isChecked() ? "88" : "-1");

        json.put("h110", bi.h110a.isChecked() ? "1" :
                bi.h110b.isChecked() ? "2" :
                        bi.h11098.isChecked() ? "98" : "-1");

        json.put("h111", bi.h111.getText().toString().trim().isEmpty() ? "-1" : bi.h111.getText().toString());
        json.put("h11202", bi.h112a.isChecked() ? "1" : bi.h112b.isChecked() ? "2" : "-1");
        json.put("h112", bi.h1121.isChecked() ? "1" : bi.h1121.isChecked() ? "2" : bi.h1123.isChecked() ? "3" : "-1");

        json.put("h113", bi.h113a.isChecked() ? "1" :
                bi.h113b.isChecked() ? "2" : "-1");

        json.put("h114", bi.h114a.isChecked() ? "1" :
                bi.h114b.isChecked() ? "2" :
                        bi.h114c.isChecked() ? "3" :
                                bi.h114d.isChecked() ? "4" :
                                        bi.h114e.isChecked() ? "5" :
                                                bi.h114f.isChecked() ? "6" :
                                                        bi.h114g.isChecked() ? "7" :
                                                                bi.h114h.isChecked() ? "8" : "-1");

        json.put("h11502a", bi.h115aa.isChecked() ? "1" : "-1");
        json.put("h11502b", bi.h115ab.isChecked() ? "2" : "-1");
        json.put("h11502c", bi.h115ac.isChecked() ? "3" : "-1");
        json.put("h11502d", bi.h115ad.isChecked() ? "4" : "-1");
        json.put("h11502e", bi.h115ae.isChecked() ? "5" : "-1");
        json.put("h11502f", bi.h115af.isChecked() ? "6" : "-1");
        json.put("h11502g", bi.h115ag.isChecked() ? "7" : "-1");
        json.put("h11502h", bi.h115ah.isChecked() ? "8" : "-1");
        json.put("h11502i", bi.h115ai.isChecked() ? "9" : "-1");

        json.put("h116", bi.h116a.isChecked() ? "1" :
                bi.h116b.isChecked() ? "2" : "-1");

        json.put("h117a", bi.h117a.getText().toString().trim().isEmpty() ? "-1" : bi.h117a.getText().toString());
        json.put("h117b", bi.h117b.getText().toString().trim().isEmpty() ? "-1" : bi.h117b.getText().toString());

        json.put("h118", bi.h118a.isChecked() ? "1" :
                bi.h118b.isChecked() ? "2" : "-1");

        json.put("h119", bi.h119.getText().toString().trim().isEmpty() ? "-1" : bi.h119.getText().toString());

        json.put("h120", bi.h120a.isChecked() ? "1" :
                bi.h120b.isChecked() ? "2" :
                        bi.h120c.isChecked() ? "3" :
                                bi.h120d.isChecked() ? "4" :
                                        bi.h120e.isChecked() ? "5" :
                                                bi.h12098.isChecked() ? "98" : "-1");

        json.put("h10500", bi.h10500a.isChecked() ? "1"
                : bi.h10500b.isChecked() ? "2"
                : "-1");

        json.put("h10501", bi.h10501a.isChecked() ? "1"
                : bi.h10501b.isChecked() ? "2"
                : "-1");

        json.put("h10800", bi.h10800a.isChecked() ? "1"
                : bi.h10800b.isChecked() ? "2"
                : "-1");

        json.put("h10801", bi.h10801a.isChecked() ? "1"
                : bi.h10801b.isChecked() ? "2"
                : "-1");

        json.put("h10900", bi.h10900a.isChecked() ? "1"
                : bi.h10900b.isChecked() ? "2"
                : "-1");

        json.put("h10901", bi.h10901a.isChecked() ? "1"
                : bi.h10901b.isChecked() ? "2"
                : "-1");

        json.put("h11000", bi.h11000a.isChecked() ? "1"
                : bi.h11000b.isChecked() ? "2"
                : "-1");

        json.put("h11001", bi.h11001a.isChecked() ? "1"
                : bi.h11001b.isChecked() ? "2"
                : "-1");

        json.put("h11200", bi.h11200a.isChecked() ? "1"
                : bi.h11200b.isChecked() ? "2"
                : "-1");

        json.put("h11201", bi.h11201a.isChecked() ? "1"
                : bi.h11201b.isChecked() ? "2"
                : "-1");

        json.put("h11300", bi.h11300a.isChecked() ? "1"
                : bi.h11300b.isChecked() ? "2"
                : "-1");

        json.put("h11301", bi.h11301a.isChecked() ? "1"
                : bi.h11301b.isChecked() ? "2"
                : "-1");

        json.put("h11500", bi.h11500a.isChecked() ? "1"
                : bi.h11500b.isChecked() ? "2"
                : "-1");

        json.put("h11501", bi.h11501a.isChecked() ? "1"
                : bi.h11501b.isChecked() ? "2"
                : "-1");

        MainApp.child.setsH1(String.valueOf(json));
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
