package com.example.android_validate;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import android.view.View;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner type;
    EditText bedRoom;
    RadioGroup furniture;
    EditText price;
    EditText nameReporter;
    EditText created_at;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.button_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arrValidate = {};
                List<String> errors = new ArrayList<>(Arrays.asList((arrValidate)));

                String[] sampleTypes = {"Flat", "House", "Bungalow"};
                String[] sampleFurnitures = {"Furnished", "Unfurnished", "PartFurnished"};

                List<String> types = new ArrayList<>(Arrays.asList(sampleTypes));
                List<String> furnitures = new ArrayList<>(Arrays.asList(sampleFurnitures));

                type = findViewById(R.id.type);
                String valType = type.getSelectedItem().toString();

                bedRoom = findViewById(R.id.bedRoom);
                String valBedRoom = bedRoom.getText().toString();

                furniture = findViewById(R.id.furniture);
                String valFurniture;
                if (furniture.getCheckedRadioButtonId() != -1) {
                    valFurniture = ((RadioButton)findViewById(furniture.getCheckedRadioButtonId())).getText().toString();
                } else {
                    valFurniture = "";
                }

                price = findViewById(R.id.priceByMonth);
                String valPriceByMonth = price.getText().toString();

                nameReporter = findViewById(R.id.reporterName);
                String valReporterName = nameReporter.getText().toString();

                created_at = findViewById(R.id.createdAt);
                String valCreatedAt = created_at.getText().toString();

                if (!(isNumber(valBedRoom))) {
                    errors.add("bed room");
                }

                if (!(types.contains(valType))) {
                    errors.add("type");
                }

                if (!(furnitures.contains(valFurniture))) {
                    errors.add("Furniture");
                }

                if (!(isNumber(valPriceByMonth))) {
                    errors.add("Price");
                }

                if (isBlank(valReporterName)) {
                    errors.add("Name of the reporter");
                }

                if (!(formatDate(valCreatedAt))) {
                    errors.add("Created_at");
                }

                if (errors.size() > 0) {
                    Toast toast = Toast.makeText(MainActivity.this, createMessageContent(errors), Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Created", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

    public final static String createMessageContent(List errors) {
        String message = "";
        for (int error = 0; error < errors.size(); error++) {
            message += (errors.get(error) + ", ");
        }

        return "Invalid data: " + message ", ";
    }

    public final static boolean isBlank(String val) //valid for reporter name
    {
        return Pattern.compile("^\\s*$").matcher(val).matches();
    }

    public final static boolean isDate(String val) // DD-MM-YYYY
    {
        return Pattern.compile("^\\d{1,2}[\\/.]\\d{1,2}[\\/.]\\d{4}$").matcher(val).matches();
    }

    public final static boolean isNumber(String val) // valid for price and bedroom
    {
        return Pattern.compile("^\\d+$").matcher(val).matches();
    }
}