package YUZUMod.cards;

import YUZUMod.character.YuzuCharacter;
import YUZUMod.effect.YUZUCollaborativeEffect;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUCollaborativeShooting extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("CollaborativeShooting");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ENEMY;
    private static final CardRarity RARITY=CardRarity.COMMON;

    public YUZUCollaborativeShooting() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=6;
        this.baseMagicNumber=this.magicNumber=1;
    }

    @Override
    protected void upgradeMethod() {
//        this.upgradeMagicNumber(1);
        this.upgradeDamage(3);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.LIGHTNING));

    }



    @Override
    public void applyPowers() {
        int baseBaseDamage=this.baseDamage;
        this.baseDamage+=YUZUCustomCard.MasterCards.size()*this.magicNumber;
        super.applyPowers();
        this.isDamageModified=this.damage!=baseBaseDamage;
        this.baseDamage=baseBaseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int baseBaseDamage=this.baseDamage;
        this.baseDamage+=YUZUCustomCard.MasterCards.size()*this.magicNumber;
        super.calculateCardDamage(mo);
        this.isDamageModified=this.damage!=baseBaseDamage;
        this.baseDamage=baseBaseDamage;
    }
}
