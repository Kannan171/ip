public class Task {
    private boolean isDone;
    private String name;
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    public static Task fromFileFormat(String line) {
        if (line.charAt(0) == 'T'){
            return new ToDoTask(line.substring(2));
        } else if (line.charAt(0)=='D'){
            String[] arr = line.split("\\|");
            return new DeadlineTask(arr[1], arr[2]);
        } else {
            String[] arr = line.split("\\|");
            return new EventTask(arr[1], arr[2], arr[3]);
        }
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
