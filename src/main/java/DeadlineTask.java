public class DeadlineTask extends Task{
    private String deadline;
    public DeadlineTask(String name, String deadline){
        super(name);
        this.deadline = deadline;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }

    @Override
    public String toFileFormat(){
        return "D|" + super.toFileFormat() + "|" + this.deadline;
    }
}
