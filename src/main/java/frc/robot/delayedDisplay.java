package frc.robot;

public class delayedDisplay {
    //in order to avoid being swamped by console messages
    String named;
    double displayedValue;
    int maxDelay;
    int counter;
    public delayedDisplay(String name, double value, int delay) {
        this.named=name;
        this.displayedValue=value;
        this.maxDelay=delay;
    }
    public void display() {
        if (counter>=maxDelay) {
            counter=0;
            System.out.println(named + displayedValue);
        } else {
            counter++;
        }
    }
}
