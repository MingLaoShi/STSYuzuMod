package baModDeveloper.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustTopCardAction extends AbstractGameAction {
    boolean shuffled;
    AbstractPlayer player;
    public ExhaustTopCardAction(int amount,boolean shuffled) {
        this.shuffled = shuffled;
        this.amount=amount;
        this.player= AbstractDungeon.player;
    }

    @Override
    public void update() {
        if(this.player.drawPile.size()<this.amount&&!this.shuffled){
            addToTop(new ExhaustTopCardAction(this.amount,true));
            addToTop(new EmptyDeckShuffleAction());
            this.isDone=true;
            return;
        }
        for(int i=Math.min(this.amount,this.player.drawPile.size())-1;i>=0;i--){
            addToTop(new ExhaustSpecificCardAction(this.player.drawPile.group.get(i),this.player.drawPile));
        }
        this.isDone=true;
    }
}
