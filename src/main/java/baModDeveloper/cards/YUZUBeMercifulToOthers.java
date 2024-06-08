package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.action.YUZUBeMercifulToOthersAction;
import baModDeveloper.character.YuzuCharacter;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUBeMercifulToOthers extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("BeMercifulToOthers");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=3;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ENEMY;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUBeMercifulToOthers() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.baseDamage=this.damage=32;
    }

    @Override
    protected void upgradeMethod() {
        this.updateCost(-1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new YUZUBeMercifulToOthersAction(abstractMonster,this));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
    }
}
