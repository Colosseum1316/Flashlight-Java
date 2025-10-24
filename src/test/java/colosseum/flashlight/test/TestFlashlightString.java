package colosseum.flashlight.test;

import colosseum.flashlight.FlashlightString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestFlashlightString {
    @Test
    void testMagic100Encode() {
        FlashlightString flashlightString = new FlashlightString(0x100);
        Assertions.assertArrayEquals(
                new byte[]{77, 95, 38, 85, 76, 52, (byte) 225, 10, (byte) 220, (byte) 237, (byte) 243, (byte) 165},
                flashlightString.encode("Hello world!", (byte) 0x11, (byte) 0x17)
        );
        Assertions.assertArrayEquals(
                new byte[]{67, 94, 42, 90, 70, 57, (byte) 233, 1, (byte) 202, (byte) 228, (byte) 231, (byte) 210},
                flashlightString.encode("Hello world!", (byte) 0x10, (byte) 0x16)
        );
        Assertions.assertArrayEquals(
                new byte[]{77, 95, 38, 85, 76, 52, (byte) 225, 10, (byte) 220, (byte) 237, (byte) 165},
                flashlightString.encode("Hello world", (byte) 0x11, (byte) 0x17)
        );
        Assertions.assertArrayEquals(
                new byte[]{67, 94, 42, 90, 70, 57, (byte) 233, 1, (byte) 202, (byte) 228, (byte) 210},
                flashlightString.encode("Hello world", (byte) 0x10, (byte) 0x16)
        );
    }

    @Test
    void testMagic100Decode() {
        FlashlightString flashlightString = new FlashlightString(0x100);
        Assertions.assertEquals("Hello world!", flashlightString.decode(new byte[]{77, 95, 38, 85, 76, 52, (byte) 225, 10, (byte) 220, (byte) 237, (byte) 243, (byte) 165}, (byte) 0x11, (byte) 0x17));
        Assertions.assertEquals("Hello world!", flashlightString.decode(new byte[]{67, 94, 42, 90, 70, 57, (byte) 233, 1, (byte) 202, (byte) 228, (byte) 231, (byte) 210}, (byte) 0x10, (byte) 0x16));
        Assertions.assertEquals("Hello world", flashlightString.decode(new byte[]{77, 95, 38, 85, 76, 52, (byte) 225, 10, (byte) 220, (byte) 237, (byte) 165}, (byte) 0x11, (byte) 0x17));
        Assertions.assertEquals("Hello world", flashlightString.decode(new byte[]{67, 94, 42, 90, 70, 57, (byte) 233, 1, (byte) 202, (byte) 228, (byte) 210}, (byte) 0x10, (byte) 0x16));
    }
}
