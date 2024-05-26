package baModDeveloper.modifier;

import baModDeveloper.action.YUZUPlayTempCardAction;
import baModDeveloper.cards.YUZUCustomCard;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class YUZUMasterPlayOnceMoreModifier extends AbstractCardModifier {
    public static final String ID = "YUZUMasterPlayOnceMoreModifier";

    @Override
    public AbstractCardModifier makeCopy() {
        return new YUZUMasterPlayOnceMoreModifier();
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        if(card instanceof YUZUCustomCard&&YUZUCustomCard.isMastered((YUZUCustomCard) card)){
            AbstractCard c=card.makeSameInstanceOf();
            CardModifierManager.removeModifiersById(c,ID,false);
            addToBot(new YUZUPlayTempCardAction(c,target));
        }
    }
}
