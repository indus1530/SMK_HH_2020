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
import edu.aku.hassannaqvi.uen_smk_hh.databinding.ActivitySectionAh6Binding;
import edu.aku.hassannaqvi.uen_smk_hh.utils.Util;

public class SectionAH6Activity extends AppCompatActivity {

    ActivitySectionAh6Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_ah6);
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

        json.put("ah3701x", bi.ah3701x.getText().toString());

        json.put("ah3702x", bi.ah3702x.getText().toString());

        json.put("ah3703x", bi.ah3703x.getText().toString());

        json.put("ah3704x", bi.ah3704x.getText().toString());

        json.put("ah3705x", bi.ah3705x.getText().toString());

        json.put("ah3706x", bi.ah3706x.getText().toString());

        json.put("ah37aa", bi.ah37aa.getText().toString());

        json.put("ah37ab", bi.ah37ab.getText().toString());

        json.put("ah37ac", bi.ah37ac.getText().toString());

        json.put("ah38", bi.ah38a.isChecked() ? "1"
                : bi.ah38b.isChecked() ? "2"
                : "-1");

        json.put("ah39x", bi.ah39x.getText().toString());

        json.put("ah40x", bi.ah40x.getText().toString());

        json.put("ah40aai", bi.ah40aai.getText().toString());

        json.put("ah41", bi.ah41a.isChecked() ? "1"
                : bi.ah41b.isChecked() ? "2"
                : bi.ah41c.isChecked() ? "3"
                : "-1");

        json.put("ah42", bi.ah42a.isChecked() ? "1"
                : bi.ah42b.isChecked() ? "2"
                : bi.ah42c.isChecked() ? "3"
                : "-1");

        json.put("ah43", bi.ah43a.isChecked() ? "1"
                : bi.ah43b.isChecked() ? "2"
                : "-1");

        json.put("ah45", bi.ah45a.isChecked() ? "1"
                : bi.ah45b.isChecked() ? "2"
                : bi.ah45c.isChecked() ? "98"
                : "-1");

        json.put("ah46", bi.ah46a.isChecked() ? "1"
                : bi.ah46b.isChecked() ? "2"
                : bi.ah46c.isChecked() ? "98"
                : "-1");

        json.put("ah47d", bi.ah47a.isChecked() ? "1"
                : bi.ah47b.isChecked() ? "2"
                : bi.ah47c.isChecked() ? "98"
                : "-1");

        json.put("ah48", bi.ah48a.isChecked() ? "1"
                : bi.ah48b.isChecked() ? "2"
                : bi.ah48c.isChecked() ? "3"
                : bi.ah48d.isChecked() ? "4"
                : "-1");


        MainApp.kish.setsH1(String.valueOf(json));
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionAH6);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
