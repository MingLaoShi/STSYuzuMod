package YUZUMod.power;

import YUZUMod.helper.ModHelper;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

public class YUZUVigorMonsterPower extends VigorPower {
    public static final String POWER_ID= ModHelper.makePath("VigorMonster");
    private static final AbstractPower.PowerType TYPE= PowerType.BUFF;
    private static final PowerStrings powerStrings= CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    private static final String NAME=powerStrings.NAME;
    private static final String[] DESCRIPTIONS=powerStrings.DESCRIPTIONS;
    public YUZUVigorMonsterPower(AbstractCreature owner,int amount){
        super(owner,amount);
        updateDescription();
        this.ID = POWER_ID;
        this.canGoNegative = true;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return damage + this.amount;
    }
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0)
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0)
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    public void updateDescription() {
        if (this.owner.isPlayer) {
            super.updateDescription();
            return;
        }
        this.description = String.format(DESCRIPTIONS[0],this.amount);
        if (this.amount > 0) {
            this.type = AbstractPower.PowerType.BUFF;
        } else {
            this.type = AbstractPower.PowerType.DEBUFF;
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (this.owner.isPlayer)
            super.onUseCard(card, action);
    }

    @Override
    public void duringTurn() {
        if(this.owner instanceof AbstractMonster){
            if(((AbstractMonster) this.owner).getIntentDmg()>0)
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }
}
