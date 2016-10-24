package malfs.utils.list.sort.comparator;


import java.util.Comparator;

import malfs.utils.list.sort.model.Cat;


public class CatAgeComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat cat1, Cat cat2) {
        return cat1.age.compareTo(cat2.age);
    }
}