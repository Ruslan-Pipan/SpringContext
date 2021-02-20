package mySpringContext.model;

import mySpringContext.model.interfaces.Policemen;

public class PolicemenImpl implements Policemen {
    @Override
    public void makePeopleLeveRoom() {
        System.out.println("пиф пах геть з кімнати");
    }
}
