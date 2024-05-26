package baModDeveloper.modifier;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class YUZUCanNotPlayModifier extends AbstractCardModifier {
    public static final String ID = "YUZUCanNotPlayModifier";

    @Override
    public AbstractCardModifier makeCopy() {
        return new YUZUCanNotPlayModifier();
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return false;
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
