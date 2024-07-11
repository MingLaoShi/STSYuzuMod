package baModDeveloper.cards;

import baModDeveloper.helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.power.YUZUAimingPreparationMasterPower;
import baModDeveloper.power.YUZUAimingPreparationPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUAimingPreparation extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("AimingPreparation");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.POWER;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;


    public YUZUAimingPreparation() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=1;
    }

    @Override
    protected void upgradeMethod() {
//        upgradeMagicNumber(1);
        this.isInnate=true;
        this.upgradeDescription(CARD_STRINGS.UPGRADE_DESCRIPTION);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUAimingPreparationPower(abstractPlayer,this.magicNumber)));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUAimingPreparationMasterPower(abstractPlayer,this.magicNumber)));
    }
}
