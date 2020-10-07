package edu.aku.hassannaqvi.uen_smk_hh.ui.sections;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.validatorcrawler.aliazaz.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.aku.hassannaqvi.uen_smk_hh.CONSTANTS;
import edu.aku.hassannaqvi.uen_smk_hh.R;
import edu.aku.hassannaqvi.uen_smk_hh.contracts.FamilyMembersContract;
import edu.aku.hassannaqvi.uen_smk_hh.contracts.MWRAContract;
import edu.aku.hassannaqvi.uen_smk_hh.core.DatabaseHelper;
import edu.aku.hassannaqvi.uen_smk_hh.core.MainApp;
import edu.aku.hassannaqvi.uen_smk_hh.databinding.ActivitySectionE1Binding;
import edu.aku.hassannaqvi.uen_smk_hh.utils.Util;

import static edu.aku.hassannaqvi.uen_smk_hh.ui.list_activity.FamilyMembersListActivity.mainVModel;

public class SectionE1Activity extends AppCompatActivity implements Util.EndSecAActivity {

    ActivitySectionE1Binding bi;
    int position;
    FamilyMembersContract selMWRA;
    int child_size = 0;
    private MWRAContract mwra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_e1);
        bi.setCallback(this);

        setUIComponent();
    }

    private void setUIComponent() {

        List<String> womenLst = new ArrayList<String>() {
            {
                add("....");
                addAll(MainApp.pragnantWoman.getSecond());
            }
        };

        bi.womanSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, womenLst));

        bi.womanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                if (position == 0) return;
                selMWRA = mainVModel.getMemberInfo(MainApp.pragnantWoman.getFirst().get(bi.womanSpinner.getSelectedItemPosition() - 1));
                MainApp.selectedMWRAChildLst = mainVModel.getChildrenOfMother(selMWRA.getSerialno());
                child_size = MainApp.selectedMWRAChildLst.getFirst().size();
                if (child_size > 0) {
                    bi.e101a.setChecked(true);
                    bi.e101b.setEnabled(false);
                } else {
                    bi.e101.clearCheck();
                    bi.e101b.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        bi.e102.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!charSequence.toString().isEmpty()) {
                    MainApp.noOfPragnencies = Integer.parseInt(charSequence.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void BtnContinue() {
        if (formValidation()) {
            if (bi.e101a.isChecked() && (Integer.parseInt(bi.e102.getText().toString()) < child_size))
                Util.openWarningActivity(this, "In Roaster you've reported\n" + child_size + " Children\n" + "Now you reporting " + bi.e102.getText().toString() + " Children");
            else {
                callingContinue();
            }
        }
    }

    private void callingContinue() {
        try {
            SaveDraft();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (UpdateDB()) {
            Intent next;
            if (bi.e101a.isChecked()) {
                next = new Intent(SectionE1Activity.this, SectionE2Activity.class);
                next.putExtra(CONSTANTS.MWRA_INFO, mwra);
            } else {
                if (MainApp.pragnantWoman.getFirst().size() > 0) {
                    next = new Intent(SectionE1Activity.this, SectionE1Activity.class);
                } else {
                    Class<?> nextClass = MainApp.selectedKishAdols != null ? SectionAH1Activity.class : SectionMActivity.class;
                    next = new Intent(this, MainApp.selectedKishMWRA != null ? SectionFActivity.class : nextClass);
                }
            }
            finish();
            startActivity(next);
        }
    }

    private boolean UpdateDB() {

        DatabaseHelper db = MainApp.appInfo.getDbHelper();
        long rowID = db.addMWRA(mwra);
        if (rowID > 0) {
            mwra.set_ID(String.valueOf(rowID));
            mwra.setUID(mwra.getDeviceId() + mwra.get_ID());
            db.updateMWRAUID(mwra);
            return true;
        } else {
            Toast.makeText(this, "Updating Database... ERROR!", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    private void SaveDraft() throws JSONException {

        mwra = new MWRAContract();
        mwra.set_UUID(MainApp.fc.get_UID());
        mwra.setDeviceId(MainApp.appInfo.getDeviceID());
        mwra.setFormDate(MainApp.fc.getFormDate());
        mwra.setUser(MainApp.fc.getUser());
        mwra.setDevicetagID(MainApp.fc.getDevicetagID());

        JSONObject json = new JSONObject();
        mwra.setFmuid(selMWRA.getUid());
        mwra.setFm_serial(selMWRA.getSerialno());
        json.put("fm_uid", selMWRA.getUid());
        json.put("fm_serial", selMWRA.getSerialno());
        json.put("hhno", MainApp.fc.getHhno());
        json.put("cluster_no", MainApp.fc.getClusterCode());
        json.put("_luid", MainApp.fc.getLuid());
        json.put("appversion", MainApp.appInfo.getAppVersion());
        json.put("sysdate", new SimpleDateFormat("dd-MM-yy HH:mm").format(new Date().getTime()));
        // xml e101
        json.put("mwra_name", bi.womanSpinner.getSelectedItem().toString());
        mwra.setMwra_name(bi.womanSpinner.getSelectedItem().toString());

        json.put("e001", bi.e10100.getText().toString().trim().isEmpty() ? "-1" : bi.e10100.getText().toString());

        json.put("e002", bi.e101a.isChecked() ? "1"
                : bi.e101b.isChecked() ? "2"
                : "-1");

        json.put("e003", bi.e102.getText().toString().trim().isEmpty() ? "-1" : bi.e102.getText().toString());

        json.put("e004", bi.e10201.getText().toString().trim().isEmpty() ? "-1" : bi.e10201.getText().toString());

        json.put("e005", bi.e102aa.isChecked() ? "1"
                : bi.e102ab.isChecked() ? "2"
                : "-1");


        // Deleting item in list
        MainApp.pragnantWoman.getFirst().remove(position - 1);
        MainApp.pragnantWoman.getSecond().remove(position - 1);

        mwra.setsE1(String.valueOf(json));

    }

    private boolean formValidation() {

        return Validator.emptyCheckingContainer(this, bi.fldGrpSectionE1);

        /*if(bi.fldGrpCVe102.getVisibility() == View.VISIBLE && bi.e102.getVisibility() == View.VISIBLE && !bi.e102.getText().toString().trim().isEmpty()) {
            if (Integer.parseInt(bi.e10100.getText().toString()) > Integer.parseInt(bi.e10201.getText().toString())) {
                Toast.makeText(this, "Age at marriage should not be greater than age at first pregnancy", Toast.LENGTH_SHORT).show();
                return false;
            }
        }*/
    }

    public void BtnEnd() {
        Util.openEndActivity(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void endSecAActivity(boolean flag) {
        callingContinue();
    }
}
