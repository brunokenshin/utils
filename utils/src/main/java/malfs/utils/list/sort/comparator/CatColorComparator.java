package malfs.utils.list.sort.comparator;


import java.util.Comparator;

import malfs.utils.list.sort.model.Cat;


public class CatColorComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat gato1, Cat gato2) {
        return gato1.color.compareTo(gato2.color);
    }
}