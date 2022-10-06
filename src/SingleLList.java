public class SingleLList {
    
    private StudentNode head;
    private StudentNode tail;

    SingleLList() {

    }

    public void addStudent() {
        if (this.head == null && this.tail == null) {
            this.head = new StudentNode(null, null, null, null);
            this.tail = this.head;
        } else {
            this.tail.setNext(new StudentNode(null, null, null, null));
        }
    }

}
