public class DoubleLList {

    private int courseCount;
    private int totalStudentCount;
    private ClassNode head;
    private ClassNode tail;

    DoubleLList() {

    }

    public void addClass() {
        this.tail.setNext(new ClassNode());
        this.tail.getNext().setPrevious(this.tail);
        this.setTail(this.tail.getNext());
    }

    public void removeClass() {

    }

    public void setHead(ClassNode node) {
        this.head = node;
    }

    public void setTail(ClassNode node) {
        this.tail = node;
    }

    public ClassNode getHead() {
        return this.head;
    }

    public ClassNode getTail() {
        return this.tail;
    }
}
