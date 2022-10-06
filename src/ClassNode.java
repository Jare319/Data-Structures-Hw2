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

    public void addStudent(ClassNode node, String first, String last, String id, String email, String adr, String city) {
        first = first.replace("\""," ").replaceFirst(" ", "");
        last = last.replace("\"", "").replace(" ", "");
        adr = adr.concat(city);
        node.studentList.addStudent(first, last, id, email, adr);
    }

    public void addStudent(ClassNode node, String first, String last, String id, String email, String adr) {
        first = first.replace("\""," ").replaceFirst(" ", "");
        last = last.replace("\"", "").replace(" ", "");
        node.studentList.addStudent(first, last, id, email, adr);
    }
}
