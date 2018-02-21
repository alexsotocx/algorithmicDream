import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DoubleLinkedList<T> implements Iterable<DoubleLinkedList.Link<T>>{
  private Link<T> first;
  private Link<T> last;
  private int size = 0;

  public Link<T> append(T data) {
    Link<T> link = new Link<>(data);
    if (isEmpty()) {
      first = last = link;
    } else {
      last.next = link;
      link.prev = last;
      last = link;
    }
    size++;
    return link;
  }

  public Link<T> prepend(T data) {
    Link<T> link = new Link<>(data);
    if (isEmpty()) {
      first = last = link;
    } else {
      first.prev = link;
      link.next = first;
      first = link;
    }
    size++;
    return link;
  }

  public Link<T> find(T data) {
    for (Link<T> link : this) {
      if (link.data.equals(data)){
        return link;
      }
    }
    return null;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public Iterator<Link<T>> iterator() {
    return new LinkIterator(first);
  }

  class LinkIterator implements Iterator<Link<T>> {
    private Link<T> current;
    private boolean isFirst = true;

    public LinkIterator(Link<T> link) {
      this.current = first;
    }

    @Override
    public boolean hasNext() {
      return (current != null && isFirst) || (current != null && current.next != null);
    }

    @Override
    public Link<T> next() {
      Link<T> temp = current;
      if (isFirst) {
        isFirst = false;
        current = current.next;
        return temp;
      }
      current = current.next;
      return temp;
    }
  }

  private class Link<T> {
    T data;
    Link<T> next;
    Link<T> prev;

    public Link(T data) {
      this.data = data;
    }
  }
}
