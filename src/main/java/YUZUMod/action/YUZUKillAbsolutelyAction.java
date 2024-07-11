package YUZUMod.action;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GiantTextEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

public class YUZUKillAbsolutelyAction extends AbstractGameAction {
    private DamageInfo info;

    public YUZUKillAbsolutelyAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
    }

    @Override
    public void update() {
        if (this.duration == 0.1F && this.target != null) {
            this.target.damage(this.info);
            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                    this.isDone = true;
                    return;
                }
                AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                addToTop(new JudgementAction(m, Integer.MAX_VALUE));
                if (m != null) {
                    addToTop(new VFXAction(new GiantTextEffect(m.hb.cX, m.hb.cY)));
                    addToTop(new WaitAction(0.8F));
                    addToTop(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, Color.GOLD.cpy())));

                }
            }
        }
        tickDuration();
    }
}
