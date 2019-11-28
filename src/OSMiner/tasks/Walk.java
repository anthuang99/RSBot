package OSMiner.tasks;

import OSMiner.Task;
import OSMiner.Walker;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class Walk extends Task<ClientContext> {
    //(Gold) public static final Tile[] path = {new Tile(2939, 3279, 0), new Tile(2937, 3283, 0), new Tile(2933, 3285, 0), new Tile(2933, 3289, 0), new Tile(2933, 3293, 0), new Tile(2933, 3297, 0), new Tile(2935, 3301, 0), new Tile(2935, 3305, 0), new Tile(2938, 3308, 0), new Tile(2942, 3309, 0), new Tile(2945, 3306, 0), new Tile(2949, 3306, 0), new Tile(2953, 3306, 0), new Tile(2957, 3306, 0), new Tile(2961, 3306, 0), new Tile(2965, 3306, 0), new Tile(2969, 3306, 0), new Tile(2973, 3306, 0), new Tile(2977, 3307, 0), new Tile(2981, 3307, 0), new Tile(2985, 3306, 0), new Tile(2989, 3306, 0), new Tile(2991, 3310, 0), new Tile(2994, 3313, 0), new Tile(2998, 3314, 0), new Tile(3001, 3318, 0), new Tile(3005, 3321, 0), new Tile(3006, 3325, 0), new Tile(3006, 3329, 0), new Tile(3006, 3333, 0), new Tile(3006, 3337, 0), new Tile(3007, 3341, 0), new Tile(3007, 3345, 0), new Tile(3007, 3349, 0), new Tile(3007, 3353, 0), new Tile(3007, 3357, 0), new Tile(3011, 3359, 0), new Tile(3012, 3355, 0)};
    //public static final Tile[] path = {new Tile(3146, 3150, 0), new Tile(3150, 3150, 0), new Tile(3151, 3154, 0), new Tile(3151, 3158, 0), new Tile(3150, 3162, 0), new Tile(3147, 3166, 0), new Tile(3144, 3169, 0), new Tile(3143, 3173, 0), new Tile(3143, 3177, 0), new Tile(3143, 3181, 0), new Tile(3143, 3185, 0), new Tile(3143, 3189, 0), new Tile(3143, 3193, 0), new Tile(3142, 3197, 0), new Tile(3139, 3200, 0), new Tile(3139, 3204, 0), new Tile(3136, 3208, 0), new Tile(3133, 3212, 0), new Tile(3137, 3210, 0), new Tile(3137, 3214, 0), new Tile(3135, 3218, 0), new Tile(3131, 3219, 0), new Tile(3127, 3219, 0), new Tile(3123, 3217, 0), new Tile(3119, 3217, 0), new Tile(3115, 3220, 0), new Tile(3112, 3223, 0), new Tile(3110, 3227, 0), new Tile(3110, 3231, 0), new Tile(3106, 3234, 0), new Tile(3102, 3235, 0), new Tile(3099, 3238, 0), new Tile(3098, 3242, 0), new Tile(3098, 3246, 0), new Tile(3094, 3247, 0), new Tile(3092, 3243, 0)};
    public static final Tile[] path = {new Tile(2940, 3279, 0), new Tile(2940, 3282, 0), new Tile(2937, 3283, 0), new Tile(2934, 3285, 0), new Tile(2933, 3288, 0), new Tile(2933, 3291, 0), new Tile(2933, 3294, 0), new Tile(2932, 3297, 0), new Tile(2933, 3300, 0), new Tile(2935, 3303, 0), new Tile(2938, 3303, 0), new Tile(2941, 3303, 0), new Tile(2944, 3303, 0), new Tile(2947, 3302, 0), new Tile(2950, 3300, 0), new Tile(2953, 3298, 0), new Tile(2956, 3297, 0), new Tile(2959, 3298, 0), new Tile(2962, 3298, 0), new Tile(2965, 3299, 0), new Tile(2968, 3298, 0), new Tile(2971, 3298, 0), new Tile(2974, 3299, 0), new Tile(2977, 3299, 0), new Tile(2980, 3300, 0), new Tile(2983, 3302, 0), new Tile(2986, 3301, 0), new Tile(2989, 3301, 0), new Tile(2992, 3301, 0), new Tile(2995, 3304, 0), new Tile(2998, 3304, 0), new Tile(3001, 3304, 0), new Tile(3004, 3306, 0), new Tile(3005, 3309, 0), new Tile(3005, 3312, 0), new Tile(3005, 3315, 0), new Tile(3005, 3318, 0), new Tile(3005, 3321, 0), new Tile(3006, 3324, 0), new Tile(3006, 3327, 0), new Tile(3006, 3330, 0), new Tile(3006, 3333, 0), new Tile(3006, 3336, 0), new Tile(3006, 3339, 0), new Tile(3007, 3342, 0), new Tile(3007, 3345, 0), new Tile(3007, 3348, 0), new Tile(3007, 3351, 0), new Tile(3007, 3354, 0), new Tile(3007, 3357, 0), new Tile(3010, 3359, 0), new Tile(3011, 3356, 0), new Tile(3014, 3355, 0)};
    //public static final Tile[] path = {new Tile(2940, 3278, 0), new Tile(2939, 3282, 0), new Tile(2935, 3284, 0), new Tile(2933, 3288, 0), new Tile(2933, 3292, 0), new Tile(2933, 3296, 0), new Tile(2933, 3300, 0), new Tile(2935, 3304, 0), new Tile(2937, 3308, 0), new Tile(2941, 3308, 0), new Tile(2945, 3309, 0), new Tile(2949, 3309, 0), new Tile(2953, 3310, 0), new Tile(2957, 3309, 0), new Tile(2961, 3309, 0), new Tile(2965, 3309, 0), new Tile(2969, 3308, 0), new Tile(2973, 3308, 0), new Tile(2977, 3308, 0), new Tile(2981, 3308, 0), new Tile(2985, 3308, 0), new Tile(2989, 3308, 0), new Tile(2992, 3311, 0), new Tile(2995, 3314, 0), new Tile(2999, 3316, 0), new Tile(3003, 3319, 0), new Tile(3006, 3322, 0), new Tile(3006, 3326, 0), new Tile(3006, 3330, 0), new Tile(3006, 3334, 0), new Tile(3006, 3338, 0), new Tile(3006, 3342, 0), new Tile(3006, 3346, 0), new Tile(3006, 3350, 0), new Tile(3006, 3354, 0), new Tile(3007, 3358, 0), new Tile(3011, 3359, 0), new Tile(3011, 3355, 0)};
    private final Walker walker = new Walker(ctx);

    public Walk(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.isFull() || (ctx.inventory.select().count() < 28 && path[0].distanceTo(ctx.players.local()) > 5);
    }

    @Override
    public void execute() {
        if (!ctx.movement.running() && ctx.movement.energyLevel() >= 25) {
            ctx.movement.running(true);
        }
        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (ctx.inventory.isFull()) {
                walker.walkPath(path);
            } else {
                walker.walkPathReverse(path);
            }


        }
    }
}
