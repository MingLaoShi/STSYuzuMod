package baModDeveloper.action;

import baModDeveloper.power.YUZUBurningPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YUZUMaliciousDesignAction extends AbstractGameAction {
    AbstractPlayer p;
    boolean mastered;
    public YUZUMaliciousDesignAction(boolean b) {
        p= AbstractDungeon.player;
        this.mastered=b;
    }

    @Override
    public void update() {
        int block=this.p.currentBlock;
        if(mastered){
            addToTop(new YUZUTriggerBurningAction(this.p,1));
        }
        addToTop(new ApplyPowerAction(this.p,this.p,new YUZUBurningPower(this.p,this.p,block)));
        addToTop(new RemoveAllBlockAction(this.p,this.p));

        for(AbstractMonster m:AbstractDungeon.getCurrRoom().monsters.monsters){
            if (!m.isDeadOrEscaped()) {
                int mBlock=m.currentBlock;
                if(mBlock<=0){
                    continue;
                }
                if(mastered){
                    addToTop(new YUZUTriggerBurningAction(m,1));
                }
                addToTop(new ApplyPowerAction(m,this.p,new YUZUBurningPower(m,this.p,mBlock)));
                addToTop(new RemoveAllBlockAction(m,this.p));
            }


        }
        this.isDone=true;
    }
}
