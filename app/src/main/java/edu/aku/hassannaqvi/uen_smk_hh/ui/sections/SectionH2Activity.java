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
import edu.aku.hassannaqvi.uen_smk_hh.databinding.ActivitySectionH2Binding;
import edu.aku.hassannaqvi.uen_smk_hh.utils.Util;

public class SectionH2Activity extends AppCompatActivity {

    ActivitySectionH2Binding bi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_h2);
        bi.setCallback(this);
        setupSkips();
        setCoronaFields();

    }

    private void setCoronaFields() {

        if (!MainApp.selectedKishMWRA.isCoronaCase()) {
            bi.h204d.setVisibility(View.GONE);

            bi.fldGrpCVh2091.setVisibility(View.GONE);

            bi.h211j.setVisibility(View.GONE);

            bi.h214e.setVisibility(View.GONE);

            bi.fldGrpCVh2161.setVisibility(View.GONE);

        }
    }


    private void setupSkips() {

        if (MainApp.G102.equals("1")) {
            Clear.clearAllFields(bi.fldGrpCVh201);
            bi.fldGrpCVh201.setVisibility(View.GONE);
        } else {
            bi.fldGrpCVh201.setVisibility(View.VISIBLE);
        }

        //h202
        bi.h202.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h202a.getId()) {
                bi.fldGrpCVh203.setVisibility(View.VISIBLE);
                bi.fldGrpCVh204.setVisibility(View.VISIBLE);
                bi.fldGrpCVh205.setVisibility(View.VISIBLE);
                bi.fldGrpCVh205aa.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVh203);
                Clear.clearAllFields(bi.fldGrpCVh204);
                Clear.clearAllFields(bi.fldGrpCVh205);
                Clear.clearAllFields(bi.fldGrpCVh205aa);
                bi.fldGrpCVh203.setVisibility(View.GONE);
                bi.fldGrpCVh204.setVisibility(View.GONE);
                bi.fldGrpCVh205.setVisibility(View.GONE);
                bi.fldGrpCVh205aa.setVisibility(View.GONE);
            }
        });

        //h203
        bi.h203.setOnCheckedChangeListener((group, checkedId) -> {
            Clear.clearAllFields(bi.h203x);
        });

        //h205a96
        bi.h205a96.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!isChecked)
                Clear.clearAllFields(bi.h205a96x);
        });

        //h206
        bi.h206.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h206a.getId()) {
                bi.fldGrpCVh207.setVisibility(View.VISIBLE);
                bi.fldGrpCVh208.setVisibility(View.VISIBLE);
                bi.fldGrpCVh20801.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVh207);
                Clear.clearAllFields(bi.fldGrpCVh208);
                Clear.clearAllFields(bi.fldGrpCVh20801);
                bi.fldGrpCVh207.setVisibility(View.GONE);
                bi.fldGrpCVh208.setVisibility(View.GONE);
                bi.fldGrpCVh20801.setVisibility(View.GONE);
            }
        });


        //h209
        bi.h209.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h209a.getId()) {
                bi.fldGrpCVh210.setVisibility(View.VISIBLE);
                bi.fldGrpCVh211.setVisibility(View.VISIBLE);
                bi.fldGrpCVh212.setVisibility(View.VISIBLE);
                bi.fldGrpCVh213.setVisibility(View.VISIBLE);
                Clear.clearAllFields(bi.fldGrpCVh2091);
                bi.fldGrpCVh2091.setVisibility(View.GONE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVh210);
                Clear.clearAllFields(bi.fldGrpCVh211);
                Clear.clearAllFields(bi.fldGrpCVh212);
                Clear.clearAllFields(bi.fldGrpCVh213);
                bi.fldGrpCVh210.setVisibility(View.GONE);
                bi.fldGrpCVh211.setVisibility(View.GONE);
                bi.fldGrpCVh212.setVisibility(View.GONE);
                bi.fldGrpCVh213.setVisibility(View.GONE);
                if (MainApp.selectedKishMWRA.isCoronaCase())
                    bi.fldGrpCVh2091.setVisibility(View.VISIBLE);
            }
        });

        bi.h2091c.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                bi.h2091check03.setVisibility(View.VISIBLE);
            else {
                Clear.clearAllFields(bi.h2091check03);
                bi.h2091check03.setVisibility(View.GONE);
            }
        });

        //h20798
        bi.h20798.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.h207check, false);
            } else {
                Clear.clearAllFields(bi.h207check, true);
            }
        });

        //h2080298
        bi.h2080298.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                Clear.clearAllFields(bi.h20802check, false);
            } else {
                Clear.clearAllFields(bi.h20802check, true);
            }
        });

        //h216
        bi.h216.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h216b.getId()) {
                if (MainApp.selectedKishMWRA.isCoronaCase())
                    bi.fldGrpCVh2161.setVisibility(View.VISIBLE);
            } else {
                Clear.clearAllFields(bi.fldGrpCVh2161);
                bi.fldGrpCVh2161.setVisibility(View.GONE);

            }
        });

        //h217
        bi.h217.setOnCheckedChangeListener((radiogroup, i) -> Clear.clearAllFields(bi.fldGrpsecH201));


        //h220
        bi.h220.setOnCheckedChangeListener((group, checkedId) -> {

            if (checkedId == bi.h220a.getId()) {
                Clear.clearAllFields(bi.fldGrpCVh222);
                bi.fldGrpCVh222.setVisibility(View.GONE);
            } else {
                bi.fldGrpCVh222.setVisibility(View.VISIBLE);
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

                /*Class<?> nextActivity = SectionJActivity.class;
                if (MainApp.selectedKishAdols != null) nextActivity = SectionAH1Activity.class;
                startActivity(new Intent(this, nextActivity));*/

                finish();
                startActivity(new Intent(this, SectionKActivity.class));

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
        int updcount = db.updatesKishMWRAColumn(KishMWRAContract.SingleKishMWRA.COLUMN_SH2, MainApp.kish.getsH2());
        if (updcount == 1) {
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void SaveDraft() throws JSONException {

        JSONObject json = new JSONObject();

        json.put("h201",
                bi.h201a.isChecked() ? "1"
                        : bi.h201b.isChecked() ? "2"
                        : bi.h201c.isChecked() ? "3"
                        : "-1");

        json.put("h201h", bi.h201h.getText().toString().trim().isEmpty() ? "-1" : bi.h201h.getText().toString());
        json.put("h201d", bi.h201d.getText().toString().trim().isEmpty() ? "-1" : bi.h201d.getText().toString());
        json.put("h201w", bi.h201w.getText().toString().trim().isEmpty() ? "-1" : bi.h201w.getText().toString());

        json.put("h20200", bi.h20200a.isChecked() ? "1"
                : bi.h20200b.isChecked() ? "2"
                : "-1");

        json.put("h20201", bi.h20201a.isChecked() ? "1"
                : bi.h20201b.isChecked() ? "2"
                : "-1");

        json.put("h202", bi.h202a.isChecked() ? "1" :
                bi.h202b.isChecked() ? "2" :
                        bi.h202c.isChecked() ? "19" : "-1");

        json.put("h203", bi.h203a.isChecked() ? "1" :
                bi.h203b.isChecked() ? "2" :
                        bi.h203c.isChecked() ? "3" : "-1");

        json.put("h203x", bi.h203x.getText().toString().trim().isEmpty() ? "-1" : bi.h203x.getText().toString());

        json.put("h204", bi.h204a.isChecked() ? "1" :
                bi.h204b.isChecked() ? "2" :
                        bi.h204c.isChecked() ? "3" :
                                bi.h204d.isChecked() ? "4" :
                                        bi.h204e.isChecked() ? "5" :
                                                bi.h204f.isChecked() ? "6" :
                                                        bi.h204g.isChecked() ? "7" :
                                                                bi.h204h.isChecked() ? "8" :
                                                                        bi.h204i.isChecked() ? "9" :
                                                                                bi.h204j.isChecked() ? "10" :
                                                                                        bi.h20496.isChecked() ? "96" : "-1");


        json.put("h205", bi.h205a.isChecked() ? "1" :
                bi.h205b.isChecked() ? "2" :
                        bi.h205c.isChecked() ? "3" :
                                bi.h205d.isChecked() ? "4" :
                                        bi.h205e.isChecked() ? "5" : "-1");


        json.put("h205aa", bi.h205aa.isChecked() ? "1" : "-1");
        json.put("h205ab", bi.h205ab.isChecked() ? "2" : "-1");
        json.put("h205ac", bi.h205ac.isChecked() ? "3" : "-1");
        json.put("h205ad", bi.h205ad.isChecked() ? "4" : "-1");
        json.put("h205ae", bi.h205ae.isChecked() ? "5" : "-1");
        json.put("h205af", bi.h205af.isChecked() ? "6" : "-1");
        json.put("h205ag", bi.h205ag.isChecked() ? "7" : "-1");
        json.put("h205ah", bi.h205ah.isChecked() ? "8" : "-1");
        json.put("h205ai", bi.h205ai.isChecked() ? "9" : "-1");
        json.put("h205a96", bi.h205a96.isChecked() ? "96" : "-1");

        json.put("h20596x", bi.h21896x.getText().toString().trim().isEmpty() ? "-1" : bi.h21896x.getText().toString());

        json.put("h206", bi.h206a.isChecked() ? "1" :
                bi.h206b.isChecked() ? "2" : "-1");

        json.put("h207a", bi.h207a.isChecked() ? "1" : "-1");
        json.put("h207b", bi.h207b.isChecked() ? "2" : "-1");
        json.put("h207c", bi.h207c.isChecked() ? "3" : "-1");
        json.put("h207d", bi.h207d.isChecked() ? "4" : "-1");
        json.put("h207e", bi.h207e.isChecked() ? "5" : "-1");
        json.put("h207f", bi.h207f.isChecked() ? "6" : "-1");
        json.put("h207g", bi.h207g.isChecked() ? "7" : "-1");
        json.put("h207h", bi.h207h.isChecked() ? "8" : "-1");
        json.put("h207i", bi.h20798.isChecked() ? "98" : "-1");

        json.put("h208a", bi.h208a.isChecked() ? "1" : "-1");
        json.put("h208b", bi.h208b.isChecked() ? "2" : "-1");
        json.put("h208c", bi.h208c.isChecked() ? "3" : "-1");
        json.put("h208d", bi.h208d.isChecked() ? "4" : "-1");
        json.put("h208e", bi.h208e.isChecked() ? "5" : "-1");
        json.put("h208f", bi.h208f.isChecked() ? "6" : "-1");

        json.put("h20801", bi.h20801a.isChecked() ? "1"
                : bi.h20801b.isChecked() ? "2"
                : "-1");

        json.put("h20802a", bi.h20802a.isChecked() ? "1" : "-1");
        json.put("h20802b", bi.h20802b.isChecked() ? "2" : "-1");
        json.put("h20802c", bi.h20802c.isChecked() ? "3" : "-1");
        json.put("h20802d", bi.h20802d.isChecked() ? "4" : "-1");
        json.put("h20802e", bi.h20802e.isChecked() ? "5" : "-1");
        json.put("h20802f", bi.h20802f.isChecked() ? "6" : "-1");
        json.put("h20802g", bi.h20802g.isChecked() ? "7" : "-1");
        json.put("h20802h", bi.h20802h.isChecked() ? "8" : "-1");
        json.put("h20802i", bi.h2080298.isChecked() ? "98" : "-1");

        json.put("h20803", bi.h20803a.isChecked() ? "1"
                : bi.h20803b.isChecked() ? "2"
                : "-1");

        json.put("h209", bi.h209a.isChecked() ? "1" :
                bi.h209b.isChecked() ? "2" : "-1");

        json.put("h209aa", bi.h2091a.isChecked() ? "1" : "-1");
        json.put("h209ab", bi.h2091b.isChecked() ? "2" : "-1");
        json.put("h209ac", bi.h2091c.isChecked() ? "3" : "-1");
        json.put("h209ad", bi.h2091d.isChecked() ? "3a" : "-1");
        json.put("h209ae", bi.h2091e.isChecked() ? "3b" : "-1");
        json.put("h209af", bi.h2091f.isChecked() ? "3c" : "-1");
        json.put("h209ag", bi.h2091g.isChecked() ? "3d" : "-1");

        json.put("h210", bi.h210a.isChecked() ? "1" :
                bi.h210b.isChecked() ? "2" :
                        bi.h210c.isChecked() ? "3" :
                                bi.h21098.isChecked() ? "98" : "-1");

        json.put("h211a", bi.h211a.isChecked() ? "1" : "-1");
        json.put("h211b", bi.h211b.isChecked() ? "2" : "-1");
        json.put("h211c", bi.h211c.isChecked() ? "3" : "-1");
        json.put("h211d", bi.h211d.isChecked() ? "4" : "-1");
        json.put("h211e", bi.h211e.isChecked() ? "5" : "-1");
        json.put("h211f", bi.h211f.isChecked() ? "6" : "-1");
        json.put("h211g", bi.h211g.isChecked() ? "7" : "-1");
        json.put("h211h", bi.h211h.isChecked() ? "8" : "-1");
        json.put("h211i", bi.h211i.isChecked() ? "9" : "-1");
        json.put("h211j", bi.h211j.isChecked() ? "10" : "-1");

        json.put("h212", bi.h212.getText().toString().trim().isEmpty() ? "-1" : bi.h212.getText().toString());

        json.put("h213", bi.h213a.isChecked() ? "1"
                : bi.h213b.isChecked() ? "2"
                : bi.h213c.isChecked() ? "3"
                : "-1");

        json.put("h214", bi.h214a.isChecked() ? "1"
                : bi.h214b.isChecked() ? "2"
                : bi.h214c.isChecked() ? "3"
                : bi.h214d.isChecked() ? "4"
                : bi.h214e.isChecked() ? "5"
                : bi.h214f.isChecked() ? "6"
                : "-1");

        json.put("h215", bi.h215a.isChecked() ? "1"
                : bi.h215b.isChecked() ? "2"
                : bi.h215c.isChecked() ? "3"
                : bi.h215d.isChecked() ? "4"
                : bi.h215e.isChecked() ? "5"
                : bi.h21598.isChecked() ? "98"
                : "-1");

        json.put("h216", bi.h216a.isChecked() ? "1"
                : bi.h216b.isChecked() ? "2"
                : bi.h216c.isChecked() ? "3"
                : "-1");

        json.put("h216a", bi.h2161a.isChecked() ? "1"
                : bi.h2161b.isChecked() ? "2"
                : bi.h2161c.isChecked() ? "3"
                : bi.h2161d.isChecked() ? "4"
                : bi.h2161e.isChecked() ? "5"
                : bi.h2161f.isChecked() ? "6"
                : "-1");

        json.put("h217", bi.h217a.isChecked() ? "1" :
                bi.h217b.isChecked() ? "2" :
                        bi.h21798.isChecked() ? "98" : "-1");
        

        json.put("h218", bi.h218a.isChecked() ? "1" :
                bi.h218b.isChecked() ? "2" :
                        bi.h218c.isChecked() ? "3" :
                                bi.h218d.isChecked() ? "4" :
                                        bi.h218e.isChecked() ? "5" :
                                                bi.h218f.isChecked() ? "6" :
                                                        bi.h21896.isChecked() ? "96" : "-1");

        json.put("h21896x", bi.h21896x.getText().toString().trim().isEmpty() ? "-1" : bi.h21896x.getText().toString());

        json.put("h219", bi.h219a.isChecked() ? "1" :
                bi.h219b.isChecked() ? "2" :
                        bi.h219c.isChecked() ? "3" :
                                bi.h219d.isChecked() ? "4" :
                                        bi.h219e.isChecked() ? "5" :
                                                bi.h219f.isChecked() ? "6" :
                                                        bi.h21996.isChecked() ? "96" : "-1");

        json.put("h21996x", bi.h21996x.getText().toString().trim().isEmpty() ? "-1" : bi.h21996x.getText().toString());

        json.put("h220", bi.h220a.isChecked() ? "1" :
                bi.h220b.isChecked() ? "2" :
                        bi.h220c.isChecked() ? "3" : "-1");

        json.put("h221", bi.h221a.isChecked() ? "1" :
                bi.h221b.isChecked() ? "2" :
                        bi.h221c.isChecked() ? "3" :
                                bi.h221d.isChecked() ? "4" :
                                        bi.h22196.isChecked() ? "96" : "-1");

        json.put("h22196x", bi.h22196x.getText().toString().trim().isEmpty() ? "-1" : bi.h22196x.getText().toString());

        json.put("h222", bi.h222a.isChecked() ? "1" :
                bi.h222b.isChecked() ? "2" :
                        bi.h222c.isChecked() ? "3" :
                                bi.h222d.isChecked() ? "4" : "-1");

        MainApp.kish.setsH2(String.valueOf(json));
    }

    private boolean formValidation() {
        return Validator.emptyCheckingContainer(this, bi.GrpName);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

}
