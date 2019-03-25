package at.htl_villach.sqllitestudentexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudentActivity extends AppCompatActivity {

    private Button btnAddRecord;
    private EditText etFirstName;
    private EditText etLastName;

    private DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        setTitle("Add Record");

        setContentView(R.layout.activity_add_student);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);

        btnAddRecord = (Button) findViewById(R.id.add_record);
        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstName = etFirstName.getText().toString();
                final String lastName = etLastName.getText().toString();

                dbManager.insert(firstName, lastName);

                Intent main = new Intent(AddStudentActivity.this, StudentsActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
            }
        });

        dbManager = new DatabaseManager(this);
        dbManager.open();

    }
}
