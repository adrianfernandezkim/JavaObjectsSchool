/**
 * Created by h205p2 on 9/20/17.
 */
public class Person {
    static int staticID = 1000;
    int id;
    String firstName;
    String lastName;

    public Person(String firstName, String lastName){

        this.firstName = firstName;
        this.lastName = lastName;
        this.id = makeID();
    }
    public int makeID(){
        return staticID++;

    }


}
