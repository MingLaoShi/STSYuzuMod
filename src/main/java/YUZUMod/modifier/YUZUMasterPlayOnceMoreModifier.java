package YUZUMod.modifier;

import YUZUMod.action.YUZUPlayTempCardAction;
import YUZUMod.cards.YUZUCustomCard;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;
import java.util.List;

public class YUZUMasterPlayOnceMoreModifier extends AbstractCardModifier {
    public static final String ID = "YUZUMasterPlayOnceMoreModifier";
    private static List<String> strings=new ArrayList<>();
    static {
        strings.add("精通：再打出1次。");
    }
    @Override
    public AbstractCardModifier makeCopy() {
        return new YUZUMasterPlayOnceMoreModifier();
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        if(YUZUCustomCard.isMastered(card)>0){
            AbstractCard c=card.makeSameInstanceOf();
            CardModifierManager.removeModifiersById(c,ID,false);
            addToBot(new YUZUPlayTempCardAction(c,target));
        }
    }

    @Override
    public List<String> extraDescriptors(AbstractCard card) {
        return strings;
    }
}
