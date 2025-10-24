package colosseum.flashlight;

public class FlashlightString {
    private final int magic;

    public FlashlightString() {
        this(0x100);
    }

    public FlashlightString(int magic) {
        this.magic = magic;
    }

    public String decode(byte[] q, byte k1, byte k2) {
        int i = 0;
        final int len = q.length;
        StringBuilder sb = new StringBuilder();
        while (i < len) {
            if (i + 1 < len) {
                final byte x = q[i];
                final byte y = q[i + 1];
                final int k = (i * k1 + k2) % magic;
                final int l = ((i + 1) * k1 + k2) % magic;
                sb.append((char) (y ^ (byte) k));
                sb.append((char) (x ^ (byte) l));
                i += 2;
            } else {
                final int k = (i * k1 + k2) % magic;
                sb.append((char) (q[i] ^ (byte) k));
                i += 1;
            }
        }
        return sb.toString();
    }

    public byte[] encode(String s, byte k1, byte k2) {
        final byte[] bytes = s.getBytes();
        final int len = bytes.length;
        byte[] result = new byte[len];
        int v = 0;
        for (int i = 0; i < len / 2; i++) {
            final int j1 = i << 1;
            final int j2 = j1 + 1;
            final byte k_j1 = (byte) ((j1 * k1 + k2) % magic);
            final byte k_j2 = (byte) ((j2 * k1 + k2) % magic);
            result[v++] = (byte) (bytes[j2] ^ k_j2);
            result[v++] = (byte) (bytes[j1] ^ k_j1);
        }
        if (len % 2 == 1) {
            final int j = len - 1;
            final byte k_j = (byte) ((j * k1 + k2) % magic);
            result[v] = (byte) (bytes[j] ^ k_j);
        }
        return result;
    }
}
