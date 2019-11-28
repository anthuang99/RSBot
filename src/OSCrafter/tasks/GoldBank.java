package OSCrafter.tasks;

import OSCrafter.Task;
import org.powerbot.script.rt4.ClientContext;

public class GoldBank extends Task<ClientContext> {
    private int GoldID = 2357;
    private int RingIDs = 1637;//Sapphire
    private int RingID = 1635;//Gold
    private int MouldID = 1592;
    private int FurnaceID = 24009;

    public GoldBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return (ctx.inventory.select().id(RingID).count() == 27 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 6);
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            ctx.bank.deposit(436, org.powerbot.script.rt4.Bank.Amount.ALL);
        } else {
            if (ctx.bank.inViewport()) {
                ctx.bank.open();
                if (ctx.bank.opened()) {
                    ctx.bank.deposit(1635, org.powerbot.script.rt4.Bank.Amount.ALL); //Gold Ring
                    ctx.bank.withdraw(2357, org.powerbot.script.rt4.Bank.Amount.ALL);


                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }


    }
}
