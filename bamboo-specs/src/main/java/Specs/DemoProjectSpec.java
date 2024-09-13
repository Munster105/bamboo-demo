package Specs;

import Plans.HelloWorldPlan;
import Projects.ProjectReference;
import config.Config;

import com.atlassian.bamboo.specs.api.BambooSpec;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.PlanIdentifier;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.util.BambooServer;
import com.atlassian.bamboo.specs.api.builders.permission.Permissions;
import com.atlassian.bamboo.specs.api.builders.permission.PermissionType;
import com.atlassian.bamboo.specs.api.builders.permission.PlanPermissions;

/**
 * Plan configuration for Bamboo.
 * Learn more on: <a href="https://confluence.atlassian.com/display/BAMBOO/Bamboo+Specs">https://confluence.atlassian.com/display/BAMBOO/Bamboo+Specs</a>
 */
@BambooSpec
public class DemoProjectSpec {

    /**
     * Run main to publish plan on Bamboo
     */
    public static void main(final String[] args) throws Exception {
        //By default, credentials are read from the '.credentials' file.
        BambooServer bambooServer = new BambooServer(Config.bambooServer);

        //Generate a Project for reference
        ProjectReference projectRef = new ProjectReference("DEMO", "Demo");

        //Generate Plans
        HelloWorldPlan plan = new HelloWorldPlan(projectRef.getProject());

        bambooServer.publish(plan);

        bambooServer.publish(plan.getPlanPermissions());
    }
}