package YUZUMod.effect;

import YUZUMod.helper.ModHelper;
import YUZUMod.helper.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class YUZUDispersedStarEffect extends AbstractGameEffect {
    private Vector2 pos;
    private Vector2 center;
    private float a;
    private static Texture star= TextureLoader.getTexture(ModHelper.makeImgPath("effect","star"));
    private static float SCALE=3.0F;

    public YUZUDispersedStarEffect(float x,float y,float centerX,float centerY){
        this.pos=new Vector2(x,y);
        this.center=new Vector2(centerX,centerY);
        this.duration=2.0F;
        this.a=-1.0F;
        this.color= Color.WHITE.cpy();
    }

    @Override
    public void update() {
        Vector2 temp=this.pos.cpy().sub(this.center);
        temp.nor();
        this.pos.add(temp.scl(this.duration));
        this.a+= Gdx.graphics.getDeltaTime();
        this.duration-=Gdx.graphics.getDeltaTime();
        if(this.duration<0.0F){
            this.isDone=true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        this.color.a=1.0F-Math.abs(a);
        spriteBatch.setColor(this.color);
        spriteBatch.draw(star,pos.x,pos.y,star.getWidth()* Settings.scale*SCALE,star.getHeight()*Settings.scale*SCALE);


    }

    @Override
    public void dispose() {

    }
}
