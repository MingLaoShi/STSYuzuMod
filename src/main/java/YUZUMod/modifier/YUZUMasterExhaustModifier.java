package YUZUMod.modifier;

import YUZUMod.cards.YUZUCustomCard;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;
import java.util.List;

public class YUZUMasterExhaustModifier extends AbstractCardModifier {
    private static ArrayList<String> des=new ArrayList<>();
    static {
        des.add("精通：消耗");
    }
    @Override
    public AbstractCardModifier makeCopy() {
        return new YUZUMasterExhaustModifier();
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        super.onUse(card, target, action);
        if(card instanceof YUZUCustomCard){
            if(YUZUCustomCard.isMastered((YUZUCustomCard) card)>0){
                action.exhaustCard=true;
            }
        }
    }

    @Override
    public List<String> extraDescriptors(AbstractCard card) {
        return des;
    }
}
