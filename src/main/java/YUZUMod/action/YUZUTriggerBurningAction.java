package YUZUMod.action;

import YUZUMod.power.YUZUBurningPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class YUZUTriggerBurningAction extends AbstractGameAction {
    private final int times;

    public YUZUTriggerBurningAction(AbstractCreature target, int times) {
        this.target=target;
        this.times=times;
    }

    @Override
    public void update() {
        if(this.target.hasPower(YUZUBurningPower.POWER_ID)){
            AbstractPower p=this.target.getPower(YUZUBurningPower.POWER_ID);
            for(int i=0;i<this.times;i++){
                p.atStartOfTurn();
            }
        }
        this.isDone=true;
    }
}
