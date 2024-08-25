package lld.lrucache.datastructure;

public class DoublyLinkedList<K, V> {

    private Node<K, V> head;
    private Node<K, V> tail;
    private long linkedListSize = 0;

    public DoublyLinkedList() {
        head = new Node<>(null, null, null, null);
        tail = new Node<>(null, null, null, null);
        head.attachNext(tail);
        tail.attachPrev(head);
    }

    public void addNodeAtFirst(Node<K, V> newNode) {
        Node<K, V> afterHead = head.getNext();
        afterHead.detachPrev();
        head.detachNext();
        newNode.attachPrev(head);
        head.attachNext(newNode);
        newNode.attachNext(afterHead);
        afterHead.attachPrev(newNode);
        linkedListSize++;
    }

    public Node<K, V> removeLastNode() {
        Node<K, V> last = tail.getPrev();
        Node<K, V> secondLast = last.getPrev();
        last.detachNext();
        last.detachPrev();
        secondLast.attachNext(tail);
        tail.attachPrev(secondLast);
        linkedListSize--;
        return last;
    }

    public void removeNode(Node<K, V> node) {
        Node<K, V> next = node.getNext();
        Node<K, V> prev = node.getPrev();
        next.detachPrev();
        prev.detachNext();
        node.detachPrev();
        node.detachNext();
        prev.attachNext(next);
        next.attachPrev(prev);
        linkedListSize--;
    }

    public void consolePrint() {
        Node<K, V> nextNode = head.getNext();

        System.out.print("[ ");
        while (nextNode != null && nextNode.getNext() != null) {
            System.out.print(nextNode.getData() + ", ");
            nextNode = nextNode.getNext();
        }
        System.out.println(" ]");
    }
}

