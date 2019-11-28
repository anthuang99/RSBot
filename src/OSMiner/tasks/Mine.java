package OSMiner.tasks;

import OSMiner.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class Mine extends Task<ClientContext> {
    public static final Tile[] path = {new Tile(2940, 3279, 0)};
    private int[] RockIDc = {7484, 7453}; // Copper
    private int[] RockIDi = {7488, 7455}; //Iron
    private int[] RockIDco = {7456, 7489}; //Coal
    private int[] RockIDm = {7492, 7459}; //Mithril
    private int[] RockIDmc = {7456, 7489, 7492, 7459}; //Coal + Mithril
    private int[] RockID = {11371, 11370}; // gold


    public Mine(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() < 28 && !ctx.objects.select().id(RockID).isEmpty() && path[0].distanceTo(ctx.players.local()) < 4 && ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        GameObject rock = ctx.objects.select().id(RockID).nearest().poll();
        rock.interact("Mine");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() != -1;
            }
        }, 150, 10);

    }
}
