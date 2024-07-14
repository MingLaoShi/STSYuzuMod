package YUZUMod.cards;

import YUZUMod.action.YUZUKillingInstructionsAction;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUKillingInstructions extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("KillingInstructions");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=3;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.SELF_AND_ENEMY;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUKillingInstructions() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=22;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(4);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new YUZUKillingInstructionsAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage)));
    }
}
