package Plans;

import com.atlassian.bamboo.specs.api.builders.plan.Job;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.Stage;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.builders.task.ScriptTask;

public class HelloWorldPlan {

    public static Plan HelloWorldPlanGen(Project project) {
        return new Plan(
                project,
                "Hello World Plan", "HWP")
                .description("Plan created from github thing")
                .stages(
                        new Stage("Stage 1")
                                .jobs(new Job("Build and Run", "RUN")
                                        .tasks(
                                                new ScriptTask().inlineBody("echo Hello World!")
                                        )
                                )
                );
    }
}