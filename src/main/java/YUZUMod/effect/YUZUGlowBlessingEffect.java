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

public class YUZUGlowBlessingEffect extends AbstractGameEffect {
    Vector2 pos;
    private float a;
    private static Texture star= TextureLoader.getTexture(ModHelper.makeImgPath("effect","star"));
    private static float SCALE=3.0F;

    public YUZUGlowBlessingEffect(float x,float y){
        pos=new Vector2(x,y);
        this.color= Color.WHITE.cpy();
        a=-1.0F;
        this.duration=2.0F;
    }

    @Override
    public void update() {
        Vector2 tmp=pos.cpy();
        tmp.nor();
        tmp.x=tmp.x* Gdx.graphics.getDeltaTime();
        tmp.y=tmp.y*Gdx.graphics.getDeltaTime();
        pos.add(tmp);
        this.a+=Gdx.graphics.getDeltaTime();
        this.duration-=Gdx.graphics.getDeltaTime();
        if(this.duration<0.0){
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
