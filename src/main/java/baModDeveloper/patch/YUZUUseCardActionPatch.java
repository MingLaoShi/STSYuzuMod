package baModDeveloper.patch;

import baModDeveloper.cards.YUZUCustomCard;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class YUZUUseCardActionPatch {
    @SpirePatch(clz = UseCardAction.class,method = "update")
    public static class updatePatch{
        @SpirePrefixPatch
        public static void preFixPatch(UseCardAction _instance, AbstractCard ___targetCard,float ___duration){
            if(___duration==0.15F){
                YUZUCustomCard.masterCard(___targetCard);
            }
        }
    }
}
