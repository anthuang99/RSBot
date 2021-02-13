package OSTelegrabber.tasks;

import OSTelegrabber.Task;
import OSTelegrabber.Telegrabber;
import OSTelegrabber.Walker;
import org.powerbot.script.Area;
import org.powerbot.script.Condition;
import org.powerbot.script.Locatable;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

import static org.powerbot.script.rt4.Magic.Spell.TELEKINETIC_GRAB;

public class Telegrab extends Task<ClientContext> {
    private int WineID = 245;
    private int TestID = 444;
    private int FoodID = 1993; //Trout
    private int emptyJugID = 1935;
    private int mindBombID = 1907;
    private int emptyGlassID = 1919;
    private int cake3ID = 1897;
    private int cake2ID = 1899;
    private int cake1ID = 1901;
    private int lawRuneID = 563;
    private int watRuneID = 555;

    //World you are staying in:
    private int world = 380;

    //private int[] joinableWorlds = {301,308,316,326,335,371,379,380,382,383,384,393,394,397,398,399};
    //430,472,473,474,475,476,497,498,499,500,501,502,503,504
    //472,473,474,475,476,497,498,499,500,501,502,503,504
    //497,498,501,502,503,435,436,379,380

    private int [] worldList = {382,380};
    private int [] BworldList = {497,498,499,500,501,502,503,504};
    private static final Tile[] wine = {new Tile(2931,3515,0)};
    private Area wineProximity = new Area(wine);
    public static final Tile path = new Tile(2940, 3517, 0);
    private static final Tile safe = new Tile(2943, 3517, 0);

    private final Walker walker = new Walker(ctx);
    private int i = 0;
    private int NumOfZammy = 0;

    public Telegrab(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(FoodID).count()>0 &&
                ctx.inventory.select().id(mindBombID).count()>0 &&
                ctx.inventory.select().id(lawRuneID).count()>0 &&
                path.distanceTo(ctx.players.local()) <13;
    }

    @Override
    public void execute() {
        Telegrabber.ActionCounter = 4;


        GroundItem wineOfZammy = ctx.groundItems.select().id(WineID).nearest().poll();
        if(needsHeal())
        {
            Heal();
        }
        clearJunk();
        if(wineOfZammy.valid() && ctx.npcs.select().select(npc->npc.interacting().equals(ctx.players.local())).isEmpty())
        {
            System.out.println("Option 1 start");
            walkPath();
            selectTelegrab();
            castTelegrab();
            System.out.println("Option 1 end");


        }
        else if(!wineOfZammy.valid() && ctx.npcs.select().select(npc->npc.interacting().equals(ctx.players.local())).isEmpty())
        {
            System.out.println("Option 2 start");

            if(ctx.combat.health() <20)
            {
                Heal();
            }
            clearJunk();
            setCamera();
            walkPath();
            int MagicLevel = ctx.skills.level(Constants.SKILLS_MAGIC);
            if(MagicLevel <33)
            {
                mindBomb();
            }
            ctx.game.tab(Game.Tab.MAGIC, true);
            if (ctx.game.tab() == Game.Tab.MAGIC)
            {
                ctx.magic.cast(TELEKINETIC_GRAB);
            }
            ctx.input.move(27,207);
            final int InventSpace = ctx.inventory.select().count();
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {

                    return ctx.groundItems.select().id(WineID).nearest().poll().valid();
                }
            }, 150, 200);
            if(ctx.groundItems.select().id(WineID).nearest().poll().valid())
            {
                ctx.input.click(ctx.groundItems.select().id(WineID).nearest().poll().centerPoint().x-10,ctx.groundItems.select().id(WineID).nearest().poll().centerPoint().y, true);
                if(ctx.combat.health()<20)
                {
                    Condition.sleep(1000);
                    System.out.println("health under 20, chatoption");
                    ctx.input.click(250,400,true);
                }
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        final int InventCount = ctx.inventory.select().count();
                        return InventCount != InventSpace;
                    }
                }, 200, 20);
            }
            walkSafe();
            if(needsHeal())
            {
                Heal();
            }
            clearJunk();
            Condition.sleep(10000);
            worldHop();
            System.out.println("Option 2 end");

        }
        else if(!ctx.npcs.select().select(npc->npc.interacting().equals(ctx.players.local())).isEmpty())
        {
            if(safe.distanceTo(ctx.players.local())>1)
            {
                worldHop();
            }
            else
            {
                walkSafe();
                worldHop();
            }
        }



    }

    private boolean needsHeal() {
        return ctx.combat.health() <16;
    }

    public void Heal() {
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
        clear(emptyJugID);

    }
    private void mindBomb()
    {
        ctx.game.tab(Game.Tab.INVENTORY, true);
        final int startingLevel = ctx.skills.level(Constants.SKILLS_MAGIC);
        Item mindBomb = ctx.inventory.select().id(mindBombID).poll();
        mindBomb.interact("Drink");
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                final int currentLevel = ctx.skills.level(Constants.SKILLS_MAGIC);
                return currentLevel != startingLevel;
            }
        }, 150, 10);
        clear(emptyGlassID);
    }
    private void clearJunk()
    {
        clear(emptyJugID);
        clear(emptyGlassID);
        if(ctx.inventory.isFull())
        {
            Heal();
        }
    }

    private void clear(int junk) {
        ctx.game.tab(Game.Tab.INVENTORY, true);
        if(ctx.inventory.select().id(junk).count()>0)
        {
            final int InventSpace = ctx.inventory.select().count();
            Item emptyGlass = ctx.inventory.select().id(junk).poll();
            emptyGlass.interact("Drop");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    final int InventCount = ctx.inventory.select().count();
                    return InventCount != InventSpace;
                }
            }, 200, 20);
        }
    }

    private void worldHop()
    {
        if(i <= 1)
        {
            new World(ctx,worldList[i],500, World.Type.FREE, World.Server.NORTH_AMERICA, World.Specialty.NONE).hop();
            System.out.println( "Current World: "+ worldList[i]);
            i++;

        }
        else
        {
            i = 0;
            System.out.println( "RESET :: Current World: "+ worldList[i]);
            new World(ctx,worldList[i],500, World.Type.FREE, World.Server.NORTH_AMERICA, World.Specialty.NONE).hop();
            i++;
        }
    }
    private void selectTelegrab() {
        int MagicLevel = ctx.skills.level(Constants.SKILLS_MAGIC);
        if(MagicLevel <33 && ctx.groundItems.select().id(WineID).nearest().poll().valid())
        {
            mindBomb();
        }
        ctx.game.tab(Game.Tab.MAGIC, true);
        if (ctx.game.tab() == Game.Tab.MAGIC && ctx.groundItems.select().id(WineID).nearest().poll().valid())
        {
            ctx.magic.cast(TELEKINETIC_GRAB);
        }
        else
        {
            walkSafe();
        }
    }
    private void castTelegrab() {
        boolean wine = false;
        clearJunk();
        if (ctx.magic.casting(TELEKINETIC_GRAB))
        {
            ctx.game.tab(Game.Tab.INVENTORY, true);
            final int InventSpace = ctx.inventory.select().count();
            GroundItem wineOfZammy = ctx.groundItems.select().id(WineID).nearest().poll();
            if(ctx.npcs.select().select(npc->npc.interacting().equals(ctx.players.local())).isEmpty() && wineOfZammy.valid())
            {
                System.out.println("casting");
                //ctx.input.click(wineOfZammy.centerPoint().x+20,wineOfZammy.centerPoint().y-5, true);
                ctx.input.click(wineOfZammy.centerPoint().x-10,wineOfZammy.centerPoint().y, true);

                if(ctx.combat.health()<20)
                {
                    Condition.sleep(1000);
                    System.out.println("health under 20, chatoption");
                    ctx.input.click(250,400,true);
                }
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        final int InventCount = ctx.inventory.select().count();
                        return InventCount != InventSpace;
                    }
                }, 200, 20);
            }
            else
            {
                wine = false;
                System.out.println("Aborting, drawn aggro");
            }

            if(ctx.inventory.select().count() != InventSpace)
            {
                wine = true;
                System.out.println("success");
            }
            else if(ctx.inventory.select().count() == InventSpace)
            {
                wine = false;
                System.out.println("not success");
            }
            walkSafe();
        }

        if(wine)
        {
            System.out.println("Casting option 1 start");
            if(needsHeal())
            {
                Heal();
            }
            clearJunk();
            Condition.sleep(10000);
            worldHop();
            System.out.println("Casting option 1 end");

        }
        else if(ctx.groundItems.select().id(WineID).nearest().poll().valid() && ctx.npcs.select().select(npc->npc.interacting().equals(ctx.players.local())).isEmpty())
        {
            System.out.println("Casting option 2 start");

            scrollOut();
            walkPath();
            setCamera();
            selectTelegrab();
            castTelegrab();
            System.out.println("Casting option 2 end");

        }
        else
        {
            System.out.println("Casting hopping");

            worldHop();
        }
    }

    private void setCamera()
    {

        if(ctx.camera.yaw() != 0)
        {
            ctx.camera.angle('n');
        }
        if(ctx.camera.pitch() < 99)
        {
            ctx.camera.pitch(true);

        }
    }

    private void walkPath()
    {
        /**
        while(path[0].distanceTo(ctx.players.local())>2)
        {
            walkPath();
        }
        **/
        ctx.movement.step(new Locatable() {
            @Override
            public Tile tile() {
                return path;
            }
        });
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return path.distanceTo(ctx.players.local())<1 && !ctx.players.local().inMotion();
            }
        }, 200, 20);

    }
    private void walkSafe()
    {
        while(safe.distanceTo(ctx.players.local())>1)
        {
            ctx.movement.step(new Locatable() {
                @Override
                public Tile tile() {
                    return safe;
                }
            });
        }
        /**
        for(int i = 0; i<5; i++)
        {
            walker.walkPath(safe);

        }
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.npcs.select().select(npc->npc.interacting().equals(ctx.players.local())).isEmpty();
            }
        }, 200, 20);
         **/

    }
    private void scrollOut()
    {
        ctx.game.tab(Game.Tab.OPTIONS);
        ctx.input.click(630,272,true);

    }





}
