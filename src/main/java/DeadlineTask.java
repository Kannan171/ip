import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    //private String deadline;
    private LocalDate deadline;
    public DeadlineTask(String name, String deadline){
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileFormat(){
        return "D|" + super.toFileFormat() + "|" + this.deadline;
    }
}
