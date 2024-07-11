package YUZUMod.power;

import YUZUMod.helper.ModHelper;
import YUZUMod.action.BATwinsBurnDamageAction;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BATwinsBurnPower extends AbstractPower implements HealthBarRenderPower {
    public static final String POWER_ID = "BATwinsMod:BurnPower";
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME = powerStrings.NAME;
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final String IMG_84 = ModHelper.makeImgPath("power", "Burn84");
    private static final String IMG_32 = ModHelper.makeImgPath("power", "Burn32");
    private static final Color HEALTHBARCOLOR = new Color(254.0F / 255.0F, 168.0F / 255.0F, 198.0F / 255.0F, 1.0F);

    private AbstractCreature source;

    public BATwinsBurnPower(AbstractCreature owner, AbstractCreature source, int Amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = TYPE;
        this.source = source;
        this.amount = Amount;
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_84), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(IMG_32), 0, 0, 32, 32);


        if (this.amount >= 9999) {
            this.amount = 9999;
        }
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
//        addToBot(new DamageAction(this.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
//        ArrayList<AbstractMonster> ms=AbstractDungeon.getCurrRoom().monsters.monsters;
//        for (AbstractMonster m : ms) {
//            if (m != this.owner)
//                addToBot(new DamageAction(m, new DamageInfo(this.owner, this.amount / 2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
//        }
//        this.flashWithoutSound();
//        addToBot(new DamageAction(this.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
//        this.amount--;
//        if (this.amount == 0) {
//            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
//        }
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            addToBot(new BATwinsBurnDamageAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public void onDeath() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead() &&
                this.owner.currentHealth <= 0) {
            addToTop(new WaitAction(0.2F));
            addToTop((AbstractGameAction) new DamageAllEnemiesAction(null,
                    DamageInfo.createDamageMatrix(this.amount * 2, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }

    @Override
    public Color getColor() {
        return HEALTHBARCOLOR;
    }
    //    @Override
//    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
//        if(damageType== DamageInfo.DamageType.NORMAL){
//            this.amount++;
//        }
//        return damage;
//    }

}
