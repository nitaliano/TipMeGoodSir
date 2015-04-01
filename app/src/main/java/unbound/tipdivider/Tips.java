package unbound.tipdivider;

import android.provider.MediaStore;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Tips implements RadioGroup.OnCheckedChangeListener {
    private Map<String, Tip> tips = new HashMap<String, Tip>();
    private Tip selectedTip;

    public Tips(String[] tipArray) {
      for (int i = 0; i < tipArray.length; i++) {
          String tipDisplay = tipArray[i];

          tips.put(tipDisplay, new Tip(tipDisplay));

          if (i == 0) {
              this.selectedTip = tips.get(tipDisplay);
          }
      }
    }

    public Tip getSelectedTip() {
        return this.selectedTip;
    }

    private Tip getTip(String key) {
        return tips.get(key);
    }

    public String calculateTip(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(amount * this.getSelectedTip().getValue()).concat("%");
    }

    public void onCheckedChanged(RadioGroup group, int id) {
        RadioButton radioButton = (RadioButton) group.findViewById(id);
        if (radioButton == null) {
            return;
        }

        Tip tip = this.tips.get(radioButton.getText());
        if (tip == null) {
            return;
        }

        this.selectedTip = tip;
    }
}
