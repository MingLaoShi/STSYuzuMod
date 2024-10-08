package YUZUMod.cards;

import YUZUMod.character.YuzuCharacter;
import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.SearingBlowEffect;

public class YUZUSpreadShooting extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("SpreadShooting");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=2;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ENEMY;
    private static final CardRarity RARITY=CardRarity.COMMON;

    public YUZUSpreadShooting() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=14;
        this.baseMagicNumber=this.magicNumber=2;
        this.isMultiDamage=true;
    }

    @Override
    protected void upgradeMethod() {
        this.upgradeDamage(4);
        this.upgradeMagicNumber(1);
        this.upgradeDescription(CARD_STRINGS.UPGRADE_DESCRIPTION);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if(abstractMonster!=null){
            addToBot(new VFXAction(new SearingBlowEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, 0), 0.2F));
        }
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer,new VulnerablePower(abstractMonster,this.magicNumber,false)));
        if(!AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)){
            int amount=1;
            if(this.upgraded){
                amount=2;
            }
            addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new StrengthPower(abstractPlayer,amount)));
            this.exhaust=true;
        }else{
            this.exhaust=false;
        }
    }

}
