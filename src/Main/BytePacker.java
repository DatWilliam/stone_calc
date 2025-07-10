package Main;
/*
    store two numbers as a byte
    a = hit, b = miss
    for single numbers b will be used
    for percent a is the first number after . and b the second
 */
public class BytePacker {
    public static byte pack(int a, int b) {
        return (byte) ((a << 4) | (b & 0xF));
    }

    public static int unpackA(byte packed) {
        return (packed >> 4) & 0xF;
    }

    public static int unpackB(byte packed) {
        return packed & 0xF;
    }

    public static int unpackSum(byte packed) {
        return unpackA(packed) + unpackB(packed);
    }

    public static byte incrementA(byte packed) {
        return (byte) (packed + (1 << 4));
    }

    public static byte incrementB(byte packed) {
        return (byte) (packed + 1);
    }

    public static double toDouble(byte packed) {
        int high = (packed >> 4) & 0xF;
        return high / 10.0 + 0.05;  // z.B. 6 → 0.65
    }

    public static float toFloat(byte packed) {
        int high = (packed >> 4) & 0xF;
        return high / 10.0f + 0.05f;
    }

    public static byte increasePercent(byte packed) {
        // is high nibble < 7 ? -> increase by 10
        return (packed & 0xF0) < 0x70 ? (byte)(packed + 0x10) : packed;
    }

    public static byte decreasePercent(byte packed) {
        // is high nibble > 2 ? -> decrease by 10
        return (packed & 0xF0) > 0x20 ? (byte)(packed - 0x10) : packed;
    }

}
