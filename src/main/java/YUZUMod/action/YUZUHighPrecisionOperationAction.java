package YUZUMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class YUZUHighPrecisionOperationAction extends AbstractGameAction {
    AbstractPlayer p;
    CardGroup group;

    public YUZUHighPrecisionOperationAction(int amount) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
        this.p = AbstractDungeon.player;
        group=new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.size() <= this.amount) {
                for(AbstractCard card:this.p.hand.group){
                    if(card.type== AbstractCard.CardType.SKILL){
                        addToTop(new YUZUPlayTempCardAction(card.makeSameInstanceOf(), null));
                    }
                }
                for(AbstractCard card:this.p.hand.group){
                    addToTop(new ExhaustSpecificCardAction(card,this.p.hand));
                }
                this.isDone=true;
                return;
            }

            AbstractDungeon.handCardSelectScreen.open("//", this.amount, false, false, false, false);
            tickDuration();
            return;
        }
        if(!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved){
            this.isDone=true;
        }

        if(this.isDone){
            for(AbstractCard card:AbstractDungeon.handCardSelectScreen.selectedCards.group){
                if(card.type== AbstractCard.CardType.SKILL){
                    addToTop(new YUZUPlayTempCardAction(card.makeSameInstanceOf(), null));
                }
            }
            for(AbstractCard card:AbstractDungeon.handCardSelectScreen.selectedCards.group){
                addToTop(new ExhaustSpecificCardAction(card,AbstractDungeon.handCardSelectScreen.selectedCards));
            }
        }

    }

}
