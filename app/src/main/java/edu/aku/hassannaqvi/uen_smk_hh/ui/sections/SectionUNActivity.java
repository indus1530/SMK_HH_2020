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

import edu.aku.hassannaqvi.uen_smk_hh.R;
import edu.aku.hassannaqvi.uen_smk_hh.contracts.KishMWRAContract;
import edu.aku.hassannaqvi.uen_smk_hh.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_smk_hh.core.MainApp;
import edu.aku.hassannaqvi.uen_smk_hh.databinding.ActivitySectionUnBinding;
import edu.aku.hassannaqvi.uen_smk_hh.utils.Util;

public class SectionUNActivity extends AppCompatActivity {

    ActivitySectionUnBinding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_un);
        bi.setCallback(this);
        setlistener();

    }

    private void setlistener() {

        bi.un01.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.un01a.getId()) {
                Clear.clearAllFields(bi.fldGrpCVun02);
            }
        }));

        bi.un04.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.un04d.getId()) {
                Clear.clearAllFields(bi.fldGrpCVun05);
            }
        }));

        bi.un05.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.un05a.getId() || i == bi.un05c.getId()) {
                Clear.clearAllFields(bi.fldGrpCVun06);
            }
        }));

        bi.un03.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.un03b.getId() || i == bi.un03d.getId()) {
                Clear.clearAllFields(bi.fldGrpCVun04);
                Clear.clearAllFields(bi.fldGrpCVun05);
                Clear.clearAllFields(bi.fldGrpCVun06);
                bi.fldGrpCVun04.setVisibility(View.GONE);
                bi.fldGrpCVun05.setVisibility(View.GONE);
                bi.fldGrpCVun06.setVisibility(View.GONE);
            } else if (i == bi.un03c.getId()) {
                bi.fldGrpCVun06.setVisibility(View.VISIBLE);
            } else {
                bi.fldGrpCVun04.setVisibility(View.VISIBLE);
                bi.fldGrpCVun05.setVisibility(View.VISIBLE);
                bi.fldGrpCVun06.setVisibility(View.VISIBLE);
            }
        }));

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
                startActivity(new Intent(this, SectionLActivity.class));
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
        int updcount = db.updatesKishMWRAColumn(KishMWRAContract.SingleKishMWRA.COLUMN_SUN, MainApp.kish.getsUN());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("un01", bi.un01a.isChecked() ? "1"
                : bi.un01b.isChecked() ? "2"
                : "-1");

        json.put("un02", bi.un02a.isChecked() ? "1"
                : bi.un02b.isChecked() ? "2"
                : "-1");

        json.put("un03", bi.un03a.isChecked() ? "1"
                : bi.un03b.isChecked() ? "2"
                : bi.un03c.isChecked() ? "3"
                : bi.un03d.isChecked() ? "4"
                : "-1");

        json.put("un04", bi.un04a.isChecked() ? "1"
                : bi.un04b.isChecked() ? "2"
                : bi.un04c.isChecked() ? "3"
                : bi.un04d.isChecked() ? "4"
                : "-1");
        json.put("un04ax", bi.un04ax.getText().toString().trim().isEmpty() ? "-1" : bi.un04ax.getText().toString());
        json.put("un04bx", bi.un04bx.getText().toString().trim().isEmpty() ? "-1" : bi.un04bx.getText().toString());

        json.put("un05", bi.un05a.isChecked() ? "1"
                : bi.un05b.isChecked() ? "2"
                : bi.un05c.isChecked() ? "3"
                : "-1");

        json.put("un06", bi.un06a.isChecked() ? "1"
                : bi.un06b.isChecked() ? "2"
                : bi.un06c.isChecked() ? "3"
                : bi.un06d.isChecked() ? "4"
                : bi.un06e.isChecked() ? "5"
                : bi.un06f.isChecked() ? "6"
                : bi.un06g.isChecked() ? "7"
                : bi.un06h.isChecked() ? "8"
                : bi.un0696.isChecked() ? "96"
                : "-1");
        json.put("un0696x", bi.un0696x.getText().toString().trim().isEmpty() ? "-1" : bi.un0696x.getText().toString());

        json.put("un07", bi.un07a.isChecked() ? "1"
                : bi.un07b.isChecked() ? "2"
                : bi.un07c.isChecked() ? "3"
                : bi.un07d.isChecked() ? "4"
                : bi.un07e.isChecked() ? "5"
                : "-1");

        MainApp.kish.setsUN(String.valueOf(json));
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionUN);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}