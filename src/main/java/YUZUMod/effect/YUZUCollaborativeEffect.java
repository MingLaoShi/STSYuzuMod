package YUZUMod.effect;

import YUZUMod.cards.YUZUCustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YUZUCollaborativeEffect extends AbstractGameEffect {
    private float sX,sY,tX,tY;
    List<String> cardList;
    AbstractGameAction action;
    int amount;
    public YUZUCollaborativeEffect(float tX, float tY, AbstractGameAction action) {
        this.tX = tX;
        this.tY = tY;

        this.scale=0.12F;

        cardList=new ArrayList<>();
        YUZUCustomCard.MasterCards.forEach((key, value) -> {
            cardList.add(key);
        });

        Collections.shuffle(cardList);
        this.action=action;
        amount=5;
    }

    @Override
    public void update() {
        this.scale-=Gdx.graphics.getDeltaTime();
        if(this.scale<0.0F&&!cardList.isEmpty()){
            String id=cardList.get(0);
            cardList.remove(id);
            AbstractDungeon.effectsQueue.add(new YUZUCollaborativeParticleEffect(this.tX,this.tY, CardLibrary.getCard(id).makeCopy()));
            this.scale=0.2F;
            amount--;
        }

        if(cardList.isEmpty()||amount==0){
            this.isDone=true;
            AbstractDungeon.actionManager.addToTop(action);
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
