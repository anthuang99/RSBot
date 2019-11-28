package OSCrafter.tasks;

import OSCrafter.Task;
import org.powerbot.script.rt4.ClientContext;


public class SapphireBank extends Task<ClientContext> {
    private int GoldID = 2357;
    private int RingID = 1637;//Sapphire
    private int RingIDg = 1635;//Gold
    private int SapphireID = 1607;
    private int MouldID = 1592;
    private int FurnaceID = 24009;

    public SapphireBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return (ctx.inventory.select().id(RingID).count() >= 1 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 5|| ctx.inventory.select().id(SapphireID).count() >13||ctx.inventory.select().id(GoldID).count() >13||ctx.inventory.select().id(SapphireID).count() <13 ||ctx.inventory.select().id(GoldID).count() <13);
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            ctx.bank.deposit(436, org.powerbot.script.rt4.Bank.Amount.ALL);
        } else {
            if (ctx.bank.inViewport()) {
                ctx.bank.open();
                if (ctx.bank.opened()) {
                    ctx.bank.deposit(1637, org.powerbot.script.rt4.Bank.Amount.ALL); //Sapphire Ring
                    ctx.bank.deposit(2357, org.powerbot.script.rt4.Bank.Amount.ALL);
                    ctx.bank.deposit(1607, org.powerbot.script.rt4.Bank.Amount.ALL);
                    ctx.bank.withdraw(2357, org.powerbot.script.rt4.Bank.Amount.TEN);
                    ctx.bank.withdraw(2357, org.powerbot.script.rt4.Bank.Amount.ONE);
                    ctx.bank.withdraw(2357, org.powerbot.script.rt4.Bank.Amount.ONE);
                    ctx.bank.withdraw(2357, org.powerbot.script.rt4.Bank.Amount.ONE);
                    ctx.bank.withdraw(1607, org.powerbot.script.rt4.Bank.Amount.TEN);
                    ctx.bank.withdraw(1607, org.powerbot.script.rt4.Bank.Amount.ONE);
                    ctx.bank.withdraw(1607, org.powerbot.script.rt4.Bank.Amount.ONE);
                    ctx.bank.withdraw(1607, org.powerbot.script.rt4.Bank.Amount.ONE);

                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }


    }
}
