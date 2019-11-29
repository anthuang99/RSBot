package OSJugFiller.tasks;
import OSJugFiller.Task;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task<ClientContext> {

    public Bank(ClientContext ctx) {
        super(ctx);
    }
    private int JugID = 1935;
    private int FilledJugID = 1937;

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(FilledJugID).count() == 28;
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            ctx.bank.deposit(FilledJugID, org.powerbot.script.rt4.Bank.Amount.ALL);
        } else {
            if (ctx.bank.inViewport()) {
                ctx.bank.open();
                if (ctx.bank.opened()) {
                    ctx.bank.deposit(FilledJugID, org.powerbot.script.rt4.Bank.Amount.ALL);
                    ctx.bank.withdraw(JugID, org.powerbot.script.rt4.Bank.Amount.ALL);
                    ctx.bank.close();
                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }


    }
}
