package edu.aku.hassannaqvi.uen_smk_hh.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import edu.aku.hassannaqvi.uen_smk_hh.R;
import edu.aku.hassannaqvi.uen_smk_hh.contracts.KishMWRAContract;
import edu.aku.hassannaqvi.uen_smk_hh.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_smk_hh.core.MainApp;
import edu.aku.hassannaqvi.uen_smk_hh.databinding.ActivitySectionAh1Binding;
import edu.aku.hassannaqvi.uen_smk_hh.utils.Util;

public class SectionAH1Activity extends AppCompatActivity {

    ActivitySectionAh1Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_ah1);
        bi.setCallback(this);

        setupSkips();

    }

    private void setupSkips() {

        //h102
        /*bi.h102.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId != bi.h102a.getId()) {
                bi.fldGrpCVh103.setVisibility(View.VISIBLE);
                bi.fldGrpCVh104.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVh103);
                Clear.clearAllFields(bi.fldGrpCVh104);
                bi.fldGrpCVh103.setVisibility(View.GONE);
                bi.fldGrpCVh104.setVisibility(View.GONE);
            }
        });*/


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
        int updcount = db.updatesKishMWRAColumn(KishMWRAContract.SingleKishMWRA.COLUMN_SH1, MainApp.kish.getsH1());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("ah1", bi.ah1a.isChecked() ? ""
                : bi.ah1b.isChecked() ? "1"
                : bi.ah1c.isChecked() ? "2"
                : bi.ah1d.isChecked() ? "3"
                : bi.ah1e.isChecked() ? "4"
                : bi.ah1f.isChecked() ? "5"
                : bi.ah1g.isChecked() ? "6"
                : bi.ah1h.isChecked() ? "7"
                : bi.ah1i.isChecked() ? "8"
                : bi.ah1j.isChecked() ? "98"
                : bi.ah1k.isChecked() ? "99"
                : "-1");

        json.put("ah2", bi.ah2a.isChecked() ? "1"
                : bi.ah2b.isChecked() ? "2"
                : "-1");

        json.put("ah3", bi.ah3.getText().toString());

        json.put("ah4", bi.ah4.getText().toString());

        json.put("ah5", bi.ah5a.isChecked() ? "1"
                : bi.ah5b.isChecked() ? "2"
                : "-1");

        json.put("ah6", bi.ah6a.isChecked() ? "1"
                : bi.ah6b.isChecked() ? "2"
                : bi.ah6c.isChecked() ? "3"
                : "-1");

        json.put("ah7x", bi.ah7x.getText().toString());


        MainApp.kish.setsH1(String.valueOf(json));
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionAH1);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
