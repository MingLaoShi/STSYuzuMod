package YUZUMod.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class YUZUAbstractGameActionPatch{
    @SpirePatch(clz = AbstractGameAction.class,method = SpirePatch.CLASS)
    public static class FieldPatch{
        public static SpireField<Boolean> isCriticalHit=new SpireField<>(()->false);
    }
}
