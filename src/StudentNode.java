public class StudentNode {
    
    private String studentName;
    private String studentId;
    private String email;
    private String address;
    private StudentNode next;

    StudentNode(String studentName, String studentId, String email, String address) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.email = email;
        this.address = address;
    }

    //---SETTERS---//
    public void setNext(StudentNode node) {
        this.next = node;
    }

    public void setStudentId(String id) {
        this.studentId = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStudentName(String name) {
        this.studentName = name;
    }

    //---GETTERS---//
    public StudentNode getNext() {
        return this.next;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }
}
