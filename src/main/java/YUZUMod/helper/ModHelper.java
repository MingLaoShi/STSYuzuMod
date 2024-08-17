package YUZUMod.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

public class ModHelper {

    public static String makePath(String id) {
        return "YUZU:" + id;
    }

    public static String makeImgPath(String floder, String imgName) {
        return "YuzuModResources/img/" + floder + "/" + imgName + ".png";
    }

    public static String makeGifPath(String floder, String character, String imgName) {
        return String.format("YuzuModResources/img/%s/%s/%s.gif", floder, character, imgName);
    }


    public static String getModID() {
        return "YUZU";
    }



    public static String makeAudioPath(String filename, String fileType) {
        return "YuzuModResources/sound/" + filename + "." + fileType;
    }

    public static String makeAudioPath(String filename) {
        return makeAudioPath(filename, "ogg");
    }

    public static AbstractCard returnRandomEnergyCardInCombat(int cost){
        ArrayList<AbstractCard> list = new ArrayList();
        Iterator var1 = srcCommonCardPool.group.iterator();

        AbstractCard c;
        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (!c.hasTag(AbstractCard.CardTags.HEALING)&&c.cost==cost) {
                list.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }

        var1 = srcUncommonCardPool.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (!c.hasTag(AbstractCard.CardTags.HEALING)&&c.cost==cost) {
                list.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }

        var1 = srcRareCardPool.group.iterator();

        while(var1.hasNext()) {
            c = (AbstractCard)var1.next();
            if (!c.hasTag(AbstractCard.CardTags.HEALING)&&c.cost==cost) {
                list.add(c);
                UnlockTracker.markCardAsSeen(c.cardID);
            }
        }
        if(list.isEmpty()){
            return null;
        }
        return (AbstractCard)list.get(cardRandomRng.random(list.size() - 1));
    }

    public static String makeCardImagePath(String ID){
        String filename=ID.replace("YUZU:","");
        String filePath=ModHelper.makeImgPath("cards",filename);
        if(Gdx.files.internal(filePath).exists()){
            return filePath;
        }else{
            return ModHelper.makeImgPath("cards","default");
        }
    }

    public static String makeRelicImagePath(String ID){
        String filename=ID.replace("YUZU:","");
        String filePath=ModHelper.makeImgPath("relic",filename);
        if(Gdx.files.internal(filePath).exists()){
            return filePath;
        }else{
            return ModHelper.makeImgPath("relic","default");
        }
    }
    public static String makeRelicOutLinePath(String ID){
        return makeRelicImagePath(ID+"_p");
    }

    private static String[] SHOOTINGCARDS={
        "ConcentratedShooting","AonengShooting","DesignShooting","CoverShooting"
    };
    public static String getRandomShootingCardId(){
        return SHOOTINGCARDS[MathUtils.random(0,SHOOTINGCARDS.length-1)];
    }

    public static void FetchActionCallback(List<AbstractCard> cards) {
        for(AbstractCard card:cards){
            AbstractDungeon.player.onCardDrawOrDiscard();
            AbstractDungeon.player.powers.forEach(p->p.onCardDraw(card));
            AbstractDungeon.player.relics.forEach(r->r.onCardDraw(card));
            card.triggerWhenDrawn();
        }
        AbstractDungeon.player.hand.refreshHandLayout();
    }
}
