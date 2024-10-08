package YUZUMod.cards;

import YUZUMod.helper.ModHelper;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.YUZUFirstMonsterTarget;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUDesignShooting extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("DesignShooting");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=1;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET= YUZUFirstMonsterTarget.FIRSTMONSTER;
    private static final CardRarity RARITY=CardRarity.BASIC;


    public YUZUDesignShooting() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=11;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(3);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractMonster target=YUZUDesignShooting.targetFirstMonster();
        if(target!=null){
            this.calculateCardDamage(target);
            addToBot(new DamageAction(target,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.LIGHTNING));
        }
    }


    public static AbstractMonster targetFirstMonster(){
        AbstractMonster target=null;
        for(AbstractMonster monster: AbstractDungeon.getCurrRoom().monsters.monsters){
            if(!monster.isDeadOrEscaped()){
                if(target==null||(AbstractDungeon.player.flipHorizontal^(Math.abs(monster.hb.x-AbstractDungeon.player.hb.x)<=Math.abs(target.hb.x-AbstractDungeon.player.hb.x)))){
                    target=monster;
                }
            }
        }
        return target;
    }

}
