package baModDeveloper.cards;

import baModDeveloper.helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class YUZUColdTouch extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("ColdTouch");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ENEMY;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;
    private boolean changedEffect=false;

    public YUZUColdTouch() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=6;
        this.baseMagicNumber=this.magicNumber=1;
    }

    @Override
    protected void upgradeMethod() {
        upgradeDamage(3);
        this.upgradeDescription(CARD_STRINGS.UPGRADE_DESCRIPTION);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(abstractMonster,abstractPlayer,new WeakPower(abstractMonster,this.magicNumber,false)));
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    public void triggerOnCriticalHit(AbstractCreature target) {
        if(this.upgraded)
            addToBot(new ApplyPowerAction(target,AbstractDungeon.player,new StrengthPower(target,-2)));
        else
            addToBot(new ApplyPowerAction(target,AbstractDungeon.player,new StrengthPower(target,-1)));

    }

    @Override
    public void triggerOnMaster() {
        super.triggerOnMaster();
        this.changedEffect=true;
        int temp=this.baseDamage;
        this.baseDamage=this.baseMagicNumber;
        this.baseMagicNumber=temp;
    }

    @Override
    public void applyPowers() {
        int BaseBaseDamage=this.baseDamage;
        int BaseBaseMagic=this.baseMagicNumber;
        if(YUZUCustomCard.isMastered(this)>0){
            this.baseDamage=BaseBaseMagic;
            this.baseMagicNumber=BaseBaseDamage;
        }
        this.magicNumber=this.baseMagicNumber;
        super.applyPowers();

        this.baseDamage=BaseBaseDamage;
        this.baseMagicNumber=BaseBaseMagic;
        this.isDamageModified=this.damage!=BaseBaseDamage;
        this.isMagicNumberModified=this.magicNumber!=BaseBaseMagic;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int BaseBaseDamage=this.baseDamage;
        int BaseBaseMagic=this.baseMagicNumber;
        if(YUZUCustomCard.isMastered(this)>0){
            this.baseDamage=BaseBaseMagic;
            this.baseMagicNumber=BaseBaseDamage;
        }
        this.magicNumber=this.baseMagicNumber;
        super.calculateCardDamage(mo);
        this.baseDamage=BaseBaseDamage;
        this.baseMagicNumber=BaseBaseMagic;
        this.isDamageModified=this.damage!=BaseBaseDamage;
        this.isMagicNumberModified=this.magicNumber!=BaseBaseMagic;

    }
}
