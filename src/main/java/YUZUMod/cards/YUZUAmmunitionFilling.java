package YUZUMod.cards;

import YUZUMod.action.YUZUApplyCriticalRateAction;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUAmmunitionFilling extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("AmmunitionFilling");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUAmmunitionFilling() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber=this.magicNumber=3;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(3);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new YUZUApplyCriticalRateAction(this.magicNumber));
        addToBot(new DrawCardAction(3));
    }
}
