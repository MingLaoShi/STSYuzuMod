package YUZUMod.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class YUZUEnergyCycleLightEffect extends AbstractGameEffect {
    private Vector2 pos;
    private static TextureRegion light= ImageMaster.vfxAtlas.findRegion("combat/blurDot2");
    private static float SCALE= Settings.renderScale*3.0F;
    private float waitDur;
    public YUZUEnergyCycleLightEffect(float x, float y, Color color){
        this.pos=new Vector2(x,y);
        this.duration=0.5F;
        this.waitDur=0.3F;
        this.color=color;
        this.color.a=0.7F;
        this.scale=(this.duration+this.waitDur)*SCALE;

    }

    @Override
    public void update() {
        this.waitDur-=Gdx.graphics.getDeltaTime();
        if(this.waitDur<0.0F){
            this.duration-= Gdx.graphics.getDeltaTime();
            this.scale=this.duration*SCALE;
            if(this.duration<0.0F){
                this.isDone=true;
            }
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(this.color);
        spriteBatch.draw(light,this.pos.x,this.pos.y,light.getRegionWidth()/2.0F,light.getRegionHeight()/2.0F,
                light.getRegionWidth(),light.getRegionHeight(),this.scale,this.scale,0.0F);
    }

    @Override
    public void dispose() {

    }
}
