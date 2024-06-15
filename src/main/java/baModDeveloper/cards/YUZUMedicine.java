package baModDeveloper.cards;

import baModDeveloper.character.YuzuCharacter;
import baModDeveloper.helper.ModHelper;
import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUMedicine extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("Medicine");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET= SelfOrEnemyTargeting.SELF_OR_ENEMY;
    private static final CardRarity RARITY=CardRarity.COMMON;

    public YUZUMedicine() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=3;
        this.baseBlock=this.block=16;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(1);
        this.upgradeBlock(6);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractCreature target=SelfOrEnemyTargeting.getTarget(this);
        if(target==null){
            target= AbstractDungeon.player;
        }

        addToBot(new DamageAction(target,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new GainBlockAction(target,this.block));
    }

    @Override
    public void applyPowers() {
        int baseBaseDamage=this.baseDamage;
        int baseBaseBlock=this.baseBlock;
        if(YUZUCustomCard.isMastered(this)%2==1){
            this.baseBlock=baseBaseDamage;
            this.baseDamage=baseBaseBlock;
        }
        super.applyPowers();

        this.isDamageModified=this.damage!=baseBaseDamage;
        this.isBlockModified=this.block!=baseBaseBlock;
        this.baseDamage=baseBaseDamage;
        this.baseBlock=baseBaseBlock;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int baseBaseDamage=this.baseDamage;
        int baseBaseBlock=this.baseBlock;
        if(YUZUCustomCard.isMastered(this)%2==1){
            this.block=baseBaseDamage;
            this.damage=baseBaseBlock;
        }
        super.calculateCardDamage(mo);
        this.isDamageModified=this.damage!=baseBaseDamage;
        this.isBlockModified=this.block!=baseBaseBlock;
        this.baseDamage=baseBaseDamage;
        this.baseBlock=baseBaseBlock;
    }
}
