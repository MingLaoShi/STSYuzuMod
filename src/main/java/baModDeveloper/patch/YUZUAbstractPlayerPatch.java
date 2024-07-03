package baModDeveloper.patch;

import baModDeveloper.cards.YUZUCustomCard;
import baModDeveloper.power.YUZUBurningPower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class YUZUAbstractPlayerPatch {
    @SpirePatch(clz = AbstractPlayer.class,method = "applyPreCombatLogic")
    public static class applyPreCombatLogic{
        @SpirePrefixPatch
        public static void prefixPatch(AbstractPlayer _instance){
            YUZUCustomCard.clearMasterCards();
            YUZUBurningPower.BASEDAMAGE=4;
//            YUZUCriticalHitPower.Multiplier=2.0F;
        }
    }

}
