package unbound.tipdivider;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Tips {
    private Map<String, Tip> tips;
    private Tip selectedTip;

    public Tips(String[] tipArray) {
      tips = new HashMap<String, Tip>();
    }

    public Tip getSelectedTip() {
        return this.selectedTip;
    }

    public boolean addTip(String key) {
        if (tips.containsKey(key)) {
            return false;
        }
        tips.put(key, new Tip(key));
        return true;
    }

    public Tip getTip(String key) {
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
