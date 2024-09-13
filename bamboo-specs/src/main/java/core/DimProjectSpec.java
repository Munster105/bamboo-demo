package core;

import Plans.HelloWorldPlan;
import Projects.ProjectReference;
import com.atlassian.bamboo.specs.api.BambooSpec;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.PlanIdentifier;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.util.BambooServer;
import com.atlassian.bamboo.specs.api.builders.permission.Permissions;
import com.atlassian.bamboo.specs.api.builders.permission.PermissionType;
import com.atlassian.bamboo.specs.api.builders.permission.PlanPermissions;
import config.Config;

/**
 * Plan configuration for Bamboo.
 * Learn more on: <a href="https://confluence.atlassian.com/display/BAMBOO/Bamboo+Specs">https://confluence.atlassian.com/display/BAMBOO/Bamboo+Specs</a>
 */
@BambooSpec
public class DimProjectSpec {

    /**
     * Run main to publish plan on Bamboo
     */
    public static void main(final String[] args) throws Exception {
        //By default, credentials are read from the '.credentials' file.
        BambooServer bambooServer = new BambooServer(Config.bambooServer);

        //Generate a Project for reference
        ProjectReference projectRef = new ProjectReference("DIM", "DimProject");

        //Generate Plans
        Plan plan = createPlan(projectRef.getProject());

        bambooServer.publish(plan);

        PlanPermissions planPermission = new DimProjectSpec().createPlanPermission(plan.getIdentifier());

        bambooServer.publish(planPermission);
    }

    public PlanPermissions createPlanPermission(PlanIdentifier planIdentifier) {
        Permissions permission = new Permissions()
                .userPermissions("bamboo", PermissionType.ADMIN, PermissionType.CLONE, PermissionType.EDIT)
                .groupPermissions("bamboo-admin", PermissionType.ADMIN)
                .loggedInUserPermissions(PermissionType.VIEW)
                .anonymousUserPermissionView();
        return new PlanPermissions(planIdentifier.getProjectKey(), planIdentifier.getPlanKey()).permissions(permission);
    }

    public static Plan createPlan(Project project) {
        return HelloWorldPlan.HelloWorldPlanGen(project);
    }
}