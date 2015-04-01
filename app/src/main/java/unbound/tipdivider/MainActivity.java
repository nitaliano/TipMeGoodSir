package unbound.tipdivider;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends ActionBarActivity {
    private RadioGroup radioGroupTips;
    private Button btnSubmit;
    private EditText editTextAmount;
    private TextView textViewTipAmount;
    private Tips tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        tips = new Tips(new String[]{"10%", "15%", "18%", "20%"});
        radioGroupTips = (RadioGroup) findViewById(R.id.tips);
        radioGroupTips.setOnCheckedChangeListener(tips);

        editTextAmount = (EditText) findViewById(R.id.billAmount);
        textViewTipAmount = (TextView) findViewById(R.id.tipAmount);

        btnSubmit = (Button) findViewById(R.id.btnTip);
        btnSubmit.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    textViewTipAmount.setText(tips.calculateTip(Double.parseDouble(editTextAmount.getText().toString())));
                } catch (NumberFormatException e) {
                    Toast.makeText(context, "Please Enter a Valid Amount!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
