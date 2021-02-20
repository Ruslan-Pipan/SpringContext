package mySpringContext.model;

import mySpringContext.model.interfaces.Announcer;
import mySpringContext.model.interfaces.Recommender;
import mySpringContext.service.annotations.InjectByType;

public class ConsoleAnnouncer implements Announcer {
    @InjectByType
    private Recommender recommender;

    @Override
    public void announce(String s) {
        System.out.println(s);
        recommender.recommend();
    }
}
