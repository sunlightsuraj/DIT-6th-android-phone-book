package np.info.suraj.phonebook2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import np.info.suraj.phonebook2.model.Person;
import np.info.suraj.phonebook2.sqlite.PersonHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView all_contact_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        all_contact_list = (ListView) findViewById(R.id.listView);

        listContent();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listContent();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == R.id.addContactMenu) {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void listContent() {
        final ArrayList<Person> allPerson;
        try (PersonHelper personHelper = new PersonHelper(getApplicationContext())) {
            allPerson = personHelper.getAllPerson();
        }

        CustomListAdapter adapter = new CustomListAdapter(this, allPerson);
        all_contact_list.setAdapter(adapter);

        all_contact_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person p = allPerson.get(position);
                Intent intent = new Intent(MainActivity.this, ContactDetail.class);
                intent.putExtra("personId", p.getId());
                startActivity(intent);
            }
        });

    }
}