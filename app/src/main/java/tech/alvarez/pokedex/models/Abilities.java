package tech.alvarez.pokedex.models;

/**
 * Created by andres on 29/05/17.
 */

public class Abilities {
    private String slot;
    private Dato ability;

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public Dato getAbility() {
        return ability;
    }

    public void setAbility(Dato ability) {
        this.ability = ability;
    }
}
