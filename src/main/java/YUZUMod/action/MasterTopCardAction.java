package YUZUMod.action;

import YUZUMod.cards.YUZUCustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MasterTopCardAction extends AbstractGameAction {
    boolean shuffled;
    AbstractPlayer player;
    public MasterTopCardAction(int amount, boolean shuffled) {
        this.shuffled = shuffled;
        this.amount=amount;
        this.player= AbstractDungeon.player;
    }

    @Override
    public void update() {
        if(this.player.drawPile.size()<this.amount&&!this.shuffled){
            addToTop(new MasterTopCardAction(this.amount,true));
            addToTop(new EmptyDeckShuffleAction());
            this.isDone=true;
            return;
        }
        for(int i=Math.min(this.amount,this.player.drawPile.size())-1;i>=0;i--){
            YUZUCustomCard.masterCard(this.player.drawPile.group.get(i));
        }
        this.isDone=true;
    }
}
