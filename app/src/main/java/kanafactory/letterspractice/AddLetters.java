package kanafactory.letterspractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddLetters extends AppCompatActivity {

    DBManage db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_letters);
        db = DBManage.getInstance(this.getApplicationContext());

        String letters = "";
        List<String> temp = db.getAll();
        for (String e : temp) letters += (e.toUpperCase() + ",");
        if (letters.length() > 0)
            letters = letters.substring(0, letters.length() - 1);
        TextView tv = findViewById(R.id.textView3);
        tv.setText(letters);
    }

    public void add(View view) {
        EditText editText = findViewById(R.id.editText);
        String text = editText.getText().toString();
        if (text.equals(""))
            Toast.makeText(this, "Please enter a letter", Toast.LENGTH_SHORT).show();
        else {
            if (db.add(text)) {
                Toast.makeText(this, "Letter added successfully", Toast.LENGTH_SHORT).show();
                TextView tv = findViewById(R.id.textView3);
                tv.append("," + text.toUpperCase());
            }
            else
                Toast.makeText(this, "Letter Wasn't added", Toast.LENGTH_SHORT).show();
        }
    }

}
