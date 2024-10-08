package YUZUMod.cards;

import YUZUMod.cards.colorless.YUZUIronBox;
import YUZUMod.helper.ModHelper;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.power.YUZUCurlUpPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUCurlUp extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("CurlUp");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.POWER;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUCurlUp() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=1;
        this.cardsToPreview=new YUZUIronBox();
    }

    @Override
    protected void upgradeMethod() {
        this.rawDescription=CARD_STRINGS.UPGRADE_DESCRIPTION;
        initializeDescription();
        this.isInnate=true;
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUCurlUpPower(abstractPlayer,this.magicNumber)));
    }

}
