package YUZUMod.action;

import YUZUMod.cards.YUZUCustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class YUZUMasterCardAction extends AbstractGameAction {
    private final AbstractCard card;

    public YUZUMasterCardAction(AbstractCard card){
        this.card=card;
    }
    @Override
    public void update() {
        YUZUCustomCard.masterCard(card);
        this.isDone=true;
    }
}
