package YUZUMod.helper;

import com.evacipated.cardcrawl.mod.stslib.cards.targeting.TargetingHandler;
import com.evacipated.cardcrawl.mod.stslib.patches.CustomTargeting;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.PotionSlot;

public class YUZUPotionTarget extends TargetingHandler<AbstractPotion> {
    @SpireEnum
    public static AbstractCard.CardTarget POTION;

    private AbstractPotion hovered=null;
    @Override
    public void updateHovered() {
        for(AbstractPotion p: AbstractDungeon.player.potions){
            if(!(p instanceof PotionSlot)&&p.hb.hovered){
                hovered=p;
            }
        }
    }

    @Override
    public AbstractPotion getHovered() {
        return hovered;
    }

    @Override
    public void clearHovered() {
        hovered=null;
    }

    @Override
    public boolean hasTarget() {
        return hovered!=null;
    }

    public static AbstractPotion getTarget(AbstractCard card){
        AbstractPotion potion= CustomTargeting.getCardTarget(card);
        if(potion==null){
            for(AbstractPotion p: AbstractDungeon.player.potions){
                if(!(p instanceof PotionSlot)){
                    potion=p;
                    break;
                }
            }
        }
        return potion;
    }

    @Override
    public void setDefaultTarget() {
        for(AbstractPotion p: AbstractDungeon.player.potions){
            if(!(p instanceof PotionSlot)){
                hovered=p;
                return;
            }
        }
    }
}
