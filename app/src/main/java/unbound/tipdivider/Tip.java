package unbound.tipdivider;

public class Tip {
    private String display;
    private double value;

    public Tip(String display) {
        this.display = display;
        this.value = Double.parseDouble(display.substring(0, display.length() - 1)) / 100;
    }

    public String getDisplay() {
        return this.display;
    }

    public double getValue() {
        return this.value;
    }
}
