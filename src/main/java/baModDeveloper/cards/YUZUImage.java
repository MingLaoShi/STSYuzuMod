package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.power.YUZUImagePower;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUImage extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Image");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=3;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.POWER;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.RARE;

    public YUZUImage() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=20;
    }

    @Override
    protected void upgradeMethod() {
        upgradeMagicNumber(5);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new AddTemporaryHPAction(abstractPlayer,abstractMonster,this.magicNumber));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUImagePower(abstractPlayer)));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
    }
}
