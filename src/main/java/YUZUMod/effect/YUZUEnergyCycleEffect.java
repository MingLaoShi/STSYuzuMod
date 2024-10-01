package YUZUMod.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class YUZUEnergyCycleEffect extends AbstractGameEffect {
    private float offset;
    private Vector2 pos;
    private float totalDur;
    public YUZUEnergyCycleEffect(Color color,float offset){
        this.color=color;
        this.offset=offset;
        this.duration=this.totalDur=1.0F;
        this.pos=new Vector2(offset,AbstractDungeon.floorY);
    }

    @Override
    public void update() {
        this.duration-= Gdx.graphics.getDeltaTime();
        float offset= (float) Math.cos((this.totalDur-this.duration)*20.0F);
        float x=AbstractDungeon.player.hb.cX+offset*this.offset;
        float y=AbstractDungeon.floorY+(this.totalDur-this.duration)*400.0F* Settings.scale;
        AbstractDungeon.topLevelEffectsQueue.add(new YUZUEnergyCycleLightEffect(x,y,this.color));
        if(duration<0.0F){
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
