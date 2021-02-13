package OSKiller.tasks;

import OSKiller.Task;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task<ClientContext> {
    private int FoodIDs = 315; //Shrimps
    private int FoodID = 333; //Trout
    private int runeMind = 558;
    private int runeAir = 556;



    private int LootID = 1739; //Cowhide


    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(FoodID).count() < 1 &&ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 6 ||
                ctx.inventory.isFull() && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 6;
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            ctx.bank.depositInventory();
        } else {
            if (ctx.bank.inViewport()) {
                ctx.bank.open();
                if (ctx.bank.opened()) {
                    ctx.bank.deposit(LootID, org.powerbot.script.rt4.Bank.Amount.ALL);
                    if(ctx.inventory.select().id(FoodID).count() <2)
                    {
                        ctx.bank.deposit(FoodID, org.powerbot.script.rt4.Bank.Amount.ALL);
                        ctx.bank.withdraw(FoodID, org.powerbot.script.rt4.Bank.Amount.ONE);
                        ctx.bank.withdraw(FoodID, org.powerbot.script.rt4.Bank.Amount.ONE);

                    }
                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }


    }
}

