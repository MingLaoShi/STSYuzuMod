package YUZUMod.action;

import YUZUMod.character.YuzuCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YUZUClearCriticalRateAction extends AbstractGameAction {
    @Override
    public void update() {
        if(AbstractDungeon.player instanceof YuzuCharacter){
            ((YuzuCharacter) AbstractDungeon.player).getCriticalRatePanel().increase(-((YuzuCharacter) AbstractDungeon.player).getCriticalRatePanel().getAmount());
        }
        this.isDone=true;
    }
}
