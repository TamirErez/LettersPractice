package kanafactory.letterspractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    DBManage db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        db = DBManage.getInstance(getApplicationContext());
    }

    public void startAddLetter(View view){
        Intent intent = new Intent(MainMenu.this,AddLetters.class);
        startActivity(intent);
    }

    public void startRemoveLetter(View view){
        Intent intent = new Intent(MainMenu.this,RemoveLetters.class);
        startActivity(intent);
    }

    public void startRandomize(View view){
        Intent intent = new Intent(MainMenu.this,Randomize.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        db.close();
        super.onDestroy();
    }

}
