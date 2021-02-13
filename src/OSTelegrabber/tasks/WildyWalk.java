package OSTelegrabber.tasks;

import OSTelegrabber.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class WildyWalk extends Task<ClientContext>{
    public static final Tile[] path = {new Tile(2951, 3820, 0)};
    public static final Tile[] wildypath = {new Tile(2946, 3368, 0), new Tile(2946, 3372, 0), new Tile(2949, 3375, 0), new Tile(2953, 3378, 0), new Tile(2957, 3379, 0), new Tile(2960, 3382, 0), new Tile(2962, 3386, 0), new Tile(2963, 3390, 0), new Tile(2964, 3394, 0), new Tile(2964, 3398, 0), new Tile(2964, 3402, 0), new Tile(2966, 3406, 0), new Tile(2966, 3410, 0), new Tile(2963, 3413, 0), new Tile(2960, 3416, 0), new Tile(2956, 3419, 0), new Tile(2953, 3422, 0), new Tile(2952, 3426, 0), new Tile(2951, 3430, 0), new Tile(2949, 3434, 0), new Tile(2948, 3438, 0), new Tile(2948, 3442, 0), new Tile(2948, 3446, 0), new Tile(2948, 3450, 0), new Tile(2945, 3453, 0), new Tile(2945, 3457, 0), new Tile(2945, 3461, 0), new Tile(2945, 3465, 0), new Tile(2945, 3469, 0), new Tile(2945, 3473, 0), new Tile(2945, 3477, 0), new Tile(2945, 3481, 0), new Tile(2945, 3485, 0), new Tile(2945, 3489, 0), new Tile(2944, 3493, 0), new Tile(2942, 3497, 0), new Tile(2941, 3501, 0), new Tile(2941, 3505, 0), new Tile(2941, 3509, 0), new Tile(2941, 3513, 0), new Tile(2942, 3517, 0), new Tile(2946, 3520, 0), new Tile(2948, 3524, 0), new Tile(2951, 3527, 0), new Tile(2955, 3527, 0), new Tile(2959, 3526, 0), new Tile(2960, 3530, 0), new Tile(2957, 3534, 0), new Tile(2957, 3538, 0), new Tile(2957, 3542, 0), new Tile(2959, 3546, 0), new Tile(2959, 3550, 0), new Tile(2959, 3554, 0), new Tile(2959, 3558, 0), new Tile(2962, 3561, 0), new Tile(2963, 3565, 0), new Tile(2963, 3569, 0), new Tile(2961, 3573, 0), new Tile(2961, 3577, 0), new Tile(2957, 3579, 0), new Tile(2958, 3583, 0), new Tile(2958, 3587, 0), new Tile(2955, 3591, 0), new Tile(2954, 3595, 0), new Tile(2955, 3599, 0), new Tile(2955, 3603, 0), new Tile(2955, 3607, 0), new Tile(2955, 3611, 0), new Tile(2955, 3615, 0), new Tile(2955, 3619, 0), new Tile(2955, 3623, 0), new Tile(2955, 3627, 0), new Tile(2955, 3631, 0), new Tile(2955, 3635, 0), new Tile(2955, 3639, 0), new Tile(2955, 3643, 0), new Tile(2955, 3647, 0), new Tile(2955, 3651, 0), new Tile(2955, 3655, 0), new Tile(2956, 3659, 0), new Tile(2959, 3662, 0), new Tile(2961, 3666, 0), new Tile(2964, 3669, 0), new Tile(2967, 3672, 0), new Tile(2971, 3674, 0), new Tile(2975, 3675, 0), new Tile(2976, 3679, 0), new Tile(2978, 3683, 0), new Tile(2981, 3687, 0), new Tile(2985, 3690, 0), new Tile(2988, 3693, 0), new Tile(2991, 3696, 0), new Tile(2991, 3700, 0), new Tile(2991, 3704, 0), new Tile(2994, 3707, 0), new Tile(2996, 3711, 0), new Tile(2998, 3715, 0), new Tile(3000, 3719, 0), new Tile(3000, 3723, 0), new Tile(3001, 3727, 0), new Tile(3004, 3730, 0), new Tile(3005, 3734, 0), new Tile(3007, 3738, 0), new Tile(3010, 3741, 0), new Tile(3010, 3745, 0), new Tile(3009, 3749, 0), new Tile(3007, 3753, 0), new Tile(3007, 3757, 0), new Tile(3007, 3761, 0), new Tile(3007, 3765, 0), new Tile(3007, 3769, 0), new Tile(3007, 3773, 0), new Tile(3003, 3775, 0), new Tile(3000, 3778, 0), new Tile(2997, 3781, 0), new Tile(2994, 3784, 0), new Tile(2992, 3788, 0), new Tile(2989, 3791, 0), new Tile(2986, 3794, 0), new Tile(2983, 3797, 0), new Tile(2980, 3800, 0), new Tile(2978, 3804, 0), new Tile(2976, 3808, 0), new Tile(2972, 3810, 0), new Tile(2969, 3813, 0), new Tile(2966, 3816, 0), new Tile(2963, 3819, 0), new Tile(2959, 3819, 0), new Tile(2955, 3820, 0), new Tile(2951, 3820, 0)};
    public static final Tile[] telePath = {new Tile(2964, 3376, 0), new Tile(2960, 3377, 0), new Tile(2956, 3379, 0), new Tile(2952, 3379, 0), new Tile(2949, 3376, 0), new Tile(2946, 3373, 0), new Tile(2946, 3369, 0)};

    public WildyWalk(ClientContext ctx) {
        super(ctx);
    }

    private int lawRuneID = 563;
    private int mindBombID = 1907;
    private int emptyJugID = 1935;
    private int FoodID = 1993; //Trout
    private int WineID = 245;


    @Override
    public boolean activate()
    {
        return ctx.inventory.isFull() ||
                ctx.inventory.select().id(FoodID).count() <1||
                ctx.inventory.select().id(mindBombID).count() <1 ||
                ctx.inventory.select().id(lawRuneID).count() <1 ||
                ctx.inventory.select().count() == 11 && path[0].distanceTo(ctx.players.local()) > 13;


    }

    @Override
    public void execute() {

    }
}
