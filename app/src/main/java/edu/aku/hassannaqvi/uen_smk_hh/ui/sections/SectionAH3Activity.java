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
import edu.aku.hassannaqvi.uen_smk_hh.databinding.ActivitySectionAh3Binding;
import edu.aku.hassannaqvi.uen_smk_hh.utils.Util;

public class SectionAH3Activity extends AppCompatActivity {

    ActivitySectionAh3Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_ah3);
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

        bi.ah16.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah16b.getId()) {
                Clear.clearAllFields(bi.fldGrpSecAH301);
            }
        }));

        bi.ah19.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah19b.getId()) {
                Clear.clearAllFields(bi.fldGrpSecAH302);
            }
        }));

        bi.ah22.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.ah22a.getId()) {
                Clear.clearAllFields(bi.fldGrpSecAH303);
            }
        }));

        bi.ah24011.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah24011a.getId()) {
                Clear.clearAllFields(bi.fldGrpSecAH304);
            }
        }));

        bi.ah25.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah25b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVah26);
            }
        }));

        bi.ah27.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i == bi.ah27b.getId()) {
                Clear.clearAllFields(bi.fldGrpCVah28);
            }
        }));

        bi.ah3003.setOnCheckedChangeListener(((radioGroup, i) -> {
            if (i != bi.ah3003a.getId()) {
                Clear.clearAllFields(bi.fldGrpCVah31);
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
                startActivity(new Intent(this, SectionAH4Activity.class));
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

        json.put("ah14", bi.ah14a.isChecked() ? "1"
                : bi.ah14b.isChecked() ? "2"
                : bi.ah14c.isChecked() ? "3"
                : bi.ah14d.isChecked() ? "4"
                : "-1");

        json.put("ah15", bi.ah15a.isChecked() ? "1"
                : bi.ah15b.isChecked() ? "2"
                : bi.ah15c.isChecked() ? "3"
                : bi.ah15d.isChecked() ? "4"
                : bi.ah15e.isChecked() ? "5"
                : "-1");

        json.put("ah16", bi.ah16a.isChecked() ? "1"
                : bi.ah16b.isChecked() ? "2"
                : bi.ah16c.isChecked() ? "3"
                : bi.ah16d.isChecked() ? "4"
                : "-1");

        json.put("ah17", bi.ah17a.isChecked() ? "1"
                : bi.ah17b.isChecked() ? "2"
                : "-1");

        json.put("ah18", bi.ah18a.isChecked() ? "1"
                : bi.ah18b.isChecked() ? "2"
                : bi.ah18c.isChecked() ? "3"
                : bi.ah18d.isChecked() ? "4"
                : bi.ah18e.isChecked() ? "5"
                : bi.ah18f.isChecked() ? "6"
                : bi.ah18g.isChecked() ? "7"
                : "-1");

        json.put("ah19", bi.ah19a.isChecked() ? "1"
                : bi.ah19b.isChecked() ? "2"
                : "-1");

        json.put("ah20", bi.ah20a.isChecked() ? "1"
                : bi.ah20b.isChecked() ? "2"
                : "-1");

        json.put("ah21", bi.ah21a.isChecked() ? "1"
                : bi.ah21b.isChecked() ? "2"
                : bi.ah21c.isChecked() ? "3"
                : bi.ah21d.isChecked() ? "4"
                : bi.ah21e.isChecked() ? "5"
                : bi.ah21f.isChecked() ? "6"
                : bi.ah21g.isChecked() ? "7"
                : "-1");

        json.put("ah22", bi.ah22a.isChecked() ? "1"
                : bi.ah22b.isChecked() ? "2"
                : bi.ah22c.isChecked() ? "3"
                : "-1");

        json.put("ah2201", bi.ah2201a.isChecked() ? "1"
                : bi.ah2201b.isChecked() ? "2"
                : bi.ah2201c.isChecked() ? "3"
                : "-1");

        json.put("ah2202", bi.ah2202a.isChecked() ? "1"
                : bi.ah2202b.isChecked() ? "2"
                : bi.ah2202c.isChecked() ? "3"
                : "-1");

        json.put("ah2203", bi.ah2203a.isChecked() ? "1"
                : bi.ah2203b.isChecked() ? "2"
                : bi.ah2203c.isChecked() ? "3"
                : "-1");

        json.put("ah2204", bi.ah2204a.isChecked() ? "1"
                : bi.ah2204b.isChecked() ? "2"
                : bi.ah2204c.isChecked() ? "3"
                : "-1");
        json.put("ah2296x", bi.ah2296x.getText().toString());

//        json.put("ah23", bi.ah23.getText().toString());

        json.put("ah23a", bi.ah23a.getText().toString());

        json.put("ah23b", bi.ah23b.getText().toString());

        json.put("ah23c", bi.ah23c.getText().toString());

        json.put("ah2401", bi.ah2401a.isChecked() ? "1"
                : bi.ah2401b.isChecked() ? "2"
                : "-1");

        json.put("ah2402", bi.ah2402a.isChecked() ? "1"
                : bi.ah2402b.isChecked() ? "2"
                : "-1");

        json.put("ah2403", bi.ah2403a.isChecked() ? "1"
                : bi.ah2403b.isChecked() ? "2"
                : "-1");

        json.put("ah2404", bi.ah2404a.isChecked() ? "1"
                : bi.ah2404b.isChecked() ? "2"
                : "-1");

        json.put("ah2405", bi.ah2405a.isChecked() ? "1"
                : bi.ah2405b.isChecked() ? "2"
                : "-1");

        json.put("ah2406", bi.ah2406a.isChecked() ? "1"
                : bi.ah2406b.isChecked() ? "2"
                : "-1");

        json.put("ah2407", bi.ah2407a.isChecked() ? "1"
                : bi.ah2407b.isChecked() ? "2"
                : "-1");

        json.put("ah2408", bi.ah2408a.isChecked() ? "1"
                : bi.ah2408b.isChecked() ? "2"
                : "-1");

        json.put("ah2409", bi.ah2409a.isChecked() ? "1"
                : bi.ah2409b.isChecked() ? "2"
                : "-1");

        json.put("ah24010", bi.ah24010a.isChecked() ? "1"
                : bi.ah24010b.isChecked() ? "2"
                : "-1");

        json.put("ah24011", bi.ah24011a.isChecked() ? "1"
                : bi.ah24011b.isChecked() ? "2"
                : "-1");

        json.put("ah24011", "-1");

        json.put("ah25", bi.ah25a.isChecked() ? "1"
                : bi.ah26b.isChecked() ? "2"
                : "-1");

        json.put("ah26", bi.ah26a.isChecked() ? "1"
                : bi.ah26b.isChecked() ? "2"
                : bi.ah26c.isChecked() ? "3"
                : "-1");

        json.put("ah27", bi.ah27a.isChecked() ? "1"
                : bi.ah27b.isChecked() ? "2"
                : "-1");

        json.put("ah28", bi.ah28a.isChecked() ? "1"
                : bi.ah28b.isChecked() ? "2"
                : bi.ah28c.isChecked() ? "3"
                : "-1");

        json.put("ah29", bi.ah29a.isChecked() ? "1"
                : bi.ah29b.isChecked() ? "2"
                : bi.ah29c.isChecked() ? "3"
                : bi.ah29d.isChecked() ? "4"
                : "-1");

        json.put("ah3001", bi.ah3001a.isChecked() ? "1"
                : bi.ah3001b.isChecked() ? "2"
                : bi.ah3001c.isChecked() ? "3"
                : "-1");

        json.put("ah31", bi.ah31a.isChecked() ? "1"
                : bi.ah31b.isChecked() ? "2"
                : bi.ah31c.isChecked() ? "3"
                : bi.ah31d.isChecked() ? "4"
                : bi.ah31e.isChecked() ? "5"
                : "-1");


        MainApp.kish.setsH1(String.valueOf(json));
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionAH3);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }
}
