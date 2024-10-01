package YUZUMod.helper;

import YUZUMod.YuzuMod;
import basemod.BaseMod;
import basemod.interfaces.ISubscriber;
import basemod.interfaces.OnPlayerTurnStartSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PreMonsterTurnSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class YUZUConcealingToPeopleHelper implements ISubscriber, PostBattleSubscriber , PreMonsterTurnSubscriber, OnPlayerTurnStartSubscriber {
    private AbstractMonster monster;
    private static Texture Fork=TextureLoader.getTexture(ModHelper.makeImgPath("effect","false"));
    private static TextureRegion ForkRegion=new TextureRegion(Fork);
    public YUZUConcealingToPeopleHelper(){
        BaseMod.subscribe(this);
    }


    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        this.monster=null;
    }


    @Override
    public boolean receivePreMonsterTurn(AbstractMonster abstractMonster) {
        if(this.monster!=null){
            return abstractMonster==monster;
        }
        return true;
    }

    @Override
    public void receiveOnPlayerTurnStart() {
        this.monster=null;
    }

    public void setMonster(AbstractMonster monster){
        this.monster=monster;
    }


//    @Override
//    public void receivePostRender(SpriteBatch spriteBatch) {
//        if(this.monster!=null){
//            for(AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
//                if(m!=this.monster){
//                    Hitbox hitbox=m.intentHb;
//                    spriteBatch.setColor(Color.WHITE);
//                    spriteBatch.draw(Fork,hitbox.x,hitbox.y,hitbox.width,hitbox.height,0,0,1.0F,1.0F);
//                }
//
//            }
//        }
//    }

    public static void renderMonsterFork(AbstractMonster monster,SpriteBatch sb){
        Hitbox hitbox=monster.intentHb;
        sb.setColor(Color.WHITE);
        sb.draw(Fork,hitbox.x,hitbox.y,hitbox.width,hitbox.height,0,0,1.0F,1.0F);

    }

    @SpirePatch(clz = AbstractMonster.class,method = "render")
    public static class renderPatch{
        @SpirePostfixPatch
        public static void postfixPatch(AbstractMonster _instance,SpriteBatch sb){
            if(YuzuMod.concealingToPeopleHelper.monster!=null&&
                    YuzuMod.concealingToPeopleHelper.monster!=_instance&&!_instance.isDeadOrEscaped()){
                renderMonsterFork(_instance,sb);
            }
        }
    }
}
