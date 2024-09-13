package Plans;

import com.atlassian.bamboo.specs.api.builders.permission.PermissionType;
import com.atlassian.bamboo.specs.api.builders.permission.Permissions;
import com.atlassian.bamboo.specs.api.builders.permission.PlanPermissions;
import com.atlassian.bamboo.specs.api.builders.plan.Job;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.PlanIdentifier;
import com.atlassian.bamboo.specs.api.builders.plan.Stage;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.builders.task.ScriptTask;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HelloWorldPlan extends Plan {

    private PlanPermissions planPermissions;

    public HelloWorldPlan(Project project) {
        super(project, "Hello World Plan", "HWP");
        this.description("Plan created from github thing");
        this.addPlanPermissions(this.getIdentifier());
        this.addStages();
    }

    // Might be able to abstract into own class to inherit in all Plans if permissions are all the same
    private void addPlanPermissions(PlanIdentifier planIdentifier) {
        Permissions permission = new Permissions()
                .userPermissions("bamboo", PermissionType.ADMIN, PermissionType.CLONE, PermissionType.EDIT)
                .groupPermissions("bamboo-admin", PermissionType.ADMIN)
                .loggedInUserPermissions(PermissionType.VIEW)
                .anonymousUserPermissionView();
        this.planPermissions = new PlanPermissions(
                    planIdentifier.getProjectKey(),
                    planIdentifier.getPlanKey())
                .permissions(permission);
    }

    private void addStages() {
        ArrayList<Stage> stages = new ArrayList<>();

        // Here you'd probably have Stage classes defined for easy importing
        stages.add(new Stage("Stage 1")
                .jobs(new Job("Run", "RUN")
                    .tasks(
                            new ScriptTask().inlineBody("echo Hello World!")
                    )
        ));

        Stage[] stagesArr = stages.toArray(new Stage[0]);
        this.stages(stagesArr);
    }

    public PlanPermissions getPlanPermissions() {
        return this.planPermissions;
    }
}