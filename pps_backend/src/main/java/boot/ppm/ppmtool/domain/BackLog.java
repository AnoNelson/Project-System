package boot.ppm.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 1/6/2020.
 */
@Entity
public class BackLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int pTSequence = 0;
    private String projectIdentifier;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id",nullable = false)
    @JsonIgnore
    private Project project;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "backLog")
    private List<ProjectTask> ProjectTasks = new ArrayList<>();


    public BackLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getpTSequence() {
        return pTSequence;
    }

    public void setpTSequence(int pTSequence) {
        this.pTSequence = pTSequence;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProjectTask> getProjectTasks() {
        return ProjectTasks;
    }

    public void setProjectTasks(List<ProjectTask> projectTasks) {
        ProjectTasks = projectTasks;
    }
}
