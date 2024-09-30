package YUZUMod.effect;

import YUZUMod.helper.ModHelper;
import YUZUMod.helper.TextureLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class YUZUPaintingEffect extends AbstractGameEffect {
    private float targetScale;
    private static Texture texture= TextureLoader.getTexture(ModHelper.makeImgPath("effect","aperture"));
    private static TextureRegion region=new TextureRegion(texture);
    private float startScale;
    private float totalDur;
    private float a;
    public YUZUPaintingEffect(float targetScale,float duration){
        this.startScale=this.scale=0.0F;
        this.targetScale=targetScale;
        this.duration=this.totalDur=duration;
        this.rotation=0.0F;
        this.color=Color.WHITE.cpy();
        this.a=-1.0F;
    }

    @Override
    public void update() {
        this.duration-= Gdx.graphics.getDeltaTime();
        if(this.duration<0.0F){
            this.isDone=true;
        }
        this.a=MathUtils.lerp(-1.0F,1.0F,1.0F-(this.duration/this.totalDur));

        this.scale= MathUtils.lerp(this.startScale,this.targetScale,1.0F-(this.duration/this.totalDur));
        this.rotation+=Gdx.graphics.getDeltaTime()*200.0F;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        this.color.a=1.0F-Math.abs(this.a);
        spriteBatch.setColor(this.color);
        spriteBatch.draw(region, AbstractDungeon.player.hb.cX-region.getRegionWidth()/2.0F,AbstractDungeon.player.hb.cY-region.getRegionHeight()/2.0F,
                region.getRegionWidth()/2.0F,region.getRegionHeight()/2.0F,
                region.getRegionWidth(),region.getRegionHeight(),
                this.scale,this.scale,this.rotation);
        spriteBatch.draw(region, AbstractDungeon.player.hb.cX-region.getRegionWidth()/2.0F,AbstractDungeon.player.hb.cY-region.getRegionHeight()/2.0F,
                region.getRegionWidth()/2.0F,region.getRegionHeight()/2.0F,
                region.getRegionWidth(),region.getRegionHeight(),
                this.scale*1.3F,this.scale*1.3F,-this.rotation);
    }

    @Override
    public void dispose() {

    }
}
