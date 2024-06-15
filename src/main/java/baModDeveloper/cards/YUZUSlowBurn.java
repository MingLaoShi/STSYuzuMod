package baModDeveloper.cards;

import baModDeveloper.action.YUZUDoubleBurningAction;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.helper.ModHelper;
import baModDeveloper.power.YUZUBurningPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUSlowBurn extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("SlowBurn");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.SKILL;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ALL;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUSlowBurn() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust=true;
        this.baseMagicNumber=this.magicNumber=2;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeMagicNumber(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new YUZUBurningPower(abstractPlayer,abstractPlayer,1)));
        for(AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
            addToBot(new ApplyPowerAction(m,abstractPlayer,new YUZUBurningPower(m,abstractPlayer,1)));
        }
        addToBot(new YUZUDoubleBurningAction(abstractPlayer,this.magicNumber-1));
        for(AbstractMonster m:AbstractDungeon.getCurrRoom().monsters.monsters){
            addToBot(new YUZUDoubleBurningAction(m,this.magicNumber-1));
        }
    }
}
