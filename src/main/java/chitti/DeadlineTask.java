package chitti;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    //private String deadline;
    private LocalDate deadline;
    public DeadlineTask(String name, String deadline){
        super(name);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns string representation of deadline task
     * @return String to be printed
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the file format for a DeadlineTest
     * @return String representation to be saved in a file
     */
    @Override
    public String toFileFormat(){
        return "D|" + super.toFileFormat() + "|" + this.deadline;
    }
}
