package kanafactory.letterspractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

public class Randomize extends AppCompatActivity {

    DBManage db;
    List<String> letters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomize);
        db = DBManage.getInstance(getApplicationContext());
        letters = db.getAll();
        TextView tv = findViewById(R.id.textView4);
        int rand = new Random().nextInt(letters.size());
        tv.setText(letters.get(rand));
    }

    public void next(View view){
        String current = ((TextView)findViewById(R.id.textView4)).getText().toString();
        String next = letters.get(new Random().nextInt(letters.size()));
        while(next.equals(current) && letters.size() < 2){
            next = letters.get(new Random(letters.size()).nextInt());
        }
        ((TextView)findViewById(R.id.textView4)).setText(next);
    }

}
