public class Task {
    private boolean isDone;
    private String name;
    public Task(String name){
        this.name = name;
        this.isDone = false;
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
}
