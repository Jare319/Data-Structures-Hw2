public class ClassNode {

    private String courseName;
    private String courseNumber;
    private int studentCount;
    private ClassNode next;
    private ClassNode previous;
    private SingleLList studentList;

    ClassNode(String courseName, String courseNumber) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.studentCount = 0;
        this.studentList = new SingleLList();
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

    public String getCourseName() {
        return this.courseName;
    }

    public String getCourseNumber() {
        return this.courseNumber;
    }

    public int getStudentCount() {
        return this.studentCount;
    }

    public SingleLList getStudentList() {
        return this.studentList;
    }

    public void addStudent(String first, String last, String id, String email, String adr, String city) {
        //for handling edge cases where adr was improperly formated in input
        first = first.replace("\"", "").replaceFirst(" ", "");
        last = last.replace("\" ", "").replace("\"", "");
        String name = first.concat(last);
        adr = adr.concat(city);
        this.studentList.addStudent(name, id, email, adr);
        this.studentCount++;
    }

    public void addStudent(String first, String last, String id, String email, String adr) {
        first = first.replace("\"", "").replaceFirst(" ", "");
        last = last.replace("\" ", "").replace("\"", "");
        String name = first.concat(last);
        this.studentList.addStudent(name, id, email, adr);
        this.studentCount++;
    }

    public void addStudent(String name, String id, String email, String adr) {
        this.studentList.addStudent(name, id, email, adr);
        this.studentCount++;
    }

    public void deleteStudent(String id) {
        StudentNode prevNode = null;
        StudentNode node = this.getStudentList().getHead();
        while (node != null) {
            if (node.getStudentId().equals(id)) {
                if (node == this.getStudentList().getHead()) { 
                    //handles if student was at head node
                    this.getStudentList().setHead(node.getNext());
                } else if (node == this.getStudentList().getTail()) {
                    //handles if student was at tail node
                    this.getStudentList().setTail(prevNode);
                }
                prevNode.setNext(node.getNext());
                node.setNext(null);
            }
            prevNode = node;
            node = node.getNext();
        }
        this.studentCount--;
    }
}
