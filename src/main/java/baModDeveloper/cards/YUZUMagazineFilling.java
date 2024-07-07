package baModDeveloper.cards;

import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.helper.ModHelper;
import baModDeveloper.power.YUZUExtraCriticalRatePower;
import baModDeveloper.power.YUZUMagazineFillingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUMagazineFilling extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("MagazineFilling");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.POWER;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUMagazineFilling() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=2;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
        this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(this.upgraded){
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUExtraCriticalRatePower(abstractPlayer,25)));
        }else{
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUExtraCriticalRatePower(abstractPlayer,50)));
        }

        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUMagazineFillingPower(abstractPlayer,this.magicNumber)));
    }
}
