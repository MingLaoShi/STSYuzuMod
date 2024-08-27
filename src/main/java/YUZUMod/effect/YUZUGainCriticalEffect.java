package YUZUMod.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.scene.TorchParticleLEffect;

public class YUZUGainCriticalEffect extends AbstractGameEffect {

    private final float x;
    private final float y;
    private final float targetX;
    private final float targetY;
    private boolean flashed=false;
    private TextureAtlas.AtlasRegion img = ImageMaster.BORDER_GLOW_2;
    private float currentX;
    private float currentY;
    private float distance;
    private float angle;
    private float speed;

    private float currentDistance=0.0F;
    private float currentAngle=0.0F;

    public YUZUGainCriticalEffect(float x, float y, float targetX, float targetY){
        this.x=x;
        this.y=y;
        this.targetX=targetX;
        this.targetY=targetY;
        this.duration=3.0F;

        this.color=new Color(1.0F,1.0F,0.1F,1.0F);
        this.color.a=0.0F;

        this.currentX=this.x;
        this.currentY=this.y;

        this.distance= (float) Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
        this.angle= (float) Math.toDegrees(30.0F);

        this.speed=5.0F;
    }

    @Override
    public void update() {
        if(!flashed){
            CardCrawlGame.sound.play("ATTACK_FLAME_BARRIER",0.05F);
            AbstractDungeon.effectsQueue.add(new BorderFlashEffect(new Color(1.0F, 1.0F, 0.1F, 1.0F)));
            AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(new Color(1.0F, 0.4F, 0.1F, 1.0F)));
            this.flashed=true;
        }
        for(int i=0;i<12;i++){
            this.duration-= Gdx.graphics.getDeltaTime()/2;

            this.currentDistance= Interpolation.fade.apply(0.0F,this.distance,this.duration/3.0F);
            this.currentAngle= (float) (this.angle+(3.0-this.duration)*this.speed);
            this.currentX= (float) (this.targetX+this.currentDistance*Math.cos(this.currentAngle));
            this.currentY= (float) (this.targetY+this.currentDistance*Math.sin(this.currentAngle));

            AbstractDungeon.effectsQueue.add(new TorchParticleLEffect(this.currentX,this.currentY));
        }



        if(this.duration<0.0F){
            this.isDone=true;
            AbstractDungeon.effectsQueue.add(new InflameEffect(AbstractDungeon.player));
//            AbstractDungeon.effectsQueue.add(new );
        }


    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
