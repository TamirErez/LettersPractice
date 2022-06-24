
package kanafactory.letterspractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class RemoveLetters extends AppCompatActivity {

    DBManage db;
    List<String> items;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_letters);

        db = DBManage.getInstance(getApplicationContext());
        items = db.getAll();
        Spinner spinner = findViewById(R.id.spinner);
        spinnerAdapter = new ArrayAdapter<> (this,R.layout.support_simple_spinner_dropdown_item,items);
        spinner.setAdapter(spinnerAdapter);
    }

    public void remove(View view){
        Spinner spinner = findViewById(R.id.spinner);
        if(spinner.getCount() == 0){
            Toast.makeText(this,"No item to delete", Toast.LENGTH_SHORT).show();
            return;
        }
        String text = spinner.getSelectedItem().toString();
        if(db.remove(text)) {
            Toast.makeText(this, "Item deleted successfully", Toast.LENGTH_SHORT).show();
            items.remove(text);
            spinnerAdapter.notifyDataSetChanged();
        }
        else
            Toast.makeText(this, "Item wasn't deleted", Toast.LENGTH_SHORT).show();
    }
}
