package baModDeveloper.cards;

import baModDeveloper.action.YUZUApplyCriticalRateAction;
import baModDeveloper.power.YUZUAnalysisPower;
import baModDeveloper.power.YUZUCriticalHitPower;
import baModDeveloper.power.YUZUCriticalHitRatePower;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.HashSet;
import java.util.Set;

public abstract class YUZUCustomCard extends CustomCard {
    protected int masterNum=0;
    protected static Set<String> MasterCards=new HashSet<>();
    public boolean autoCritical=false;

    public YUZUCustomCard(String ID, String NAME, String IMG_PATH, int COST, String DESCRIPTION, CardType TYPE, CardColor COLOR, CardRarity RARITY, CardTarget TARGET) {
        super(ID,NAME,IMG_PATH,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
    }

    @Override
    public void upgrade() {
        if(!upgraded){
            this.upgradeName();
            upgradeMethod();
        }
    }

    protected abstract void upgradeMethod();

    public static boolean isMastered(YUZUCustomCard card){
        if(card.masterNum>0){
            return true;
        }
        if(AbstractDungeon.player.hasPower(YUZUAnalysisPower.POWER_ID)){
            return ((YUZUAnalysisPower)AbstractDungeon.player.getPower(YUZUAnalysisPower.POWER_ID)).isMastered(card);
        }
        return MasterCards.contains(card.cardID);
    }

    public static boolean isMasteredWithChangeNum(YUZUCustomCard card){
        boolean master=isMastered(card);
        if(master&&card.masterNum==0){
            card.masterNum++;
        }
        return master;
    }
    public static void masterCard(YUZUCustomCard card){
        if(AbstractDungeon.player.hasPower(YUZUAnalysisPower.POWER_ID)){
            ((YUZUAnalysisPower)AbstractDungeon.player.getPower(YUZUAnalysisPower.POWER_ID)).masterCard(card);
        }
        card.masterOnce();
        if(!MasterCards.contains(card.cardID)){
            MasterCards.add(card.cardID);
            card.triggerOnMaster();
            AbstractDungeon.player.drawPile.group.stream().filter(c->c instanceof YUZUCustomCard).forEach(c->((YUZUCustomCard) c).triggerOnMaster());
            AbstractDungeon.player.hand.group.stream().filter(c->c instanceof YUZUCustomCard).forEach(c->((YUZUCustomCard) c).triggerOnMaster());
            AbstractDungeon.player.discardPile.group.stream().filter(c->c instanceof YUZUCustomCard).forEach(c->((YUZUCustomCard) c).triggerOnMaster());

        }
    }
    public int getMasterNum() {
        return masterNum;
    }

    public void setMasterNum(int masterNum) {
        masterNum = masterNum;
    }
    public void masterOnce(){
        this.masterNum++;
    }
    public static void removeMaster(YUZUCustomCard card){
        MasterCards.remove(card.cardID);
    }

    public static void clearMasterCards(){
        MasterCards.clear();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        YUZUCustomCard.isMasteredWithChangeNum(this);
        if(this.autoCritical){
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUCriticalHitPower(abstractPlayer,1)));
        }
        if(this.masterNum==0){
            this.commonUse(abstractPlayer,abstractMonster);
        }else{
            this.masterUse(abstractPlayer,abstractMonster,this.masterNum);
        }
//        YUZUCustomCard.masterCard(this);
        addToBot(new YUZUApplyCriticalRateAction(1));
    }

    public abstract void commonUse(AbstractPlayer abstractPlayer,AbstractMonster abstractMonster);
    public abstract void masterUse(AbstractPlayer abstractPlayer,AbstractMonster abstractMonster,int masterNum);


    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card=super.makeStatEquivalentCopy();
        if(card instanceof YUZUCustomCard){
            ((YUZUCustomCard) card).masterNum=this.masterNum;
        }
        return card;
    }

    public void triggerOnMaster(){}

    public void triggerOnCriticalHit(AbstractCreature target){
//        this.damage*=2;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
    }

    @Override
    public AbstractCard makeCopy() {
        AbstractCard card=super.makeCopy();
        if(card instanceof YUZUCustomCard&&YUZUCustomCard.isMasteredWithChangeNum((YUZUCustomCard) card)){
            ((YUZUCustomCard) card).triggerOnMaster();
        }
        return card;
    }

    @Override
    public void triggerOnGlowCheck() {
        if(YUZUCustomCard.isMasteredWithChangeNum(this)){
            this.glowColor= Color.RED.cpy();
        }
    }
}
