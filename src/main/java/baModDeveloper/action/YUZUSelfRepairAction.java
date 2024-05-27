package baModDeveloper.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class YUZUSelfRepairAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private AbstractPlayer p;
    private boolean mastered;
    private int energyOnUse;
    private boolean upgraded;
    public YUZUSelfRepairAction(int energyOnUse,boolean freeToPlayOnce,boolean mastered,boolean upgraded){
        this.energyOnUse=energyOnUse;
        this.freeToPlayOnce=freeToPlayOnce;
        this.p= AbstractDungeon.player;
        this.upgraded=upgraded;
        this.mastered=mastered;
    }
    @Override
    public void update() {
        int effect= EnergyPanel.totalCount;
        if(this.energyOnUse!=-1){
            effect=this.energyOnUse;
        }
        if(this.p.hasRelic(ChemicalX.ID)){
            effect+=2;
            this.p.getRelic(ChemicalX.ID).flash();
        }
        if(this.upgraded){
            effect+=1;
        }
        if(mastered){
            addToTop(new HealAction(this.p,this.p,2*effect));
        }
        addToTop(new YUZUChangeCriticalHitRateMaxAction(effect));
        EnergyPanel.useEnergy(EnergyPanel.totalCount);
        this.isDone=true;
    }
}
