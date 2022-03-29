package entity;

public class IdGenerator {
    private static Integer id = 1;

    public static Integer generate_id(){
        return id++;
    }
}
