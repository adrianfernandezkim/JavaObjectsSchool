/**
 * Created by h205p2 on 9/20/17.
 */
public class Student extends Person {

    int grade;
    boolean awe;

    public Student(int grade, String firstName, String lastName, boolean awesome){
        super(firstName, lastName);
        this.grade=grade;
        this.awe = awesome;

    }

}
