package Projects;

import com.atlassian.bamboo.specs.api.builders.project.Project;


public class ProjectReference {

    private final Project project;
    public ProjectReference(String projectKey, String projectName) {
        this.project = genProject(projectKey, projectName);
    }

    private Project genProject(String projectKey, String projectName) {
        return new Project().key(projectKey).name(projectName);
    }

    public Project getProject() {
        return this.project;
    }
}