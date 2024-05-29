package baModDeveloper.modifier;

import baModDeveloper.cards.YUZUCustomCard;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class YUZUMasterExhaustModifier extends AbstractCardModifier {
    @Override
    public AbstractCardModifier makeCopy() {
        return new YUZUMasterExhaustModifier();
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        if(card instanceof YUZUCustomCard){
            if(YUZUCustomCard.isMastered((YUZUCustomCard) card)){
                action.exhaustCard=true;
            }
        }
    }
}
