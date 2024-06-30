package baModDeveloper.cards;

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

import java.util.HashSet;
import java.util.Set;

public class YUZULinearSegmentation extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("LinearSegmentation");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH= ModHelper.makeCardImagePath(ID);
    private static final int COST=2;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ALL_ENEMY;
    private static final CardRarity RARITY=CardRarity.UNCOMMON;

    public YUZULinearSegmentation() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=13;
        this.baseMagicNumber=this.magicNumber=10;
        this.isMultiDamage=true;
    }

    @Override
    protected void upgradeMethod() {
        upgradeDamage(4);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAllEnemiesAction(abstractPlayer,this.multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void applyPowers() {
        int baseBaseDamage=this.baseDamage;
        this.baseDamage=hasSameEmery()?this.baseDamage+this.magicNumber:this.baseDamage;
        super.applyPowers();

        this.isDamageModified=this.damage!=baseBaseDamage;
        this.baseDamage=baseBaseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int baseBaseDamage=this.baseDamage;
        this.baseDamage=hasSameEmery()?this.baseDamage+this.magicNumber:this.baseDamage;
        super.calculateCardDamage(mo);
        this.isDamageModified=this.damage!=baseBaseDamage;
        this.baseDamage=baseBaseDamage;
    }

    private boolean hasSameEmery(){
        Set<String> set=new HashSet<>();
        for(AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!m.isDeadOrEscaped()){
                if(set.contains(m.name)){
                    return true;
                }else{
                    set.add(m.name);
                }
            }
        }
        return false;
    }
}
