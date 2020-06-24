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
import edu.aku.hassannaqvi.uen_smk_hh.contracts.AdolscentContract;
import edu.aku.hassannaqvi.uen_smk_hh.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_smk_hh.core.MainApp;
import edu.aku.hassannaqvi.uen_smk_hh.databinding.ActivitySectionAh4Binding;
import edu.aku.hassannaqvi.uen_smk_hh.utils.JSONUtils;
import edu.aku.hassannaqvi.uen_smk_hh.utils.Util;

public class SectionAH4Activity extends AppCompatActivity {

    ActivitySectionAh4Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_ah4);
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
                startActivity(new Intent(this, SectionAH5Activity.class));
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
        int updcount = db.updatesChildColumn(AdolscentContract.SingleAdolscent.COLUMN_SAH2, MainApp.adolscent.getsAH2());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("ah3201", bi.ah3201a.isChecked() ? "1"
                : bi.ah3201b.isChecked() ? "2"
                : bi.ah3201c.isChecked() ? "3"
                : bi.ah3201d.isChecked() ? "4"
                : bi.ah3201e.isChecked() ? "98"
                : "-1");

        json.put("ah3202", bi.ah3202a.isChecked() ? "1"
                : bi.ah3202b.isChecked() ? "2"
                : bi.ah3202c.isChecked() ? "3"
                : bi.ah3202d.isChecked() ? "4"
                : bi.ah3202e.isChecked() ? "98"
                : "-1");

        json.put("ah3203", bi.ah3203a.isChecked() ? "1"
                : bi.ah3203b.isChecked() ? "2"
                : bi.ah3203c.isChecked() ? "3"
                : bi.ah3203d.isChecked() ? "4"
                : bi.ah3203e.isChecked() ? "98"
                : "-1");

        json.put("ah3204", bi.ah3204a.isChecked() ? "1"
                : bi.ah3204b.isChecked() ? "2"
                : bi.ah3204c.isChecked() ? "3"
                : bi.ah3204d.isChecked() ? "4"
                : bi.ah3204e.isChecked() ? "98"
                : "-1");

        json.put("ah3205", bi.ah3205a.isChecked() ? "1"
                : bi.ah3205b.isChecked() ? "2"
                : bi.ah3205c.isChecked() ? "3"
                : bi.ah3205d.isChecked() ? "4"
                : bi.ah3205e.isChecked() ? "98"
                : "-1");

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.adolscent.getsAH2()), json);

            MainApp.adolscent.setsAH2(String.valueOf(json_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionAH4);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
