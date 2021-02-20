package mySpringContext.model;


import mySpringContext.model.interfaces.Announcer;
import mySpringContext.model.interfaces.Policemen;
import mySpringContext.service.annotations.InjectByType;

public class CoronaDisinfect {

    @InjectByType
    private Announcer announcer;
    @InjectByType
    private Policemen policemen;

    public void start(Room room){
        announcer.announce("Починаєме дезенфекцію!!!!");
        policemen.makePeopleLeveRoom();
        disinfect(room);
        announcer.announce("Можна зходити в кімнату.");
    }

    private void disinfect(Room rom){
        System.out.println("Дезкнфекція: ");
    }
}
