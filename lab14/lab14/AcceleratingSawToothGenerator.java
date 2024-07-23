package lab14;

import edu.princeton.cs.introcs.StdAudio;
import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double acceleration;

    public AcceleratingSawToothGenerator(int period, double acceleration) {
        this.period = period;
        this.acceleration = acceleration;
        state = 0;
    }

    @Override
    public double next() {
        state++;
        if (state % period == 0) {
            period = (int) (period * acceleration);
        }
        return normalize(period);
    }


    private double normalize(int period) {
        return (double) (state % period - period / 2) / period * 2;
    }
}
