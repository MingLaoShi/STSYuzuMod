package YUZUMod.patch;

import YUZUMod.helper.ModHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.events.beyond.SensoryStone;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

public class YUZUSensoryStonePatch {
    @SpirePatch(clz = SensoryStone.class,method = "getRandomMemory")
    public static class getRandomMemoryPatch{
        private static UIStrings yuzuEventString= CardCrawlGame.languagePack.getUIString(ModHelper.makePath("SensoryStone"));
        @SpireInsertPatch(rloc = 2,localvars = "memories")
        public static void insertPatch(SensoryStone __instance, ArrayList<String> memories){
            memories.add(yuzuEventString.TEXT[0]);
        }
    }
}
