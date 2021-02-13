package OSDuster.tasks;

import OSDuster.Duster;
import OSDuster.Task;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

public class Dust extends Task<ClientContext>
{
    private int CDustID = 1975;
    private int CBarID = 1973;
    private int KnifeID = 946;
    private Random random = new Random();


    public Dust(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(CBarID).count() == 27 && ctx.inventory.select().id(KnifeID).count() == 1;
    }

    @Override
    public void execute()
    {
        Duster.ActionCounter = 1;
        for(int i = 0; i<27 ; i++)
        {
            int randInt1 = Random.nextInt(1,10);
            int randInt2 = Random.nextInt(1,10);

            switch(randInt1)
            {
                case 1 :
                    ctx.input.click(700, 445, true);
                    break;
                case 2 :
                    ctx.input.click(695, 435, true);
                    break;
                case 3 :
                    ctx.input.click(710, 450, true);
                    break;
                case 4 :
                    ctx.input.click(700, 432, true);
                    break;
                case 5 :
                    ctx.input.click(716, 431, true);
                    break;
                case 6 :
                    ctx.input.click(701, 448, true);
                    break;
                case 7 :
                    ctx.input.click(704, 439, true);
                    break;
                case 8 :
                    ctx.input.click(715, 451, true);
                    break;
                case 9 :
                    ctx.input.click(711, 432, true);
                    break;
                case 10 :
                    ctx.input.click(705, 440, true);
                    break;
            }
            switch(randInt2)
            {
                case 1 :
                    ctx.input.click(659, 456, true);
                    Duster.ChocolateCounter++;

                    break;
                case 2 :
                    ctx.input.click(654, 442, true);
                    Duster.ChocolateCounter++;

                    break;
                case 3 :
                    ctx.input.click(660, 452, true);
                    Duster.ChocolateCounter++;

                    break;
                case 4 :
                    ctx.input.click(664, 439, true);
                    Duster.ChocolateCounter++;

                    break;
                case 5 :
                    ctx.input.click(670, 440, true);
                    Duster.ChocolateCounter++;

                    break;
                case 6 :
                    ctx.input.click(669, 453, true);
                    Duster.ChocolateCounter++;

                    break;
                case 7 :
                    ctx.input.click(655, 432, true);
                    Duster.ChocolateCounter++;

                    break;
                case 8 :
                    ctx.input.click(668, 430, true);
                    Duster.ChocolateCounter++;

                    break;
                case 9 :
                    ctx.input.click(672, 450, true);
                    Duster.ChocolateCounter++;

                    break;
                case 10 :
                    ctx.input.click(667, 436, true);
                    Duster.ChocolateCounter++;

                    break;
            }
            //ctx.input.click(700, 445, true);
            //ctx.input.click(665, 450, true);
            if(ctx.inventory.select().id(CDustID).count() == 27)
            {
                break;
            }
        }


    }
}
