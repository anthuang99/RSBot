package OSMiner.tasks;

import OSMiner.Task;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class Drop extends Task<ClientContext> {
    public int RockID = 436;

    public Drop(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull();
    }

    @Override
    public void execute() {
        for (Item i : ctx.inventory.id(RockID)) {
            i.interact("Drop");

        }
    }
}
