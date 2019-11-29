package OSJugFiller.tasks;

import OSJugFiller.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;


public class Fill extends Task<ClientContext>
{
    private int JugID = 1935;
    private int FilledJugID = 1937;
    private int FountainID = 879;

    public Fill(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate()
    {
        return ctx.inventory.select().id(JugID).count() == 28;
    }

    @Override
    public void execute()
    {
        ctx.camera.angle(90);
        ctx.camera.pitch(99);
        GameObject fountain = ctx.objects.select().id(FountainID).nearest().poll();
        Item Jug = ctx.inventory.select().id(JugID).poll();
        Jug.interact("Use");
        fountain.click();
        while(ctx.inventory.select().id(FilledJugID).count() != 28 )
        {
            int jugCount1 = ctx.inventory.select().id(FilledJugID).count();
            Condition.sleep(3000);
            int jugCount2 = ctx.inventory.select().id(FilledJugID).count();
            if(jugCount1 == jugCount2)
            {
                Jug.interact("Use");
                fountain.click();
            }
            else
            {
                Condition.sleep(500);
            }
        }


    }
}
