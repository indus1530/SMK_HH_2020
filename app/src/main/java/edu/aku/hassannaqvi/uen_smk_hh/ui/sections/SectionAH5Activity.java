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
import edu.aku.hassannaqvi.uen_smk_hh.databinding.ActivitySectionAh5Binding;
import edu.aku.hassannaqvi.uen_smk_hh.utils.JSONUtils;
import edu.aku.hassannaqvi.uen_smk_hh.utils.Util;

public class SectionAH5Activity extends AppCompatActivity {

    ActivitySectionAh5Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_ah5);
        bi.setCallback(this);
        setupSkips();

    }

    private void setupSkips() {

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
                startActivity(new Intent(this, SectionAH6Activity.class));
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
        int updcount = db.updatesAdolsColumn(AdolscentContract.SingleAdolscent.COLUMN_SAH2, MainApp.adolscent.getsAH2());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("ah33a", bi.ah33a.isChecked() ? "1" : "-1");
        json.put("ah33b", bi.ah33b.isChecked() ? "2" : "-1");
        json.put("ah33c", bi.ah33c.isChecked() ? "3" : "-1");
        json.put("ah33d", bi.ah33d.isChecked() ? "98" : "-1");

        json.put("ah34", bi.ah34a.isChecked() ? "1"
                : bi.ah34b.isChecked() ? "2"
                : bi.ah34c.isChecked() ? "98"
                : "-1");

        json.put("ah35a", bi.ah35a.isChecked() ? "1" : "-1");
        json.put("ah35b", bi.ah35b.isChecked() ? "2" : "-1");
        json.put("ah35c", bi.ah35c.isChecked() ? "3" : "-1");
        json.put("ah35d", bi.ah35d.isChecked() ? "4" : "-1");
        json.put("ah35e", bi.ah35e.isChecked() ? "5" : "-1");
        json.put("ah35f", bi.ah35f.isChecked() ? "6" : "-1");
        json.put("ah35g", bi.ah35g.isChecked() ? "7" : "-1");
        json.put("ah35h", bi.ah35h.isChecked() ? "8" : "-1");
        json.put("ah35i", bi.ah35i.isChecked() ? "9" : "-1");
        json.put("ah35j", bi.ah35j.isChecked() ? "10" : "-1");

        json.put("ah36a", bi.ah36a.getText().toString().trim().isEmpty() ? "-1" : bi.ah36a.getText().toString());
        json.put("ah36b", bi.ah36b.isChecked() ? "1" : "-1");

        try {

            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.adolscent.getsAH2()), json);
            MainApp.adolscent.setsAH2(String.valueOf(json_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionAH5);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
