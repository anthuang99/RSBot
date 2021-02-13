package OSTelegrabber.tasks;

import OSTelegrabber.Task;
import OSTelegrabber.Telegrabber;
import OSTelegrabber.Walker;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;


public class Walk extends Task<ClientContext>
{
    public static final Tile[] path = {new Tile(2938, 3517, 0), new Tile(2942, 3517, 0), new Tile(2942, 3513, 0), new Tile(2941, 3509, 0), new Tile(2941, 3505, 0), new Tile(2941, 3501, 0), new Tile(2942, 3497, 0), new Tile(2942, 3493, 0), new Tile(2942, 3489, 0), new Tile(2942, 3485, 0), new Tile(2942, 3481, 0), new Tile(2942, 3477, 0), new Tile(2943, 3473, 0), new Tile(2944, 3469, 0), new Tile(2944, 3465, 0), new Tile(2944, 3461, 0), new Tile(2944, 3457, 0), new Tile(2944, 3453, 0), new Tile(2944, 3449, 0), new Tile(2946, 3445, 0), new Tile(2946, 3441, 0), new Tile(2946, 3437, 0), new Tile(2948, 3433, 0), new Tile(2948, 3429, 0), new Tile(2949, 3425, 0), new Tile(2952, 3422, 0), new Tile(2955, 3419, 0), new Tile(2959, 3417, 0), new Tile(2962, 3414, 0), new Tile(2965, 3410, 0), new Tile(2965, 3406, 0), new Tile(2965, 3402, 0), new Tile(2965, 3398, 0), new Tile(2965, 3394, 0), new Tile(2965, 3390, 0), new Tile(2965, 3386, 0), new Tile(2964, 3382, 0), new Tile(2961, 3379, 0), new Tile(2957, 3379, 0), new Tile(2953, 3379, 0), new Tile(2949, 3376, 0), new Tile(2946, 3373, 0), new Tile(2946, 3368, 0)};
    public static final Tile[] telePath = {new Tile(2964, 3376, 0), new Tile(2960, 3377, 0), new Tile(2956, 3379, 0), new Tile(2952, 3379, 0), new Tile(2949, 3376, 0), new Tile(2946, 3373, 0), new Tile(2946, 3369, 0)};

    private int WineID = 245;
    private int TestID = 444;
    private int FoodID = 1993; //Trout
    private int emptyJugID = 1935;
    private int mindBombID = 1907;
    private int emptyGlassID = 1919;
    private int lawRuneID = 563;
    private int watRuneID = 555;
    private final Walker walker = new Walker(ctx);
    public Walk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull() ||
                ctx.inventory.select().id(FoodID).count() <1||
                ctx.inventory.select().id(mindBombID).count() <1 ||
                ctx.inventory.select().id(lawRuneID).count() <1 ||
                ctx.inventory.select().count() == 27 && path[0].distanceTo(ctx.players.local()) > 13;

    }

    @Override
    public void execute()
    {
        if (!ctx.movement.running() && ctx.movement.energyLevel() >= 25) {
            ctx.movement.running(true);
        }
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (ctx.inventory.isFull() && ctx.inventory.select().id(FoodID).count() <1||
                    ctx.inventory.isFull() && ctx.inventory.select().id(mindBombID).count() <1||
                    ctx.inventory.select().id(FoodID).count() <1 || ctx.inventory.select().id(mindBombID).count() <1 || ctx.inventory.select().id(lawRuneID).count() <1 )
            {
                Telegrabber.ActionCounter = 3;
                if(ctx.camera.yaw() <200)
                {
                    ctx.camera.angle('s');
                }
                walker.walkPath(path);

            }
            else {
                Telegrabber.ActionCounter = 2;
                if(ctx.camera.yaw() > 0)
                {
                    ctx.camera.angle('n');
                }
                walker.walkPathReverse(path);
            }


        }


    }
}
