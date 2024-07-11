package YUZUMod.action;

import YUZUMod.character.YuzuCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YUZUSetCriticalRateMaxAction extends AbstractGameAction {
    public YUZUSetCriticalRateMaxAction(int amount){
        this.amount=amount;
    }
    @Override
    public void update() {
        if(AbstractDungeon.player instanceof YuzuCharacter){
            ((YuzuCharacter) AbstractDungeon.player).getCriticalRatePanel().setMAX(this.amount);
        }
        this.isDone=true;
    }
}
