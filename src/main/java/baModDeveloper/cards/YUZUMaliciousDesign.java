package baModDeveloper.cards;

import baModDeveloper.action.YUZUMaliciousDesignAction;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.helper.ModHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUMaliciousDesign extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("MaliciousDesign");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF_AND_ENEMY;
    private static final CardRarity RARITY=CardRarity.RARE;

    public YUZUMaliciousDesign() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.isEthereal=true;
    }

    @Override
    protected void upgradeMethod() {
        this.isEthereal=false;
        this.upgradeDescription(CARD_STRINGS.UPGRADE_DESCRIPTION);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new YUZUMaliciousDesignAction(abstractMonster,false));

    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new YUZUMaliciousDesignAction(abstractMonster,true));
    }
}
