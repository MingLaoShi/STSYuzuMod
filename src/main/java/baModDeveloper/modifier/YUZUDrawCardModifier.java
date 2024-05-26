package baModDeveloper.modifier;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class YUZUDrawCardModifier extends AbstractCardModifier {
    private int amount;
    public YUZUDrawCardModifier(int amount){
        this.amount=amount;
    }
    @Override
    public AbstractCardModifier makeCopy() {
        return new YUZUDrawCardModifier(this.amount);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        addToBot(new DrawCardAction(this.amount));
    }
}
