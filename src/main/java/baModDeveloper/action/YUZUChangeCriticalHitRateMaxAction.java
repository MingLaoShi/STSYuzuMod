package baModDeveloper.action;

import baModDeveloper.power.YUZUCriticalHitRatePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YUZUChangeCriticalHitRateMaxAction extends AbstractGameAction {
    public YUZUChangeCriticalHitRateMaxAction(int amount){
        this.amount=amount;
    }
    @Override
    public void update() {
        if(AbstractDungeon.player.hasPower(YUZUCriticalHitRatePower.POWER_ID)){
            ((YUZUCriticalHitRatePower)AbstractDungeon.player.getPower(YUZUCriticalHitRatePower.POWER_ID)).changeMax(this.amount);
        }else{
            addToTop(new YUZUChangeCriticalHitRateMaxAction(this.amount));
            addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new YUZUCriticalHitRatePower(AbstractDungeon.player,0)));
        }
        this.isDone=true;
    }
}
