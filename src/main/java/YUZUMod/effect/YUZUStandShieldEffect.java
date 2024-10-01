package YUZUMod.effect;

import YUZUMod.helper.ModHelper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class YUZUStandShieldEffect extends AbstractGameEffect {
    private TextureAtlas atlas;
    private SkeletonJson json;
    private SkeletonData data;
    private AnimationStateData stateData;
    private Skeleton skeleton;
    private AnimationState state;
//    private SkeletonBounds bounds;
    public YUZUStandShieldEffect(){
        this.atlas= new TextureAtlas(ModHelper.makeFilePath("effect/StandShield","skeleton","atlas"));
        this.json=new SkeletonJson(this.atlas);
        this.json.setScale(Settings.renderScale*2);

        this.data=json.readSkeletonData(Gdx.files.internal(ModHelper.makeFilePath("effect/StandShield","skeleton37","json")));
        this.skeleton=new Skeleton(data);
        this.skeleton.setColor(Color.WHITE);
        this.stateData=new AnimationStateData(data);
        this.state=new AnimationState(this.stateData);
        this.state.setAnimation(0,"animation",false);
//        this.bounds=new SkeletonBounds();
    }

    @Override
    public void update() {
        this.state.update(Gdx.graphics.getDeltaTime());
        this.state.apply(this.skeleton);
        this.skeleton.updateWorldTransform();
//        this.bounds.update(skeleton,true);

        this.skeleton.setPosition(AbstractDungeon.player.hb.cX-128.0F*Settings.renderScale*2,
                AbstractDungeon.player.hb.cY+25.0F*Settings.renderScale*2);
        if(this.state.getCurrent(0)==null||this.state.getCurrent(0).isComplete()){
            this.isDone=true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.end();
        CardCrawlGame.psb.begin();
        AbstractCreature.sr.draw(CardCrawlGame.psb,this.skeleton);
        CardCrawlGame.psb.end();
        spriteBatch.begin();
        spriteBatch.setBlendFunction(770,771);
    }

    @Override
    public void dispose() {

    }
}
