package baModDeveloper.cards;

import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.helper.ModHelper;
import baModDeveloper.power.YUZULoseEnergyNextTurnPower;
import baModDeveloper.power.YUZUReduceDrawPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUFinallyIHope extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("FinallyIHope");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=2;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.RARE;

    public YUZUFinallyIHope() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

    }

    @Override
    protected void upgradeMethod() {
        this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(this.upgraded){
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUReduceDrawPower(abstractPlayer,3)));
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZULoseEnergyNextTurnPower(abstractPlayer,2)));
        }else{
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUReduceDrawPower(abstractPlayer,2)));
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZULoseEnergyNextTurnPower(abstractPlayer,1)));
        }
        addToBot(new SkipEnemiesTurnAction());
        addToBot(new PressEndTurnButtonAction());
    }
}
