package YUZUMod.effect;

import YUZUMod.helper.ModHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class YUZUSmokeEffect extends AbstractGameEffect {
    private float x,y;
    private Color color;
    private static Texture img= ImageMaster.loadImage(ModHelper.makeImgPath("effect","smoke"));
    private Vector2 pos;
    private float speed;
    public YUZUSmokeEffect(){
        this.x= Settings.WIDTH/2.0F;
        this.y= AbstractDungeon.floorY;
        this.duration=2.0F;
        this.color=Color.WHITE.cpy();
        this.color.a=0.0F;
        this.pos=new Vector2(-img.getWidth(),0.0F);
        this.speed=3000.0F*Settings.xScale;
    }

    @Override
    public void update() {
        if (this.duration > 0.7F) {
            // 透明度从 0.0 增加到 0.5（当 duration 从 2.0 递减到 0.7 时）
            this.color.a = MathUtils.lerp(0.0F, 0.5F, (2.0F - this.duration) / 1.3F); // 从 duration = 2.0 到 0.7
        } else {
            // 透明度从 0.5 减少到 0.0（当 duration 从 0.7 递减到 0.0 时）
            this.color.a = MathUtils.lerp(0.5F, 0.0F, (0.7F-this.duration) / 0.7F); // 从 duration = 0.7 到 0.0
        }
//        System.out.println(this.color.a);
//        this.color.a=1.0F;
        this.pos.x+=(this.speed*Gdx.graphics.getDeltaTime());
        if(this.duration<1.6F&&this.duration>0.3F){
            this.speed=MathUtils.lerp(3000.0F*Settings.xScale,0.0F, (float) ((1.6-this.duration)/1.3F));
        }

        this.duration-= Gdx.graphics.getDeltaTime();
        if(this.duration<0.0F){
            this.isDone=true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if(this.isDone){
            return;
        }
        spriteBatch.setColor(this.color);
        spriteBatch.draw(img,this.pos.x,this.pos.y,img.getWidth()*Settings.xScale,img.getHeight()*Settings.yScale);

    }

    @Override
    public void dispose() {

    }
}
