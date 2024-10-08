package YUZUMod.cards;

import YUZUMod.YuzuMod;
import YUZUMod.hooks.YUZUTriggerOnMasteredInterface;
import YUZUMod.power.YUZUAnalysisPower;
import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.patches.FlavorText;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.HashMap;
import java.util.Map;

public abstract class YUZUCustomCard extends CustomCard {
    public static Map<String,Integer> MasterCards=new HashMap<>();

    public YUZUCustomCard(String ID, String NAME, String IMG_PATH, int COST, String DESCRIPTION, CardType TYPE, CardColor COLOR, CardRarity RARITY, CardTarget TARGET) {
        super(ID,NAME,IMG_PATH,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
        FlavorText.AbstractCardFlavorFields.boxColor.set(this,YuzuMod.YUZUColor.cpy());
    }

    @Override
    public void upgrade() {
        if(!upgraded){
            this.upgradeName();
            upgradeMethod();
        }
    }

    protected abstract void upgradeMethod();

    public static int isMastered(AbstractCard card){
        int masterNum=0;
        if(AbstractDungeon.player.hasPower(YUZUAnalysisPower.POWER_ID)){
            masterNum+= ((YUZUAnalysisPower)AbstractDungeon.player.getPower(YUZUAnalysisPower.POWER_ID)).isMastered(card);
        }
        masterNum+=MasterCards.getOrDefault(card.cardID,0);
        return masterNum;
    }

    public static void masterCard(AbstractCard card){
        MasterCards.merge(card.cardID, 1, Integer::sum);
        int masterNum=MasterCards.get(card.cardID);
        if(masterNum==1){
            if(card instanceof YUZUTriggerOnMasteredInterface){
                ((YUZUTriggerOnMasteredInterface) card).triggerOnMastered(masterNum);
            }
            for(AbstractCard c:AbstractDungeon.player.drawPile.group){
                if(c.cardID.equals(card.cardID)&&c instanceof YUZUTriggerOnMasteredInterface&&c!=card){
                    ((YUZUTriggerOnMasteredInterface) c).triggerOnMastered(masterNum);
                }
            }
            for(AbstractCard c:AbstractDungeon.player.hand.group){
                if(c.cardID.equals(card.cardID)&&c instanceof YUZUTriggerOnMasteredInterface&&c!=card){
                    ((YUZUTriggerOnMasteredInterface) c).triggerOnMastered(masterNum);
                }
            }
            for(AbstractCard c:AbstractDungeon.player.discardPile.group){
                if(c.cardID.equals(card.cardID)&&c instanceof YUZUTriggerOnMasteredInterface&&c!=card){
                    ((YUZUTriggerOnMasteredInterface) c).triggerOnMastered(masterNum);
                }
            }
        }

    }

    public static void removeMaster(AbstractCard card){
        MasterCards.remove(card.cardID);
    }

    public static void clearMasterCards(){
        MasterCards.clear();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(YUZUCustomCard.isMastered(this)<=0){
            this.commonUse(abstractPlayer,abstractMonster);
        }else{
            this.masterUse(abstractPlayer,abstractMonster);
        }
//        YUZUCustomCard.masterCard(this);
    }

    public abstract void commonUse(AbstractPlayer abstractPlayer,AbstractMonster abstractMonster);
    public void masterUse(AbstractPlayer abstractPlayer,AbstractMonster abstractMonster){
        commonUse(abstractPlayer,abstractMonster);
    }



    public void triggerOnMaster(){}

    public void triggerOnCriticalHit(AbstractCreature target){
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }



//    @Override
//    public void triggerOnGlowCheck() {
//        if(YUZUCustomCard.isMastered(this)>0){
//            this.glowColor= YuzuMod.YUZUColor.cpy();
//        }else {
//            this.glowColor=AbstractCard.BLUE_BORDER_GLOW_COLOR;
//        }
//    }

    public static class YUZUCardTag{
        @SpireEnum
        public static CardTags NoNeedCriticalHit;

    }

    public static class YUZUTarget{
        @SpireEnum
        public static CardTarget POTIONS;
    }

    public void upgradeDescription(String des){
        this.rawDescription=des;
        this.initializeDescription();
    }
}
