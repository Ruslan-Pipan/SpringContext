package mySpringContext.model;

import mySpringContext.model.interfaces.Policemen;
import mySpringContext.model.interfaces.Recommender;
import mySpringContext.service.annotations.InjectByType;

import javax.annotation.PostConstruct;

public class AngryPolicemen implements Policemen {

    @InjectByType
    private Recommender recommender;

    @PostConstruct
    public void init(){
        System.out.println(recommender.getClass());
    }
    @Override
    public void makePeopleLeveRoom() {
        System.out.println("Бю дубінкою!!!!");
    }
}
