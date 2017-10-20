import java.util.ArrayList;

/**
 * Created by h205p2 on 9/20/17.
 */
public class Section {
    String name;
    int maxSize;
    int currentSize;
    static ArrayList<Student> students = new ArrayList<Student>();




    Teacher teacher;

    public Section(String name, int maxSize, Teacher teacher){
        this.name = name;
        this.maxSize = maxSize;
        this.teacher = teacher;
        this.currentSize = 0;
        this.students = students;
    }
    public Section(String name, int maxSize){
        this.name = name;
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.students = students;
    }

    public void addStudent(Student student){
        students.add(student);
        currentSize++;

    }
    public void removeStudent(int id){
        for(int i=0; i<students.size(); i++){
            Student current = students.get(i);
           if(current.id == id){
               students.remove(current);
               currentSize -= 1;
           }
        }
    }
    public String sectionSeatsRemaining(){
        return("There are " +(maxSize - currentSize) +" seats remaining");

    }
    public String isAwesome(){
        int count = 0;
        for(int i = 0; i<students.size(); i++){
            if(students.get(i).awe) {
                count += 1;
            }
        }
        if(count>(currentSize/2)){
            count = 0;
            return("THIS SECTION IS AWESOOOOME!!!!");

        }
        if(count<(currentSize/2)){
            count = 0;
            return("This section is not awesome. You need "+ (((currentSize/2)-count)+1) + " more awesome students to make this section awesome.");
        }
        if(count == (currentSize/2)){
            count = 0;
            return("It's fifty fifty bud. Maybe you can mold some of the losers into awesomeness. Then again, maybe not.");
        }
        return("error");
    }
    public String search(int id){
        boolean stud = false;
        for(int i = 0;i<students.size();i++){
            if(students.get(i).id == id){
                stud = true;
               if(students.get(i).awe) {
                   return ("The student you searched is " + students.get(i).firstName + " " + students.get(i).lastName + ". They are in" +
                           " " + students.get(i).grade + "th grade. Their id number is " + students.get(i).id + " and they are awesome!");
               }
               else{
                   return ("The student you searched is " + students.get(i).firstName + " " + students.get(i).lastName + ". They are in" +
                           " " + students.get(i).grade + "th grade. Their id number is " + students.get(i).id + " and they are NOT awesome!");
               }
            }


        }
        if(!stud){
           return("This student doesn't exist.");
        }
        return("error");

    }
    public int getID(String firstName, String lastName){
        for(int i = 0; i<students.size(); i++){
            if(students.get(i).firstName.equals(firstName) &&students.get(i).lastName.equals(lastName) ){
                return students.get(i).id;
            }
        }
        return(0);
    }



}
