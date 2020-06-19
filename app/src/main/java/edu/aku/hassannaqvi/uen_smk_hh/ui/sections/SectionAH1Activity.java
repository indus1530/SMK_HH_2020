package edu.aku.hassannaqvi.uen_smk_hh.ui.sections;

import android.content.Intent;
import android.os.Bundle;
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

        bi.ah1.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah1a.getId()) {
                Clear.clearAllFields(bi.fldGrpSecAH101);
            }
        }));

        bi.ah2.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah2b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVah3);
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
                startActivity(new Intent(this, SectionAH2Activity.class));
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

        json.put("ah1", bi.ah1a.isChecked() ? "11"
                : bi.ah1b.isChecked() ? "12"
                : bi.ah1c.isChecked() ? "13"
                : bi.ah1d.isChecked() ? "14"
                : bi.ah1e.isChecked() ? "15"
                : bi.ah1f.isChecked() ? "16"
                : bi.ah1g.isChecked() ? "17"
                : bi.ah1h.isChecked() ? "18"
                : bi.ah1i.isChecked() ? "19"
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

        json.put("ah7a", bi.ah7a.isChecked() ? "1" : "-1");
        json.put("ah7b", bi.ah7b.isChecked() ? "2" : "-1");
        json.put("ah7c", bi.ah7c.isChecked() ? "3" : "-1");
        json.put("ah7d", bi.ah7d.isChecked() ? "4" : "-1");
        json.put("ah7e", bi.ah7e.isChecked() ? "5" : "-1");
        json.put("ah7f", bi.ah7f.isChecked() ? "6" : "-1");
        json.put("ah7g", bi.ah7g.isChecked() ? "7" : "-1");
        json.put("ah7h", bi.ah7h.isChecked() ? "8" : "-1");
        json.put("ah796", bi.ah796.isChecked() ? "96" : "-1");
        json.put("ah796x", bi.ah796x.getText().toString());


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
