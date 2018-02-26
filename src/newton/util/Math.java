package newton.util;

public class Math {

    /**
     * Constrains a value to a range [min, max]. If value is between {@code min}
     * and {@code max} or equal to {@code min} or {@code max}, the value is part
     * of the range and is returned. If the value is outside the range, this
     * method returns {@code min}, if {@code value} is smaller than {@code min},
     * or {@code max}, if the {@code value} is greater than {@code max}.
     *
     * @param min The minimum of the range
     * @param max The maximum of the range
     * @param value A value that is going to be clamped
     * @return The clamped value
     */
    public static float clamp(float min, float max, float value) {
        return Float.max(min, Float.min(value, max));
    }

}
