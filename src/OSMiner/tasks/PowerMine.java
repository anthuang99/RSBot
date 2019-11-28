package OSMiner.tasks;

import OSMiner.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class PowerMine extends Task<ClientContext> {

    private int[] RockID = {7484, 7453}; // Copper

    public PowerMine(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() < 28 && !ctx.objects.select().id(RockID).isEmpty() && ctx.players.local().animation() == -1;
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

