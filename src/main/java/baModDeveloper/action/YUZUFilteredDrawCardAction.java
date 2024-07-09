package baModDeveloper.action;

import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.function.Predicate;

public class YUZUFilteredDrawCardAction extends AbstractGameAction {

    private final Predicate<AbstractCard> filter;
    private final boolean clearDrawHistory;
    private boolean shuffleCheck=false;
    private static final Logger logger= LogManager.getLogger(YUZUFilteredDrawCardAction.class.getName());
    public static ArrayList<AbstractCard> drawnCards=new ArrayList<>();
    private AbstractGameAction followUpAction=null;
    private AbstractPlayer p;
    public YUZUFilteredDrawCardAction(int amount, Predicate<AbstractCard> filter,boolean clearDrawHistory,AbstractGameAction followUpAction){
        this.amount=amount;
        this.filter=filter;
        this.actionType=ActionType.DRAW;
        this.p=AbstractDungeon.player;
        this.clearDrawHistory=clearDrawHistory;
        this.followUpAction=followUpAction;
        if(Settings.FAST_MODE){
            this.duration= Settings.ACTION_DUR_XFAST;
        }else {
            this.duration=Settings.ACTION_DUR_FASTER;
        }
    }
    @Override
    public void update() {
        if(this.clearDrawHistory)
            drawnCards.clear();
        if(p.hasPower(NoDrawPower.POWER_ID)){
            p.getPower(NoDrawPower.POWER_ID).flash();
            this.endActionWithFollowUp();
            return;
        }
        if(this.amount<=0){
            endActionWithFollowUp();
            return;
        }
        int deckSize= (int) this.p.drawPile.group.stream().filter(this.filter).count();
        int discardSize= (int) this.p.discardPile.group.stream().filter(this.filter).count();
        if(deckSize+discardSize==0){
            endActionWithFollowUp();
            return;
        }

        if(this.p.hand.size()== BaseMod.MAX_HAND_SIZE){
            this.p.createHandIsFullDialog();
            endActionWithFollowUp();
            return;
        }

        if(!this.shuffleCheck){
            if(this.amount+this.p.hand.size()>BaseMod.MAX_HAND_SIZE){
                this.amount=BaseMod.MAX_HAND_SIZE-this.p.hand.size();
                this.p.createHandIsFullDialog();
            }
            if(this.amount>deckSize){
                int temp=this.amount-deckSize;
                addToTop(new YUZUFilteredDrawCardAction(temp,this.filter,false,this.followUpAction));
                addToTop(new EmptyDeckShuffleAction());
                if(deckSize!=0){
                    addToTop(new YUZUFilteredDrawCardAction(deckSize,this.filter,false,null));
                }
                this.amount=0;
                this.isDone=true;
                return;
            }
            this.shuffleCheck=true;
        }
        this.duration-= Gdx.graphics.getDeltaTime();

        if(this.amount!=0&&this.duration<0.0F){
            if(Settings.FAST_MODE){
                this.duration=Settings.ACTION_DUR_XFAST;
            }else {
                this.duration=Settings.ACTION_DUR_FASTER;
            }

            this.amount--;
            for(int i=this.p.drawPile.size()-1;i>=0;i--){
                AbstractCard card=this.p.drawPile.group.get(i);
                if(this.filter.test(card)){
                    drawnCards.add(card);
                    card.current_x= CardGroup.DRAW_PILE_X;
                    card.current_y=CardGroup.DRAW_PILE_Y;
                    card.setAngle(0.0F,true);
                    card.lighten(false);
                    card.drawScale=0.12F;
                    card.targetDrawScale=0.75F;
                    if(this.p.hand.size()==BaseMod.MAX_HAND_SIZE){
                        this.p.createHandIsFullDialog();
                        break;
                    }
                    CardCrawlGame.sound.playAV("CARD_DRAW_8", -0.12F, 0.25F);
                    this.p.hand.addToHand(card);
                    this.p.drawPile.removeCard(card);
                    card.triggerWhenDrawn();
                    for(AbstractPower p:this.p.powers){
                        p.onCardDraw(card);
                    }
                    for(AbstractRelic r:this.p.relics){
                        r.onCardDraw(card);
                    }
                    this.p.onCardDrawOrDiscard();
                    this.p.hand.refreshHandLayout();
                    break;
                }
            }

            if(this.amount==0){
                endActionWithFollowUp();
            }
        }
    }

    private void endActionWithFollowUp(){
        this.isDone=true;
        if(this.followUpAction!=null){
            addToTop(this.followUpAction);
        }
    }
}
