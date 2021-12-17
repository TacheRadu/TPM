package lists;

public interface List<T> {
    boolean add(T item);
    boolean remove(T item);
    boolean contains(T item);
}
