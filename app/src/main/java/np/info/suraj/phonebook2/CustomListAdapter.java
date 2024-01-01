package np.info.suraj.phonebook2;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import np.info.suraj.phonebook2.model.Person;

public class CustomListAdapter extends ArrayAdapter<Person> {
    private final Activity context;
    private final ArrayList<Person> persons;
    TextView name;
    ImageView icon;

    public CustomListAdapter(Activity context, ArrayList<Person> persons) {
        super(context, R.layout.contact_list_item, persons);
        this.persons = persons;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = (View) inflater.inflate(R.layout.contact_list_item, null, true);

        name = (TextView) rowView.findViewById(R.id.name);
        icon = (ImageView) rowView.findViewById(R.id.icon);

        Person person = persons.get(position);

        name.setText(String.format("%s %s", person.getFirstName(), person.getLastName()));
        if(!person.getImagePath().equals("")) {
            icon.setImageBitmap(ThumbnailUtils
                    .extractThumbnail(BitmapFactory.decodeFile(person.getImagePath()),
                            60, 60));
        } else {
            icon.setImageResource(R.drawable.user);
        }

        return rowView;
    }
}
