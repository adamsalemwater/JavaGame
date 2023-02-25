package game;

import city.cs.engine.*;

public class Student extends Walker {

    private static final Shape studentImage = new BoxShape(1,2);
    private static final BodyImage image = new BodyImage("data/student.png", 4f);
    private int credits;


    public Student(World world) {
        super(world, studentImage);
        addImage(image);
        this.credits = 0;

    }


    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
