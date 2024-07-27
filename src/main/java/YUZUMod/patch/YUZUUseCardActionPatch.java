//package YUZUMod.patch;
//
//import YUZUMod.action.YUZUApplyCriticalRateAction;
//import YUZUMod.cards.YUZUCustomCard;
//import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.megacrit.cardcrawl.actions.utility.UseCardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//
//public class YUZUUseCardActionPatch {
//    @SpirePatch(clz = UseCardAction.class,method = "update")
//    public static class updatePatch{
//        @SpireInsertPatch(rloc = 71)
//        public static void preFixPatch(UseCardAction _instance, AbstractCard ___targetCard,float ___duration){
//
//            YUZUCustomCard.masterCard(___targetCard);
//            AbstractDungeon.actionManager.addToBottom(new YUZUApplyCriticalRateAction(1));
//            YUZUBlockWordEffectPatch.isCriticalHit=false;
//
//        }
//    }
//}
