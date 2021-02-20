package mySpringContext;

import mySpringContext.model.AngryPolicemen;
import mySpringContext.model.CoronaDisinfect;
import mySpringContext.model.interfaces.Policemen;
import mySpringContext.model.Room;
import mySpringContext.service.Application;
import mySpringContext.service.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("mySpringContext", new HashMap<>(Map.of(Policemen.class, AngryPolicemen.class)));
        CoronaDisinfect coronaDisinfect = context.getObject(CoronaDisinfect.class);
        coronaDisinfect.start(new Room());
    }
}
