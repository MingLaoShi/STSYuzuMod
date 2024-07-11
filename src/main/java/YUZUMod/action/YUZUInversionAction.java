package YUZUMod.action;

import YUZUMod.modifier.YUZUMasterExhaustModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YUZUInversionAction extends AbstractGameAction {

    @Override
    public void update() {
        for(AbstractCard card: AbstractDungeon.player.hand.group){
            CardModifierManager.addModifier(card,new YUZUMasterExhaustModifier());
        }
        this.isDone=true;
    }
}
