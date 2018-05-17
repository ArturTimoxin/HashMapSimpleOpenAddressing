import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Random;
import org.junit.Test;

public class HashMap_Simple_OpenAddressingTest {

    /*
    A test to verify the key's match and the values for the standard size hashmap (in my case 37)
    and the hashmap of the specified size. The size of the hashmap must be the prime number.
    */
    @Test
    public void standartTest() {

        final HashMap_Simple_OpenAddressing defaultMap = new HashMap_Simple_OpenAddressing();
        defaultMap.put(4,19);
        defaultMap.put(7,100);

        assertEquals(19, defaultMap.get(4), 0);
        assertEquals(100, defaultMap.get(7), 0);
        assertNull(defaultMap.get(3));

        final HashMap_Simple_OpenAddressing map = new HashMap_Simple_OpenAddressing(43);
        map.put(2, 1);
        map.put(8, 22);
        map.put(9, 2);

        assertEquals(1, map.get(2), 0);
        assertEquals(22, map.get(8), 0);
        assertEquals(2, map.get(9), 0);
        assertNull(map.get(11));
    }

    /*
    randomTest determines the correctness of my HashMap implementation comparing it with the library one.
    Two hash tables are created (a library hashmap and created by me).
    It is filled with random numbers of keys (0; 1000000) and values (0; 100) of tables (to exclude the repeatability of the key value).
    My hashmap are filled by 50% (Boot factor controls the fullness of the table to save performance).
    Then the key/values of the two tables are compared.
    */
    @Test
    public void randomTest() {
        final HashMap_Simple_OpenAddressing map = new HashMap_Simple_OpenAddressing(997);
        //The size is not specified because the JDK hashmap expands automatically.
        final HashMap<Integer, Long> mapJDK = new HashMap<Integer, Long>();
        Random random = new Random();
        for (int i = 0; i < 997 * 0.5; i++) {
            int k = random.nextInt(1000000);
            long v = (long) random.nextInt(100);
            map.put(k, v);
            mapJDK.put(k, v);
        }
        System.out.println("Size map: " + map.size());
    }

    @Test
    public void rewriteTest() {
        final HashMap_Simple_OpenAddressing map = new HashMap_Simple_OpenAddressing(29);
        map.put(2, 89);
        map.put(10, 62);

        assertEquals(89, map.get(2), 0);
        assertEquals(62, map.get(10), 0);

        map.put(2, 43);
        map.put(10, 122);
        assertEquals(43, map.get(2), 0);
        assertEquals(122, map.get(10), 0);
    }

    //Test the size of the table with overwriting.

    @Test
    public void testSize() {
        final HashMap_Simple_OpenAddressing map = new HashMap_Simple_OpenAddressing();
        map.put(6, 32);
        map.put(2, 44);
        assertEquals(2, map.size(), 0);

        map.put(12, 45);
        assertEquals(3, map.size(), 0);

        map.put(6, 788);
        map.put(2, 445);
        assertEquals(3, map.size(), 0);
    }
}