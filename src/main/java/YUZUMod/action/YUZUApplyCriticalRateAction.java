package YUZUMod.action;

import YUZUMod.character.YuzuCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YUZUApplyCriticalRateAction extends AbstractGameAction {
    public YUZUApplyCriticalRateAction(int amount) {
        this.amount=amount;

    }

    @Override
    public void update() {
        if(AbstractDungeon.player instanceof YuzuCharacter){
            YuzuCharacter yuzu= (YuzuCharacter) AbstractDungeon.player;
            yuzu.getCriticalRatePanel().increase(this.amount);
        }
        this.isDone=true;
    }
}
