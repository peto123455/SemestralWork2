package sk.uniza.fri.enums;

import sk.uniza.fri.entities.Entity;
import sk.uniza.fri.entities.Npc;
import sk.uniza.fri.essentials.Position;

import java.util.ArrayList;

//TODO: Odstrániť
@Deprecated
public enum ENpcs {
    WIZARD(EImageList.WIZARD, new Position(200, 200), 1);

    private Npc npc;
    private int map;

    ENpcs(EImageList image, Position position, int map) {
        //this.npc = new Npc(position, image);
        this.map = map;
    }

    public int getMap() {
        return this.map;
    }

    public static ArrayList<Entity> getNpcs(int map) {
        ArrayList<Entity> npcs = new ArrayList<>();
        for (ENpcs npc : ENpcs.values()) {
            if (npc.getMap() == map) {
                npcs.add(npc.npc);
            }
        }

        return npcs;
    }
}
