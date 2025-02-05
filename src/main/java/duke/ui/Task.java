package duke.ui;

public class Task {
    private boolean isDone;
    private String name;
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    public static Task fromFileFormat(String line) {
        Task task;
        String[] arr = line.split("\\|");
        if (arr[0].equals("T")){
            task = new ToDoTask(arr[2]);
        } else if (arr[0].equals("D")){
            return new DeadlineTask(arr[2], arr[3]);
        } else {
            return new EventTask(arr[2], arr[3], arr[4]);
        }
        if (arr[1].equals("1")){
            task.doTask();
        }
        return task;
    }

    public void doTask(){
        this.isDone = true;
    }
    public void undoTask(){
        this.isDone = false;
    }
    @Override
    public String toString(){
        if (this.isDone){
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    public String toFileFormat() {
        if (this.isDone){
            return "1|" + this.name;
        } else {
            return "0|" + this.name;
        }
    }
}
