package com.example.tugasrahasia;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText inputField1, inputField2, inputField3;
    Button calculateButton;
    TextView resultTextView, resultPrimaTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        inputField1 = findViewById(R.id.inputField1);
        inputField2 = findViewById(R.id.inputField2);
        inputField3 = findViewById(R.id.inputField3);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        resultPrimaTextView = findViewById(R.id.resultPrimaTextView);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOperation = parent.getItemAtPosition(position).toString();
                toggleInputFields(selectedOperation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void toggleInputFields(String selectedOperation) {
        if (selectedOperation.equals("KPK") || selectedOperation.equals("FPB")) {
            inputField2.setVisibility(View.VISIBLE);
            inputField3.setVisibility(View.GONE);
        } else if (selectedOperation.equals("Kubus")) {
            inputField2.setVisibility(View.VISIBLE);
            inputField3.setVisibility(View.VISIBLE);
        } else {
            inputField2.setVisibility(View.GONE);
            inputField3.setVisibility(View.GONE);
        }
    }

    private void calculate() {
        String operation = spinner.getSelectedItem().toString();
        int num1 = Integer.parseInt(inputField1.getText().toString());
//        int num2 = Integer.parseInt(inputField2.getText().toString());
//        int num3 = Integer.parseInt(inputField3.getText().toString());
        int num3, num2;

        String inputText2 = inputField2.getText().toString();
        if (!inputText2.isEmpty()) {
            num2 = Integer.parseInt(inputText2);
        } else {
            num2 = 0; // Nilai default jika input kosong
        }

        String inputText3 = inputField3.getText().toString();
        if (!inputText3.isEmpty()) {
            num3 = Integer.parseInt(inputText3);
        } else {
            num3 = 0; // Nilai default jika input kosong
        }

        String resultPrima = "";
        int result = 0;
        switch (operation) {
            case "KPK":
                result = calculateKPK(num1, num2);
                resultTextView.setVisibility(View.VISIBLE);
                resultPrimaTextView.setVisibility(View.GONE);
                break;
            case "FPB":
                result = calculateFPB(num1, num2);
                resultTextView.setVisibility(View.VISIBLE);
                resultPrimaTextView.setVisibility(View.GONE);
                break;
            case "Prima":
                result = isPrime(num1) ? 1 : 0;
                if (result == 1){
                    resultPrima = "bilangan prima";
                }else {resultPrima = "bukan bilangan prima";};
                resultTextView.setVisibility(View.GONE);
                resultPrimaTextView.setVisibility(View.VISIBLE);
                resultPrimaTextView.setText(resultPrima);
                break;
            case "Kubus":
                result = calculateKubusVolume(num1, num2, num3);
                resultTextView.setVisibility(View.VISIBLE);
                resultPrimaTextView.setVisibility(View.GONE);
                break;
            default:
                // Do nothing
        }
        resultTextView.setText(String.valueOf(result));
    }

    private int calculateKPK(int num1, int num2) {
        int gcd = calculateFPB(num1, num2);
        return (num1 * num2) / gcd;
    }

    private int calculateFPB(int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    private int calculateKubusVolume(int length, int width, int height) {
        return length * width * height;
    }

}
