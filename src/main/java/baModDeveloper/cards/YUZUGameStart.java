package baModDeveloper.cards;

import baModDeveloper.Helper.ModHelper;
import baModDeveloper.character.YuzuCharacter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUGameStart extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("GameStart");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=3;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ALL_ENEMY;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZUGameStart() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=28;
        this.isMultiDamage=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(12);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAllEnemiesAction(abstractPlayer,this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.LIGHTNING));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
        this.triggerOnCriticalHit(abstractMonster);
    }

    @Override
    public void applyPowers() {
        this.tags.remove(YUZUCardTag.NoNeedCriticalHit);
        if(YUZUCustomCard.isMastered(this)>0){
            this.tags.add(YUZUCardTag.NoNeedCriticalHit);
        }
        super.applyPowers();

        if(YUZUCustomCard.isMastered(this)>0){
            this.damage*=4;
            this.isDamageModified=this.damage!=this.baseDamage;
        }
    }
}
