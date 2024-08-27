package YUZUMod.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class YUZUManageCriticalEffect extends AbstractGameEffect {
    private final float x;
    private final float y;
    private final float targetX;
    private final float targetY;
    private int count;
    public YUZUManageCriticalEffect(float x, float y, float targetX, float targetY){
        this.x=x;
        this.y=y;
        this.targetX=targetX;
        this.targetY=targetY;
        this.count=20;
        this.duration=0.02F;
    }

    @Override
    public void update() {
        this.duration-= Gdx.graphics.getDeltaTime();
        if(this.duration<0.0F){
            AbstractDungeon.topLevelEffectsQueue.add(new YUZUGainCriticalEffect(this.x,this.y,this.targetX,this.targetY));
            this.duration=0.02F;
            this.count--;
            if(this.count<0)
                this.isDone=true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
