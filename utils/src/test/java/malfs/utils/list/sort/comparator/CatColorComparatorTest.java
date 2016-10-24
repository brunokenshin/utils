package malfs.utils.list.sort.comparator;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import malfs.utils.list.sort.model.Cat;

public class CatColorComparatorTest {

    private List<Cat> cats;
    
    @Before
    public void setup() {
        cats = asList(new Cat(1, 10.3, 12, "black"), 
                      new Cat(2, 8.88, 6, "black"), 
                      new Cat(3, 8.25, 6, "black"),
                      new Cat(4, 10.0, 5, "white"), 
                      new Cat(5, 9.20, 6, "white"), 
                      new Cat(6, 7.80, 7, "brown"));
    }
    
    @Test
    public void sort_cats_by_color() {
        List<Cat> sortedCats = cats.stream()
                                   .sorted(new CatColorComparator())
                                   .collect(Collectors.toList());

        assertEquals(Integer.valueOf(1), sortedCats.get(0).id);
        assertEquals(Integer.valueOf(2), sortedCats.get(1).id);
        assertEquals(Integer.valueOf(3), sortedCats.get(2).id);
        assertEquals(Integer.valueOf(6), sortedCats.get(3).id);
        assertEquals(Integer.valueOf(4), sortedCats.get(4).id);
        assertEquals(Integer.valueOf(5), sortedCats.get(5).id);
    }
    
}
