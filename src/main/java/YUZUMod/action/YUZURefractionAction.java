package YUZUMod.action;

import basemod.cardmods.RetainMod;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.List;

public class YUZURefractionAction extends AbstractGameAction {
    private boolean freeToPlayOnce;
    private AbstractPlayer p;
    private boolean mastered;
    public YUZURefractionAction(int energyOnUse, boolean freeToPlayOnce, boolean mastered){
        this.amount=energyOnUse;
        this.p= AbstractDungeon.player;
        this.freeToPlayOnce=freeToPlayOnce;
        this.mastered=mastered;
    }
    @Override
    public void update() {
        int effect= EnergyPanel.totalCount;
        if(this.amount!=-1){
            effect=this.amount;
        }
        if(mastered){
            effect=2;
        }
        if(this.p.hasRelic(ChemicalX.ID)){
            effect+=2;
            this.p.getRelic(ChemicalX.ID).flash();
        }
        this.amount=effect;
        addToTop(new SelectCardsInHandAction(1,"//",false,false,this::filter,this::callback));
        if(!this.freeToPlayOnce){
            this.p.energy.use(EnergyPanel.totalCount);
        }
        this.isDone=true;
    }

    private boolean filter(AbstractCard card){
        return true;
    }

    private void callback(List<AbstractCard> cards){
        AbstractCard card=cards.get(0).makeStatEquivalentCopy();
        CardModifierManager.addModifier(card,new RetainMod());
        addToTop(new MakeTempCardInHandAction(card,this.amount));
    }
}
