package OSMiner.tasks;

import OSMiner.Task;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task<ClientContext> {

    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull() && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 2;
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            ctx.bank.deposit(436, org.powerbot.script.rt4.Bank.Amount.ALL);
        } else {
            if (ctx.bank.inViewport()) {
                ctx.bank.open();
                if (ctx.bank.opened()) {
                    //ctx.bank.depositInventory();
                    //ctx.bank.deposit(453, org.powerbot.script.rt4.SapphireBank.Amount.ALL); //Coal
                    ctx.bank.deposit(444, org.powerbot.script.rt4.Bank.Amount.ALL); //Gold
                    ctx.bank.deposit(1623, org.powerbot.script.rt4.Bank.Amount.ALL);
                    ctx.bank.deposit(1617, org.powerbot.script.rt4.Bank.Amount.ALL);
                    ctx.bank.deposit(1619, org.powerbot.script.rt4.Bank.Amount.ALL);
                    ctx.bank.deposit(1621, org.powerbot.script.rt4.Bank.Amount.ALL);
                    //ctx.bank.deposit(447, org.powerbot.script.rt4.SapphireBank.Amount.ALL);//Mithril


                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }


    }
}
