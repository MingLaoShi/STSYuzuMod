package baModDeveloper.modifier;

import baModDeveloper.action.YUZUPlayTempCardAction;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class YUZUPlayOnceMoreModifier extends AbstractCardModifier {
    public static final String ID = "YUZUPlayOnceMoreModifier";

    @Override
    public AbstractCardModifier makeCopy() {
        return new YUZUPlayOnceMoreModifier();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        AbstractCard c=card.makeSameInstanceOf();
        CardModifierManager.removeModifiersById(c,ID,false);
        addToBot(new YUZUPlayTempCardAction(c,target));
    }
}
