public class SingleLList {
    
    private StudentNode head;
    private StudentNode tail;

    SingleLList() {
        this.head = null;
        this.tail = null;
    }

    public void addStudent(String name, String id, String email, String adr) {
        if (this.head == null && this.tail == null) {
            this.head = new StudentNode(name, id, email, adr);
            this.tail = this.head;
            //handles the first node added to the list
        } else {
            this.tail.setNext(new StudentNode(name, id, email, adr));
            this.tail = this.tail.getNext();
            //handles all additional nodes after the first
        }
    }

    public void deleteStudent() {

    }

    public void setHead(StudentNode node) {
        this.head = node;
    }

    public void setTail(StudentNode node) {
        this.tail = node;
    }

    public StudentNode getHead() {
        return this.head;
    }

    public StudentNode getTail() {
        return this.tail;
    }


}
