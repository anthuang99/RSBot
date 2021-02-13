package OSTelegrabber.tasks;

import OSTelegrabber.Task;
import OSTelegrabber.Telegrabber;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class Bank extends Task<ClientContext> {
    private int FoodID = 1993; //Trout
    private int emptyJugID = 1935;
    private int mindBombID = 1907;
    private int lawRuneID = 563;
    private int a = 0;
    // CHANGE THESE VALUES TO MODIFY AMOUNT WITHDRAWN
    private int numFood = 11;
    private int numMindBomb = 15;
    private int numLawRune = 30;


    public Bank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull() && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 5 ||
                ctx.inventory.select().id(FoodID).count() <1 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 5 ||
                ctx.inventory.select().id(mindBombID).count()<1 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 5 ||
                ctx.inventory.select().id(lawRuneID).count()<1 && ctx.bank.nearest().tile().distanceTo(ctx.players.local()) < 5 ;
    }

    @Override
    public void execute() {
        Telegrabber.ActionCounter = 1;
        if (ctx.bank.inViewport()) {
            ctx.bank.open();
            if (ctx.bank.opened())
            {
                ctx.bank.depositInventory();
                withdraw(lawRuneID,numLawRune);
                withdraw(mindBombID,numMindBomb);
                withdraw(FoodID,numFood);
                ctx.bank.close();
            }
            while(ctx.combat.health()<25)
            {
                ctx.game.tab(Game.Tab.INVENTORY, true);
                final int startingHealth = ctx.combat.health();
                Item foodtoEat = ctx.inventory.select().id(FoodID).poll();
                foodtoEat.interact("Drink");
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        final int currentHealth = ctx.combat.health();
                        return currentHealth != startingHealth;
                    }
                }, 150, 10);
                Item emptyFood = ctx.inventory.select().id(emptyJugID).poll();
                emptyFood.interact("Drop");
            }
            if(ctx.inventory.select().id(FoodID).count()<numFood) {
                ctx.bank.open();
                {
                    int foodCount = numFood - ctx.inventory.select().id(FoodID).count();
                    withdraw(FoodID,foodCount);
                    ctx.bank.close();
                }
            }
        }
        else
            {
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
