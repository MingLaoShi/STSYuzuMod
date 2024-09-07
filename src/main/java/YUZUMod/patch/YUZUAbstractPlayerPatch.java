package YUZUMod.patch;

import YUZUMod.cards.YUZUCustomCard;
import YUZUMod.cards.YUZUDesignShooting;
import YUZUMod.helper.YUZUFirstMonsterTarget;
import YUZUMod.power.YUZUBurningPower;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

    //增加新的目标类型
    @SpirePatch(clz = AbstractPlayer.class,method = "renderHoverReticle")
    public static class renderHoverReticlePatch{
        @SpirePostfixPatch
        public static void postfixPatch(AbstractPlayer _instance, SpriteBatch sb, AbstractCard ___hoveredCard){
            if(___hoveredCard.target== YUZUFirstMonsterTarget.FIRSTMONSTER){
                YUZUDesignShooting.targetFirstMonster().renderReticle(sb);
            }
        }
    }

}
