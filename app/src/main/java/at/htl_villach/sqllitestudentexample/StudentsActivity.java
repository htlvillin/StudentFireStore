package at.htl_villach.sqllitestudentexample;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class StudentsActivity extends AppCompatActivity{

    private DatabaseManager dbManager;

    private ListView listView;

    private SimpleCursorAdapter adapter;
    private Button btnAddStudent;

    final String[] from = new String[] { DatabaseHelper.ID,
            DatabaseHelper.FIRSTNAME, DatabaseHelper.LASTNAME };
    final int[] to = new int[] { R.id.tvId, R.id.tvFirstName, R.id.tvLastName };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_students);

        dbManager = new DatabaseManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_studentrecord, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView tvId = (TextView) view.findViewById(R.id.tvId);
                TextView tvFirstName = (TextView) view.findViewById(R.id.tvFirstName);
                TextView tvLastName = (TextView) view.findViewById(R.id.tvLastName);

                String id = tvId.getText().toString();
                String firstName = tvFirstName.getText().toString();
                String lastName = tvLastName.getText().toString();

              Intent modify_intent = new Intent(getApplicationContext(), UpdateStudentActivity.class);
                modify_intent.putExtra("firstname", firstName);
                modify_intent.putExtra("lastname", lastName);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
        btnAddStudent = findViewById(R.id.btn_addstudent);
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent newStudent = new Intent( StudentsActivity.this, AddStudentActivity.class);
                startActivity(newStudent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     //   getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
