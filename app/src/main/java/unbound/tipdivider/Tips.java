package unbound.tipdivider;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Tips {
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

    public boolean setSelected(String key) {
        Tip tip = this.tips.get(key);

        if (tip == null) {
            return false;
        }

        this.selectedTip = tip;
        return true;
    }
}
