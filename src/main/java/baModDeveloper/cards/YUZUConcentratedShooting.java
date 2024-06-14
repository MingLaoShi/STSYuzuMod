package baModDeveloper.cards;

import baModDeveloper.helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.power.YUZUCriticalHitPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUConcentratedShooting extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("ConcentratedShooting");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ENEMY;
    private static final CardRarity RARITY=CardRarity.COMMON;

    public YUZUConcentratedShooting() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=10;
        this.baseMagicNumber=this.magicNumber=50;
        this.selfRetain=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(5);
        this.upgradeMagicNumber(50);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if(AbstractDungeon.player.hasPower(YUZUCriticalHitPower.POWER_ID)){
            this.damage= (int) (this.damage/2*(200+this.magicNumber)/100.0F);
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if(AbstractDungeon.player.hasPower(YUZUCriticalHitPower.POWER_ID)){
            this.damage= (int) (this.damage/2*(200+this.magicNumber)/100.0F);
        }
    }
}
