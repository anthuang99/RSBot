package OSKiller.tasks;

import OSKiller.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Npc;

import java.util.concurrent.Callable;

public class Kill extends Task<ClientContext> {
    private int[] NPCIDg = {3031, 3033, 3032, 3029, 3036}; //Goblin
    //{3254}; //Guard
    private int FoodIDs = 315; //Shrimps
    private int FoodID = 333; //Trout
    private int LootID = 1739; //Cowhide
    private int BoneID = 326; //Bones

    private int[] NPCID = {2791, 2790, 2794, 2792}; //Cow
    private String NPCName = "Cow";
    private int healthToHeal = 15;



    public Kill(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return  ctx.inventory.select().id(FoodID).count() >0 && ctx.npcs.select().name(NPCName).nearest().poll().inViewport();
    }

    @Override
    public void execute() {
        if(needsHeal())
        {
            Heal();
        }
        if(ctx.inventory.select().id(BoneID).count()>0)
        {
            buryBones();
        }
        if(ctx.inventory.select().id(FoodID).count() >0)
        {
            final Npc npctoAttack = ctx.npcs.select().id(NPCID).select(npc->!npc.interacting().valid() && npc.healthPercent() > 0 &&
                    npc.tile().matrix(ctx).reachable()).nearest().poll();
            if (!npctoAttack.inViewport())
            {
                ctx.camera.turnTo(npctoAttack);
            }
            npctoAttack.interact("Attack");
            System.out.println("Attacking");
            Condition.sleep(1000);
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return !ctx.players.local().interacting().name().equals(NPCName) &&
                            !ctx.npcs.select().nearest().poll().healthBarVisible();
                }
            }, 300,200);
            loot();
            buryBones();
            if(needsHeal())
            {
                Heal();
            }
        }


    }
    private boolean needsHeal()
    {
        return ctx.combat.health() < healthToHeal;
    }
    private void Heal()
    {
        Item food = ctx.inventory.select().id(FoodID).poll();
        food.interact("Eat");
        final int startingHealth = ctx.combat.health();
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception
            {
                final int currentHealth = ctx.combat.health();
                return currentHealth > startingHealth;
            }
        },150,10);

    }
    private void loot()
    {
        System.out.println("Looting start");
        final GroundItem Bones = ctx.groundItems.select().id(LootID).nearest().poll();
        Bones.interact(false,"Take","Bones");
        final int InventSpace = ctx.inventory.select().count();
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().count() != InventSpace;
            }
        }, 200, 20);
        System.out.println("Looting end");

    }
    private void buryBones()
    {
        Item InventBones = ctx.inventory.select().id(LootID).poll();
        InventBones.interact("Bury");

        final int InventSpace2 = ctx.inventory.select().count();

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().count() != InventSpace2;
            }
        }, 150, 10);
    }


}
