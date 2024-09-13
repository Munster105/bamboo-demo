package tests;

import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.api.exceptions.PropertiesValidationException;
import com.atlassian.bamboo.specs.api.util.EntityPropertiesBuilders;
import Specs.DimProjectSpec;
import org.junit.Test;

public class DimProjectSpecTest {
    @Test
    public void checkYourPlanOffline() throws PropertiesValidationException {
        Plan plan = DimProjectSpec.createPlan(new Project().key("DIM").name("Something"));

        EntityPropertiesBuilders.build(plan);
    }
}