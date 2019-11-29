package OSDuster.tasks;

import OSDuster.Task;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task<ClientContext>
{
    private int CDustID = 1975;
    private int CBarID = 1973;
    private int KnifeID = 946;

    public Bank(ClientContext ctx)
    {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(CDustID).count() == 27 && ctx.inventory.select().id(KnifeID).count() == 1;
    }

    @Override
    public void execute()
    {
    if (ctx.bank.opened())
        ctx.bank.deposit(CDustID, org.powerbot.script.rt4.Bank.Amount.ALL);
    else {

        if (ctx.bank.inViewport())
        {
        ctx.bank.open();
            if (ctx.bank.opened())
            {
                ctx.bank.deposit(CDustID, org.powerbot.script.rt4.Bank.Amount.ALL);
                ctx.bank.withdraw(CBarID, org.powerbot.script.rt4.Bank.Amount.ALL);

            }
        }
    else
        ctx.camera.turnTo(ctx.bank.nearest());
}


}
}