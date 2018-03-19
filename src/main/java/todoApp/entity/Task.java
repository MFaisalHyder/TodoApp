package todoApp.entity;

import javax.persistence.*;

@Entity
@Table(name = "TASK")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TASK_NAME")
    private String taskName;

    @Column(name = "TASK_STATUS")
    private String taskStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        return getId().equals(task.getId()) && getTaskName().equals(task.getTaskName()) && getTaskStatus().equals(task.getTaskStatus());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTaskName().hashCode();
        result = 31 * result + getTaskStatus().hashCode();
        return result;
    }

}