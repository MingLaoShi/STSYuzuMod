package baModDeveloper.modifier;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class YUZUExhaustModifier extends AbstractCardModifier {
    @Override
    public AbstractCardModifier makeCopy() {
        return new YUZUExhaustModifier();
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        card.exhaust=true;
        return super.modifyDescription(rawDescription, card);
    }
}
