package OSJugFiller.tasks;

import OSJugFiller.Task;
import org.powerbot.script.rt4.ClientContext;

public class Walk extends Task<ClientContext>
{
    private int JugID = 1935;
    private int FilledJugID = 1937;
    public Walk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate()
    {
        return ctx.inventory.select().id(FilledJugID).count() == 28 || ctx. inventory.select().id(JugID).count() == 28;
    }

    @Override
    public void execute() {

    }
}
