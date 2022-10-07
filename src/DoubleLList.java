public class DoubleLList {

    private int courseCount;
    private int totalStudentCount;
    private ClassNode head;
    private ClassNode tail;

    DoubleLList() {
        this.courseCount = 0;
        this.totalStudentCount = 0;
    }

    public void addCourse(String courseName, String courseNumber) {
        if (this.head == null && this.tail == null) {
            this.head = new ClassNode(courseName, courseNumber);
            this.tail = this.head;
        } else {
            this.tail.setNext(new ClassNode(courseName, courseNumber));
            this.tail.getNext().setPrevious(this.tail);
            this.setTail(this.tail.getNext());
        }
        this.courseCount++;
    }

    public boolean deleteCourse(String courseNumber) {
        ClassNode node = search(courseNumber);
        if (node != null) {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
            node.setNext(null);
            node.setPrevious(null);
            this.courseCount--;
            return true;
        }
        else {
            return false;
        }
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

    public ClassNode search(String courseNumber) {
        ClassNode node = this.head;
        if (node != null) {
            while (node != null) {
                if (node.getCourseNumber().equals(courseNumber)) {
                    return node;
                }
                node = node.getNext();
            }
        }
        return null;
    }

    public int getCourseCount() {
        return this.courseCount;
    }

    public int getStudentCount() {
        ClassNode node = this.head;
        while (node != null) {
            this.totalStudentCount += node.getStudentCount();
            node = node.getNext();
        }
        return this.totalStudentCount;
    }
}
