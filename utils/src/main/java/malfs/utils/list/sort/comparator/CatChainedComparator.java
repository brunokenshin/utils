package malfs.utils.list.sort.comparator;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import malfs.utils.list.sort.model.Cat;

public class CatChainedComparator implements Comparator<Cat> {

    private List<Comparator<Cat>> listComparators;

    @SafeVarargs
    public CatChainedComparator(Comparator<Cat>... comparators) {
        this.listComparators = Arrays.asList(comparators);
    }

    @Override
    public int compare(Cat cat1, Cat cat2) {
        for (Comparator<Cat> comparator : listComparators) {
            int result = comparator.compare(cat1, cat2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

}
