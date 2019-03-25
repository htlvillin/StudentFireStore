package at.htl_villach.sqllitestudentexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateStudentActivity extends AppCompatActivity {

    private EditText etFirstName;
    private Button btnUpdate, btnDelete;
    private EditText etLastName;

    private long id;

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Update Record");

        setContentView(R.layout.activity_update_student);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        etFirstName = (EditText) findViewById(R.id.subject_edittext);
        etLastName = (EditText) findViewById(R.id.description_edittext);

        btnUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String str_id = intent.getStringExtra("id");
        String firstName = intent.getStringExtra("firstname");
        String lastName = intent.getStringExtra("lastname");

        id = Long.parseLong(str_id);
        etFirstName.setText(firstName);
        etLastName.setText(lastName);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();

                dbManager.update(id, firstName, lastName);
                returnToActivity();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.delete(id);
                returnToActivity();
            }

        });
    }
    private void returnToActivity() {
        Intent home_intent = new Intent(getApplicationContext(), StudentsActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }

  }
