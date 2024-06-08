package baModDeveloper.action;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class YUZUEstimateAction extends AbstractGameAction {
    int seeAmount;
    CardGroup temp;
    public YUZUEstimateAction(int seeAmount,int magicNumber) {
        this.amount=magicNumber;
        this.seeAmount=seeAmount;
    }

    @Override
    public void update() {
        temp=new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for(int i=0;i<Math.min(this.seeAmount,AbstractDungeon.player.drawPile.size());i++){
            temp.addToBottom(AbstractDungeon.player.drawPile.group.get(i));
        }
        addToTop(new SelectCardsAction(temp.group,amount,"",true,this::filter,this::callback));
        this.isDone=true;
    }

    private void callback(List<AbstractCard> cards) {
        Set<AbstractCard.CardType> types=new HashSet<>();
        for(AbstractCard c:cards){
            types.add(c.type);
        }
        if(temp!=null&&!temp.isEmpty()){
            for(AbstractCard c:temp.group){
                if(types.contains(c.type)){
                    c.unhover();
                    if(AbstractDungeon.player.hand.size()== BaseMod.MAX_HAND_SIZE){
                        AbstractDungeon.player.drawPile.moveToDiscardPile(c);
                        AbstractDungeon.player.createHandIsFullDialog();
                    }else{
                        AbstractDungeon.player.drawPile.removeCard(c);
                        AbstractDungeon.player.hand.addToTop(c);
                    }
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }

            }
        }
        cards.clear();
        AbstractDungeon.player.hand.refreshHandLayout();

    }

    private boolean filter(AbstractCard card) {
        return true;
    }

}
