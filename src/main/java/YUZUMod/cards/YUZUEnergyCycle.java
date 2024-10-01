package YUZUMod.cards;

import YUZUMod.YuzuMod;
import YUZUMod.character.YuzuCharacter;
import YUZUMod.effect.YUZUEnergyCycleEffect;
import YUZUMod.helper.ModHelper;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUEnergyCycle extends YUZUCustomCard{
    public static final String ID= ModHelper.makePath("EnergyCycle");
    private static final CardStrings CARD_STRINGS= CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME=CARD_STRINGS.NAME;
    private static final String IMG_PATH=ModHelper.makeCardImagePath(ID);
    private static final int COST=3;
    private static final String DESCRIPTION=CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE=CardType.ATTACK;
    private static final CardColor COLOR= YuzuCharacter.PlayerClass.YUZU_CARD;
    private static final CardTarget TARGET=CardTarget.ENEMY;
    private static final CardRarity RARITY=CardRarity.COMMON;

    public YUZUEnergyCycle() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage=this.damage=16;
    }

    @Override
    protected void upgradeMethod() {
        upgradeDamage(5);
    }

    @Override
    public void commonUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DamageAction(abstractMonster,new DamageInfo(abstractPlayer,this.damage), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new GainEnergyAction(1));
        CardCrawlGame.sound.play("BUFF_1");
        AbstractDungeon.topLevelEffectsQueue.add(new YUZUEnergyCycleEffect(YuzuMod.YUZUColor.cpy(),130.0F* Settings.scale));
    }

    @Override
    public void masterUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        commonUse(abstractPlayer,abstractMonster);
        addToBot(new GainEnergyAction(1));
        AbstractDungeon.topLevelEffectsQueue.add(new YUZUEnergyCycleEffect(Color.FOREST.cpy(),-190.0F* Settings.scale));
    }

    @Override
    public void triggerOnCriticalHit(AbstractCreature target) {
        super.triggerOnCriticalHit(target);
        addToBot(new GainEnergyAction(1));
        AbstractDungeon.topLevelEffectsQueue.add(new YUZUEnergyCycleEffect(Color.GOLDENROD.cpy(),250.0F* Settings.scale));
    }
}
