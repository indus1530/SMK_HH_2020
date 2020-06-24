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
import edu.aku.hassannaqvi.uen_smk_hh.contracts.AdolscentContract;
import edu.aku.hassannaqvi.uen_smk_hh.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_smk_hh.core.MainApp;
import edu.aku.hassannaqvi.uen_smk_hh.databinding.ActivitySectionAh7Binding;
import edu.aku.hassannaqvi.uen_smk_hh.ui.other.EndingActivity;
import edu.aku.hassannaqvi.uen_smk_hh.utils.JSONUtils;
import edu.aku.hassannaqvi.uen_smk_hh.utils.Util;

public class SectionAH7Activity extends AppCompatActivity {

    ActivitySectionAh7Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_ah7);
        bi.setCallback(this);

        setupSkips();

    }

    private void setupSkips() {


        bi.ah57b.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.fldGrpSecAH701);
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
                startActivity(new Intent(this, bi.ah51e.isChecked() ? EndingActivity.class : SectionKActivity.class).putExtra("complete", true));
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
        int updcount = db.updatesChildColumn(AdolscentContract.SingleAdolscent.COLUMN_SAH3, MainApp.adolscent.getsAH3());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("ah49a", bi.ah49a.isChecked() ? "1" : "-1");
        json.put("ah49b", bi.ah49b.isChecked() ? "2" : "-1");
        json.put("ah49c", bi.ah49c.isChecked() ? "98" : "-1");

        json.put("ah50", bi.ah50a.isChecked() ? "1"
                : bi.ah50b.isChecked() ? "2"
                : bi.ah50c.isChecked() ? "98"
                : "-1");

        json.put("ah50aaa", bi.ah50aaa.getText().toString());

        json.put("ah50aab", bi.ah50aab.getText().toString());

        json.put("ah51", bi.ah51a.isChecked() ? "1"
                : bi.ah51b.isChecked() ? "2"
                : bi.ah51c.isChecked() ? "3"
                : bi.ah51d.isChecked() ? "4"
                : bi.ah51e.isChecked() ? "5"
                : "-1");

        json.put("ah52", bi.ah52.getText().toString());

        json.put("ah53", bi.ah53a.isChecked() ? "1"
                : bi.ah53b.isChecked() ? "2"
                : "-1");

        json.put("ah54", bi.ah54a.isChecked() ? "1"
                : bi.ah54b.isChecked() ? "98"
                : "-1");
        json.put("ah54ax", bi.ah54ax.getText().toString());

        json.put("ah55", bi.ah55a.isChecked() ? "1"
                : bi.ah55b.isChecked() ? "2"
                : bi.ah55c.isChecked() ? "3"
                : bi.ah55d.isChecked() ? "7"
                : "-1");

        json.put("ah56a", bi.ah56a.getText().toString());

//        json.put("ah57", "-1");

        json.put("ah57a", bi.ah57a.getText().toString());
        json.put("ah57b", bi.ah49a.isChecked() ? "1" : "-1");

//        json.put("ah57b", "-1");

        json.put("ah58", bi.ah58.getText().toString());

        json.put("ah59", bi.ah59.getText().toString());

        json.put("ah60", bi.ah60.getText().toString());

        try {
            JSONObject json_merge = JSONUtils.mergeJSONObjects(new JSONObject(MainApp.adolscent.getsAH3()), json);

            MainApp.adolscent.setsAH3(String.valueOf(json_merge));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionAH7);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
