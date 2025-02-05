package chitti;

public class ToDoTask extends Task{
    public ToDoTask(String name){
        super(name);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat(){
        return "T|" + super.toFileFormat();
    }
}
