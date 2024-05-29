package baModDeveloper.cards;

import baModDeveloper.action.YUZUApplyCriticalRateAction;
import baModDeveloper.power.YUZUAnalysisPower;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class YUZUCustomCard extends CustomCard {
    protected static Map<String,Integer> MasterCards=new HashMap<>();
    private int extraCirHitMulter;

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
        if(AbstractDungeon.player.hasPower(YUZUAnalysisPower.POWER_ID)){
            return ((YUZUAnalysisPower)AbstractDungeon.player.getPower(YUZUAnalysisPower.POWER_ID)).isMastered(card);
        }
        return MasterCards.containsKey(card.cardID);
    }

    public static void masterCard(YUZUCustomCard card){
        if(AbstractDungeon.player.hasPower(YUZUAnalysisPower.POWER_ID)){
            ((YUZUAnalysisPower)AbstractDungeon.player.getPower(YUZUAnalysisPower.POWER_ID)).masterCard(card);
//            MasterAllCards(card);
        } else{
            MasterCards.merge(card.cardID, 1, Integer::sum);
        }
    }

    private static void MasterAllCards(AbstractCard card) {
        AbstractDungeon.player.drawPile.group.stream().filter(c->c instanceof YUZUCustomCard&&c.cardID.equals(card.cardID)).forEach(c->((YUZUCustomCard) c).triggerOnMaster());
        AbstractDungeon.player.hand.group.stream().filter(c->c instanceof YUZUCustomCard&&c.cardID.equals(card.cardID)).forEach(c->((YUZUCustomCard) c).triggerOnMaster());
        AbstractDungeon.player.discardPile.group.stream().filter(c->c instanceof YUZUCustomCard&&c.cardID.equals(card.cardID)).forEach(c->((YUZUCustomCard) c).triggerOnMaster());
    }

    public void setMasterNum(int masterNum) {
        masterNum = masterNum;
    }
    public static void removeMaster(YUZUCustomCard card){
        MasterCards.remove(card.cardID);
    }

    public static void clearMasterCards(){
        MasterCards.clear();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(!YUZUCustomCard.isMastered(this)){
            this.commonUse(abstractPlayer,abstractMonster);
        }else{
            this.masterUse(abstractPlayer,abstractMonster);
        }
        addToBot(new YUZUApplyCriticalRateAction(1));
        YUZUCustomCard.masterCard(this);
    }

    public abstract void commonUse(AbstractPlayer abstractPlayer,AbstractMonster abstractMonster);
    public abstract void masterUse(AbstractPlayer abstractPlayer,AbstractMonster abstractMonster);



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
        if(card instanceof YUZUCustomCard&&YUZUCustomCard.isMastered((YUZUCustomCard) card)){
            ((YUZUCustomCard) card).triggerOnMaster();
        }
        return card;
    }

    @Override
    public void triggerOnGlowCheck() {
        if(YUZUCustomCard.isMastered(this)){
            this.glowColor= Color.RED.cpy();
        }
    }
}
