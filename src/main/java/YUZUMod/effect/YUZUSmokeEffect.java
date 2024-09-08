package YUZUMod.effect;

import YUZUMod.helper.ModHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class YUZUSmokeEffect extends AbstractGameEffect {
    private float x,y;
    private Color color;
    private static Texture img= ImageMaster.loadImage(ModHelper.makeImgPath("effect","smoke"));
    private ShapeRenderer shapeRenderer;
    public YUZUSmokeEffect(){
        this.x= Settings.WIDTH/2.0F;
        this.y= AbstractDungeon.floorY;
        this.duration=1.0F;
        this.color=Color.WHITE.cpy();
        this.color.a=0.0F;
        shapeRenderer=new ShapeRenderer();
    }

    @Override
    public void update() {
        if (this.duration > 0.5F) {
            // 透明度从 0.0 增加到 0.5
            this.color.a = MathUtils.lerp(0.0F, 0.5F, (1.0F - this.duration) * 2.0F); // 从 duration = 1.0 到 0.5
        } else {
            // 透明度从 0.5 减少到 0.0
            this.color.a = MathUtils.lerp(0.5F, 0.0F, 1.0F - (this.duration * 2.0F)); // 从 duration = 0.5 到 0.0
        }


        this.duration-= Gdx.graphics.getDeltaTime();
        if(this.duration<0.0F){
            this.isDone=true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(Color.WHITE.cpy());
        spriteBatch.draw(img,this.x,this.y);
        spriteBatch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(this.color);
        // 绘制全屏矩形
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        shapeRenderer.end();
        spriteBatch.begin();
    }

    @Override
    public void dispose() {

    }
}
