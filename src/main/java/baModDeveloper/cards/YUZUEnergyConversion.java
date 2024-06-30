package baModDeveloper.cards;

import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.helper.ModHelper;
import baModDeveloper.power.YUZUCriticalHitPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUEnergyConversion extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("EnergyConversion");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
    private static final int COST=0;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUEnergyConversion() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust=true;
    }

    @Override
    protected void upgradeMethod() {
        this.exhaust=false;
        this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AbstractGameAction() {
            AbstractPlayer p= AbstractDungeon.player;
            @Override
            public void update() {
                int hitAmount=p.hasPower(YUZUCriticalHitPower.POWER_ID)?p.getPower(YUZUCriticalHitPower.POWER_ID).amount:0;
                int energyAmount=p.energy.energy;
                if(energyAmount==0){
                    addToTop(new RemoveSpecificPowerAction(p,p,YUZUCriticalHitPower.POWER_ID));
                }else {
                    addToTop(new ApplyPowerAction(p,p,new YUZUCriticalHitPower(p,energyAmount-hitAmount)));
                }
                if(hitAmount-energyAmount>0){
                    addToTop(new GainEnergyAction(hitAmount-energyAmount));
                }else{
                    addToTop(new LoseEnergyAction(energyAmount-hitAmount));
                }
                this.isDone=true;
            }
        });
    }
}
