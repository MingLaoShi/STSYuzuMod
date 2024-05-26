package baModDeveloper.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class YUZUMicroBlastingAction extends AbstractGameAction {
    private DamageInfo info;
    private boolean isMastered;
    public YUZUMicroBlastingAction(AbstractMonster target, DamageInfo info,boolean isMastered){
        this.target=target;
        this.info=info;
        this.isMastered=isMastered;
        this.actionType=ActionType.DAMAGE;
        this.startDuration= Settings.ACTION_DUR_FAST;
        this.duration=this.startDuration;
    }
    @Override
    public void update() {
        if(shouldCancelAction()){
            this.isDone=true;
            return;
        }
        tickDuration();
        if(this.isDone){
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX,this.target.hb.cY,AttackEffect.SLASH_DIAGONAL));
            this.target.damage(this.info);
            if(this.target.lastDamageTaken>0){
                if(isMastered){
                    addToTop(new ApplyPowerAction(this.target,AbstractDungeon.player,new WeakPower(this.target,this.target.lastDamageTaken,false)));
                }
                addToTop(new ApplyPowerAction(this.target,AbstractDungeon.player,new VulnerablePower(this.target,this.target.lastDamageTaken,false)));
            }

            if(AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()){
                AbstractDungeon.actionManager.clearPostCombatActions();
            }else{
                addToTop(new WaitAction(0.1F));
            }
        }
    }
}
