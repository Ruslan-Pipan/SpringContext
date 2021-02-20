package mySpringContext.model;

import mySpringContext.model.interfaces.Recommender;
import mySpringContext.service.annotations.InjectProperty;
import mySpringContext.service.annotations.Singleton;

@Singleton
@Deprecated
public class RecommenderImpl implements Recommender {
    @InjectProperty("wisky")
    private String alcohol;

    public RecommenderImpl() {
        System.out.println("recomendator was created.");
    }

    @Override
    public void recommend() {
        System.out.println("to protect to covid-2019 drink " + alcohol);
    }
}
