package OSKiller.tasks;

import OSKiller.Task;
import org.powerbot.script.rt4.ClientContext;

public class PowerBank extends Task<ClientContext> {
    private int FoodIDs = 315; //Shrimps
    private int FoodID = 333; //Trout
    private int runeMind = 558;
    private int runeAir = 556;
    public int numFood = 2;   //input how much food to withdraw
    private int LootID = 1739; //Cowhide


    public PowerBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(FoodID).count() < 1 &&ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 6;
    }

    @Override
    public void execute()
    {
        if (ctx.bank.inViewport()) {
            ctx.bank.open();
            if (ctx.bank.opened()) {
                withdraw(FoodID,26);
            }
        } else {
            ctx.camera.turnTo(ctx.bank.nearest());
        }



    }
    private void withdraw(int id, int num)
    {
        if(num == 5)
        {
            ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.FIVE);
        }
        if (num>5)
        {
            ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.FIVE);
            int foodCount = num-5;
            if(foodCount < 5)
            {
                for(int i = 0; i<foodCount; i++)
                {
                    ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.ONE);
                }
            }
            if(foodCount >= 5 && foodCount <10)
            {
                ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.FIVE);
                foodCount-=5;
                for(int i = 0;i<foodCount;i++)
                {
                    ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.ONE);

                }

            }
            if(foodCount >= 10 && foodCount<20)
            {
                ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.TEN);
                foodCount -=10;
                for(int i = 0; i<foodCount;i++)
                {
                    ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.ONE);
                }
            }
            if(foodCount >= 20)
            {
                ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.TEN);
                ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.TEN);
                foodCount-=20;
                for(int i = 0; i<foodCount; i++)
                {
                    ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.ONE);
                }
            }

        }
        if(num<5)
        {
            for(int i = 0;i<num;i++)
            {
                ctx.bank.withdraw(id, org.powerbot.script.rt4.Bank.Amount.ONE);

            }
        }
    }

}
