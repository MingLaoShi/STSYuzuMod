package YUZUMod.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactLineEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class YUZUCollaborativeParticleEffect extends AbstractGameEffect {

    private float rotationRate;
    private float sX,sY,tX,tY;
    private AbstractCard card;
    private float x,y;
    private float speed;
    private Vector2 pos;
    private Vector2 target;
    private static final float VELOCITY_RAMP_RATE = 3000.0F * Settings.scale;
    private static final float DST_THRESHOLD = 42.0F * Settings.scale;
    private static final float MAX_VELOCITY = 4000.0F * Settings.scale;
    private boolean stopRotating=false;
    private float distance;
    public YUZUCollaborativeParticleEffect(float tX, float tY,AbstractCard card) {
//        this.sX = Settings.WIDTH/2.0F;
//        this.sY = Settings.HEIGHT/2.0F;
        this.pos=new Vector2(Settings.WIDTH/2.0F,Settings.HEIGHT/2.0F);
        this.target=new Vector2(tX,tY);
//        this.tX = tX;
//        this.tY = tY;
        this.x=this.sX;
        this.y=this.sY;
        this.duration=1.0F;

        this.scale = 1.0F * Settings.scale;
        //        this.rotation= MathUtils.random(-90.0F,90.0F);
        this.rotation = 180;
        this.rotationRate = MathUtils.random(600.0F, 650.0F) * Settings.scale;
        this.speed= MathUtils.random(1000.0F,1200.0F)*Settings.scale;

        this.distance=this.target.dst(this.pos);
        this.card=card;
    }

    @Override
    public void update() {
//        this.speedX=MathUtils.lerp(this.speedX,(this.tX-this.x)/this.duration,1.0F-this.duration);
//        this.speedY=MathUtils.lerp(this.speedY,(this.tY-this.y)/this.duration,1.0F-this.duration);
//        this.x+=this.speedX*Gdx.graphics.getDeltaTime();
//        this.y+=this.speedY*Gdx.graphics.getDeltaTime();
//        this.rotation= (float) Math.atan2(this.speedY,this.speedX);
        Vector2 temp=new Vector2(this.pos.x-this.target.x,this.pos.y-this.target.y);
        temp.nor();
        float targetAngle=temp.angle();
        this.rotationRate+=Gdx.graphics.getDeltaTime()*2000.0F;
//        this.scale+=Gdx.graphics.getDeltaTime()*Settings.scale;
        if(!this.stopRotating)
            this.rotation+=Gdx.graphics.getDeltaTime()*this.rotationRate;
        if(this.rotation<0.0F){
            this.rotation+=360.0F;
        }
        this.rotation %= 360.0F;
        if(!this.stopRotating&&Math.abs(this.rotation-targetAngle)<Gdx.graphics.getDeltaTime()*this.rotationRate){
            this.rotation=targetAngle;
            this.stopRotating=true;
        }
        temp.setAngle(this.rotation);
        temp.x*=Gdx.graphics.getDeltaTime()*this.speed;
        temp.y*=Gdx.graphics.getDeltaTime()*this.speed;
        this.pos.sub(temp);

        this.speed+=Gdx.graphics.getDeltaTime()*VELOCITY_RAMP_RATE*1.5F;
        this.speed=Math.min(this.speed,MAX_VELOCITY);

        if(this.target.dst(this.pos)<DST_THRESHOLD){
            AbstractDungeon.topLevelEffectsQueue.add(new DamageImpactLineEffect(this.target.x - DST_THRESHOLD, this.target.y));
            AbstractDungeon.topLevelEffectsQueue.add(new FlashAtkImgEffect(this.target.x, this.target.y, AbstractGameAction.AttackEffect.BLUNT_LIGHT, false));
            this.isDone=true;
        }

        this.card.angle=this.rotation+90.0F;
        this.card.current_x=this.pos.x;
        this.card.current_y=this.pos.y;
        this.card.target_x=this.card.current_x;
        this.card.target_y=this.card.current_y;
        this.card.drawScale=Math.min(MathUtils.lerp(0.1F,0.5F,this.target.dst(this.pos)/this.distance),0.5F);
        this.card.targetDrawScale=Math.min(MathUtils.lerp(0.1F,0.5F,temp.dst(target)/this.distance),0.5F);


        this.duration-=Gdx.graphics.getDeltaTime();
        if(this.duration<0.0F){
            this.isDone=true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        this.card.renderOuterGlow(spriteBatch);
        this.card.render(spriteBatch);
    }

    @Override
    public void dispose() {

    }
}
