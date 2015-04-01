package unbound.tipdivider;

import android.support.v7.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private final int ACTION_BAR_BACKGROUND_COLOR = Color.parseColor("#fa6800");
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
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(ACTION_BAR_BACKGROUND_COLOR));

        tips = new Tips(new String[]{"10%", "15%", "18%", "20%"});
        radioGroupTips = (RadioGroup) findViewById(R.id.tips);

        for (int i = 0; i < radioGroupTips.getChildCount(); i++) {
            RadioButton btn = (RadioButton) radioGroupTips.getChildAt(i);

            if (btn != null) {
                String tipPercent = btn.getText().toString();
                tips.addTip(tipPercent);

                if (btn.isChecked()) {
                    tips.setSelected(tipPercent);
                }
            }
        }

        radioGroupTips.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                RadioButton radioButton = (RadioButton) group.findViewById(id);

                if (radioButton == null) {
                    return;
                }

                tips.setSelected(radioButton.getText().toString());
            }
        });

        editTextAmount = (EditText) findViewById(R.id.billAmount);
        textViewTipAmount = (TextView) findViewById(R.id.tipAmount);
        textViewTipAmount.setFocusable(false);

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
}
