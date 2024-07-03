package baModDeveloper.cards;

import baModDeveloper.action.YUZUApplyCriticalRateAction;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.helper.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUSpreadShooting extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("SpreadShooting");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ALL_ENEMY;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUSpreadShooting() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=6;
        this.baseMagicNumber=this.magicNumber=2;
        this.isMultiDamage=true;
    }

    @Override
    protected void upgradeMethod() {
//        this.upgradeDamage(2);
        this.upgradeMagicNumber(1);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAllEnemiesAction(abstractPlayer,this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        int monsterNum= (int) AbstractDungeon.getCurrRoom().monsters.monsters.stream().filter(m->!m.isDeadOrEscaped()).count();
        addToBot(new YUZUApplyCriticalRateAction(this.magicNumber*monsterNum));
    }

}
