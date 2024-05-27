package baModDeveloper.patch;

import baModDeveloper.cards.YUZUSolidifying;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.common.DiscardAtEndOfTurnAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DiscardAtEndOfTurnActionPatch {
    @SpirePatch(clz = DiscardAtEndOfTurnAction.class,method = "update")
    public static class updatePatch{
        @SpirePrefixPatch
        public static void updatePatch(DiscardAtEndOfTurnAction __instance){
            for(int i=0;i<AbstractDungeon.player.hand.size();i++){
                if(AbstractDungeon.player.hand.group.get(i) instanceof YUZUSolidifying){
                    if(i-1>=0){
                        AbstractDungeon.player.hand.group.get(i-1).retain=true;
                    }
                    if(i+1<AbstractDungeon.player.hand.group.size()){
                        AbstractDungeon.player.hand.group.get(i+1).retain=true;
                    }
                }
            }
        }
    }
}
