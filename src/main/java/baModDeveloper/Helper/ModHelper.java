package baModDeveloper.Helper;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.Iterator;

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
}
