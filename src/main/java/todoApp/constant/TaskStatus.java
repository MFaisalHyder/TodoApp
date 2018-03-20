package todoApp.constant;

public enum TaskStatus {

    COMPLETED("COMPLETED"),
    PENDING("PENDING");

    private String taskStatus;

    TaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

}