public class ClassNode {
    
    private String courseName;
    private String courseNumber;
    private int studentCount;
    private ClassNode next;
    private ClassNode previous;
    private SingleLList studentList;

    ClassNode() {

    }

    public void setNext(ClassNode node) {
        this.next = node;
    }

    public void setPrevious(ClassNode node) {
        this.previous = node;
    }

    public ClassNode getNext() {
        return this.next;
    }

    public ClassNode getPrevious() {
        return this.previous;
    }
}
