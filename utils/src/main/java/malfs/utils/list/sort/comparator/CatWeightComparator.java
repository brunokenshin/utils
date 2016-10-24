package malfs.utils.list.sort.comparator;


import java.util.Comparator;

import malfs.utils.list.sort.model.Cat;


public class CatWeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat cat1, Cat cat2) {
        return cat1.weight.compareTo(cat2.weight);
    }
}
