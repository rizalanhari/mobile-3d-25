package org.aplas.basicappx;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private AlertDialog startDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convertBtn = (Button) findViewById(R.id.convertButton);
        inputTxt = (EditText) findViewById(R.id.inputText);
        outputTxt = (EditText) findViewById(R.id.outputText);
        unitOri = (Spinner) findViewById(R.id.oriList);
        unitConv = (Spinner) findViewById(R.id.convList);
        unitType = (RadioGroup) findViewById(R.id.radioGroup);
        roundBox = (CheckBox) findViewById(R.id.chkRounded);
        formBox = (CheckBox) findViewById(R.id.chkFormula);
        imgView = (ImageView) findViewById(R.id.img);
        unitType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                ArrayAdapter<CharSequence> arr;
                inputTxt.setText("0");
                outputTxt.setText("0");
                if (rb.getText().equals("Temperature")) {
                    arr = ArrayAdapter.createFromResource(unitType.getContext(), R.array.tempList, android.R.layout.simple_spinner_item);
                    imgView.setImageResource(R.drawable.temperature);
                    arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    unitOri.setAdapter(arr);
                    unitConv.setAdapter(arr);
                } else if (rb.getText().equals("Distance")) {
                    arr = ArrayAdapter.createFromResource(unitType.getContext(), R.array.distList, android.R.layout.simple_spinner_item);
                    imgView.setImageResource(R.drawable.distance);
                    arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    unitOri.setAdapter(arr);
                    unitConv.setAdapter(arr);
                } else {
                    arr = ArrayAdapter.createFromResource(unitType.getContext(), R.array.weightList, android.R.layout.simple_spinner_item);
                    imgView.setImageResource(R.drawable.weight);
                    arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    unitOri.setAdapter(arr);
                    unitConv.setAdapter(arr);
                }
            }
        });
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doConvert();
            }
        });
        unitOri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doConvert();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        unitConv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                doConvert();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });
        roundBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doConvert();
            }
        });
        formBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    ((ImageView)findViewById(R.id.imgFormula)).setVisibility(View.VISIBLE);
                }else {
                    ((ImageView)findViewById(R.id.imgFormula)).setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        startDialog = new AlertDialog.Builder(MainActivity.this).create();
        startDialog.setTitle("Application started");
        startDialog.setMessage("This app can use to convert any units");
        startDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });
        startDialog.show();
    }

    private Distance dist = new Distance();
    private Weight weight = new Weight();
    private Temperature temp = new Temperature();
    private Button convertBtn;
    private EditText inputTxt;
    private EditText outputTxt;
    private Spinner unitOri;
    private Spinner unitConv;
    private RadioGroup unitType;
    private CheckBox roundBox;
    private CheckBox formBox;
    private ImageView imgView;

    protected double convertUnit(String original, String oriUnit, String convUnit, double value) {
        if (original.equals("Temperature")) {
            value = temp.convert(oriUnit, convUnit, value);
        } else if (original.equals("Distance")) {
            value = dist.convert(oriUnit, convUnit, value);
        } else {
            value = weight.convert(oriUnit, convUnit, value);
        }
        return value;
    }

    protected String strResult(double val, boolean rounded) {
        String number;
        if (rounded) {
            NumberFormat formatter = new DecimalFormat("#0.00");
            number = formatter.format(val);
        } else {
            NumberFormat formatter = new DecimalFormat("#0.00000");
            number = formatter.format(val);
        }
        return number;
    }

    void doConvert() {
        double value = Double.parseDouble(inputTxt.getText().toString());
        RadioButton rb = (RadioButton) findViewById(unitType.getCheckedRadioButtonId());
        double val = convertUnit(rb.getText().toString(), unitOri.getSelectedItem().toString(), unitConv.getSelectedItem().toString(), value);
        String result = strResult(val, roundBox.isChecked());
        outputTxt.setText(result);
    }
}