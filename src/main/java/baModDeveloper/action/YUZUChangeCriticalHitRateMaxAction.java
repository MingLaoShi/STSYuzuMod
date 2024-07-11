package baModDeveloper.action;

import baModDeveloper.character.YuzuCharacter;
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
        if(AbstractDungeon.player instanceof YuzuCharacter){
            ((YuzuCharacter) AbstractDungeon.player).getCriticalRatePanel().changeMAX(this.amount);
        }
        this.isDone=true;
    }
}
