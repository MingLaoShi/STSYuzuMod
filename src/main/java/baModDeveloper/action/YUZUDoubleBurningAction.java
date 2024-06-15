package baModDeveloper.action;

import baModDeveloper.power.YUZUBurningPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YUZUDoubleBurningAction extends AbstractGameAction {
    public YUZUDoubleBurningAction(AbstractCreature creature, int magicNumber) {
        this.target=creature;
        this.amount=magicNumber;
    }

    @Override
    public void update() {
        if(this.target.hasPower(YUZUBurningPower.POWER_ID)){
            int a=this.target.getPower(YUZUBurningPower.POWER_ID).amount;
            addToTop(new ApplyPowerAction(this.target, AbstractDungeon.player,new YUZUBurningPower(this.target,AbstractDungeon.player,a*this.amount)));
        }
        this.isDone=true;
    }
}
