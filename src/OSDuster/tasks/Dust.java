package OSDuster.tasks;

import OSDuster.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class Dust extends Task<ClientContext>
{
    private int CDustID = 1975;
    private int CBarID = 1973;
    private int KnifeID = 946;


    public Dust(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(CBarID).count() == 27 && ctx.inventory.select().id(KnifeID).count() == 1;
    }

    @Override
    public void execute()
    {
        for(int i = 0; i<27 ; i++)
        {
            ctx.input.click(700, 445, true);
            Condition.sleep(1000);
            ctx.input.click(665, 450, true);
            if(ctx.inventory.select().id(CDustID).count() == 27)
            {
                break;
            }
        }


    }
}
